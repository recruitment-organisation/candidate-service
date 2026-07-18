package recruitment.dev.candidateservice.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import recruitment.dev.candidateservice.dto.CVDto;
import recruitment.dev.candidateservice.entities.CV;
import recruitment.dev.candidateservice.entities.CV;
import recruitment.dev.candidateservice.entities.Candidate;
import recruitment.dev.candidateservice.mapper.CVMapper;
import recruitment.dev.candidateservice.repositories.CVRepository;
import recruitment.dev.candidateservice.repositories.CandidateRepository;
import recruitment.dev.candidateservice.service.CVService;
import recruitment.dev.candidateservice.service.MinioService;


import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CVServiceImpl implements CVService {



    private final CVRepository cvRepository;

    private final CandidateRepository candidateRepository;

    private final CVMapper cvMapper;

    private final MinioService minioService;


    @Override
    @Transactional
    public CVDto upload(Long candidateId, MultipartFile file) {

        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        // Vérifier si un CV existe déjà
        if (candidate.getCv() != null) {

            CV oldCv = candidate.getCv();

            // Supprimer le fichier de MinIO
            minioService.delete(oldCv.getFileUrl());

            // Supprimer le CV de la base
            cvRepository.delete(oldCv);

            // Rompre la relation
            candidate.setCv(null);
        }

        // Upload du nouveau fichier
        String url = minioService.upload(file);

        CV cv = CV.builder()
                .candidate(candidate)
                .fileName(file.getOriginalFilename())
                .fileUrl(url)
                .fileType(file.getContentType())
                .active(true)
                .uploadedAt(LocalDateTime.now())
                .build();

        candidate.setCv(cv);

        candidateRepository.save(candidate);

        return cvMapper.toDto(cv);
    }


    @Override
    public List<CVDto> findByCandidate(Long candidateId) {


        return cvRepository
                .findByCandidateId(candidateId)
                .stream()
                .map(cvMapper::toDto)
                .toList();

    }




    @Override
    public CVDto findById(Long id) {


        return cvRepository.findById(id)
                .map(cvMapper::toDto)
                .orElseThrow(
                        () -> new RuntimeException("CV not found")
                );
    }



    @Override
    public Resource download(Long id) {
        CV cv = cvRepository.findById(id).orElseThrow();
        InputStream stream = minioService.getFile(cv.getFileName());
        return new InputStreamResource(stream);
    }



}