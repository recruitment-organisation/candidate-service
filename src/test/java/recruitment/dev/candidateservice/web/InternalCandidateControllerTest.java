package recruitment.dev.candidateservice.web;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import recruitment.dev.candidateservice.dto.CandidateDto;
import recruitment.dev.candidateservice.exception.CandidateNotFoundException;
import recruitment.dev.candidateservice.service.CandidateService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InternalCandidateControllerTest {

    @Test
    void shouldReturnCandidateForWorkflowContract() {
        CandidateDto candidate = CandidateDto.builder()
                .id(1L)
                .keycloakId("kc-1")
                .firstName("Jean")
                .lastName("Dupont")
                .email("jean@email.com")
                .phone("+21611111111")
                .location("Tunis")
                .build();

        CandidateService candidateService = new CandidateService() {
            @Override
            public CandidateDto create(CandidateDto dto) {
                throw new UnsupportedOperationException();
            }

            @Override
            public CandidateDto update(Long id, CandidateDto dto) {
                throw new UnsupportedOperationException();
            }

            @Override
            public CandidateDto findById(Long id) {
                return candidate;
            }

            @Override
            public CandidateDto findByKeycloakId(String keycloakId) {
                throw new UnsupportedOperationException();
            }

            @Override
            public org.springframework.data.domain.Page<CandidateDto> findAll(org.springframework.data.domain.Pageable pageable) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void delete(Long id) {
                throw new UnsupportedOperationException();
            }
        };

        InternalCandidateController controller = new InternalCandidateController(candidateService);

        ResponseEntity<CandidateDto> response = controller.getCandidateById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
        assertEquals("kc-1", response.getBody().getKeycloakId());
        assertEquals("Jean", response.getBody().getFirstName());
    }

    @Test
    void shouldPropagateNotFoundWhenCandidateDoesNotExist() {
        CandidateService candidateService = new CandidateService() {
            @Override
            public CandidateDto create(CandidateDto dto) {
                throw new UnsupportedOperationException();
            }

            @Override
            public CandidateDto update(Long id, CandidateDto dto) {
                throw new UnsupportedOperationException();
            }

            @Override
            public CandidateDto findById(Long id) {
                throw new CandidateNotFoundException(id);
            }

            @Override
            public CandidateDto findByKeycloakId(String keycloakId) {
                throw new UnsupportedOperationException();
            }

            @Override
            public org.springframework.data.domain.Page<CandidateDto> findAll(org.springframework.data.domain.Pageable pageable) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void delete(Long id) {
                throw new UnsupportedOperationException();
            }
        };

        InternalCandidateController controller = new InternalCandidateController(candidateService);

        CandidateNotFoundException exception = assertThrows(
                CandidateNotFoundException.class,
                () -> controller.getCandidateById(99L)
        );

        assertEquals("Candidate not found with id: 99", exception.getMessage());
    }
}
