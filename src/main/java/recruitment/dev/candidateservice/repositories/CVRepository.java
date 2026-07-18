package recruitment.dev.candidateservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import recruitment.dev.candidateservice.entities.CV;

import java.util.List;

public interface CVRepository extends JpaRepository<CV, Long> {
    List<CV> findByCandidateId(Long candidateId);

}
