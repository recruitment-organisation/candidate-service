package recruitment.dev.candidateservice.exception;

public class CandidateNotFoundException extends RuntimeException {

    public CandidateNotFoundException(Long candidateId) {
        super("Candidate not found with id: " + candidateId);
    }

    public CandidateNotFoundException(String keycloakId) {
        super("Candidate not found with keycloakId: " + keycloakId);
    }
}
