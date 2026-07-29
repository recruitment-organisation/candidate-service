package recruitment.dev.candidateservice.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    @Test
    void shouldReturnNotFoundForCandidateNotFoundException() {
        ResponseEntity<Map<String, String>> response = globalExceptionHandler.handleCandidateNotFound(
                new CandidateNotFoundException(7L)
        );

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Candidate not found with id: 7", response.getBody().get("message"));
    }
}
