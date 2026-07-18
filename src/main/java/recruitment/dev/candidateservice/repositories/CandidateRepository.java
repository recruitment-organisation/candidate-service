package recruitment.dev.candidateservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import recruitment.dev.candidateservice.entities.Candidate;

import java.util.Optional;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    Candidate findByKeycloakId(String keycloakId);
}
