package recruitment.dev.candidateservice.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import recruitment.dev.candidateservice.dto.CVDto;

import java.util.List;


public interface CVService {


    CVDto upload(Long candidateId, MultipartFile file);


    List<CVDto> findByCandidate(Long candidateId);


    CVDto findById(Long id);




    Resource download(Long id);

}