package recruitment.dev.candidateservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import recruitment.dev.candidateservice.dto.CandidateDto;

public interface CandidateService {


    CandidateDto create(CandidateDto dto);


    CandidateDto update(Long id, CandidateDto dto);


    CandidateDto findById(Long id);


    CandidateDto findByKeycloakId(String keycloakId);


    Page<CandidateDto> findAll(Pageable pageable);


    void delete(Long id);

}