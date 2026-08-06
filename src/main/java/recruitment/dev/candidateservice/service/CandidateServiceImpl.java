package recruitment.dev.candidateservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import recruitment.dev.candidateservice.dto.CandidateDto;
import recruitment.dev.candidateservice.entities.Candidate;
import recruitment.dev.candidateservice.exception.CandidateNotFoundException;
import recruitment.dev.candidateservice.mapper.CandidateMapper;
import recruitment.dev.candidateservice.repositories.CandidateRepository;


@Service
@RequiredArgsConstructor
@Transactional
public class CandidateServiceImpl implements CandidateService {


    private final CandidateRepository candidateRepository;

    private final CandidateMapper candidateMapper;



    @Override
    public CandidateDto create(CandidateDto dto) {


        Candidate candidate =
                candidateMapper.toEntity(dto);


        Candidate saved =
                candidateRepository.save(candidate);



        return candidateMapper.toDto(saved);
    }



    @Override
    public CandidateDto update(Long id, CandidateDto dto) {
        Candidate candidate = getCandidateById(id);


        candidate.setFirstName(dto.getFirstName());
        candidate.setLastName(dto.getLastName());
        candidate.setPhone(dto.getPhone());
        candidate.setLocation(dto.getLocation());
        candidate.setAvailable(dto.getAvailable());
        candidate.setLinkedinUrl(dto.getLinkedinUrl());
        candidate.setGithubUrl(dto.getGithubUrl());


        return candidateMapper.toDto(
                candidateRepository.save(candidate)
        );
    }




    @Override
    @Transactional(readOnly = true)
    public CandidateDto findById(Long id) {
        return candidateMapper.toDto(getCandidateById(id));
    }




    @Override
    public CandidateDto findByKeycloakId(String keycloakId) {
        Candidate candidate = candidateRepository.findByKeycloakId(keycloakId);

        if (candidate == null) {
            throw new CandidateNotFoundException(keycloakId);
        }

        return candidateMapper.toDto(candidate);
    }





    @Override
    public Page<CandidateDto> findAll(Pageable pageable) {


        return candidateRepository
                .findAll(pageable)
                .map(candidateMapper::toDto);

    }




    @Override
    public void delete(Long id) {
        candidateRepository.delete(getCandidateById(id));
    }

    private Candidate getCandidateById(Long id) {
        return candidateRepository.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException(id));
    }

}
