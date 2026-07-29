package recruitment.dev.candidateservice.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import recruitment.dev.candidateservice.dto.CandidateDto;
import recruitment.dev.candidateservice.entities.Candidate;
import recruitment.dev.candidateservice.exception.CandidateNotFoundException;
import recruitment.dev.candidateservice.mapper.CandidateMapper;
import recruitment.dev.candidateservice.repositories.CandidateRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CandidateServiceImplTest {

    @Mock private CandidateRepository candidateRepository;
    @Mock private CandidateMapper candidateMapper;
    @InjectMocks private CandidateServiceImpl service;

    @Test
    void createsCandidateThroughMapperAndRepository() {
        CandidateDto request = dto();
        Candidate candidate = new Candidate();
        CandidateDto expected = dto();
        when(candidateMapper.toEntity(request)).thenReturn(candidate);
        when(candidateRepository.save(candidate)).thenReturn(candidate);
        when(candidateMapper.toDto(candidate)).thenReturn(expected);

        assertThat(service.create(request)).isSameAs(expected);
        verify(candidateRepository).save(candidate);
    }

    @Test
    void updatesEditableCandidateProfileFields() {
        Candidate candidate = new Candidate();
        CandidateDto request = dto();
        when(candidateRepository.findById(4L)).thenReturn(Optional.of(candidate));
        when(candidateRepository.save(candidate)).thenReturn(candidate);
        when(candidateMapper.toDto(candidate)).thenReturn(request);

        assertThat(service.update(4L, request)).isSameAs(request);
        assertThat(candidate.getFirstName()).isEqualTo("Ada");
        assertThat(candidate.getLocation()).isEqualTo("Tunis");
        assertThat(candidate.getAvailable()).isTrue();
        verify(candidateRepository).save(candidate);
    }

    @Test
    void reportsMissingCandidateForKeycloakIdAndDelete() {
        when(candidateRepository.findByKeycloakId("missing")).thenReturn(null);
        when(candidateRepository.findById(8L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.findByKeycloakId("missing"))
                .isInstanceOf(CandidateNotFoundException.class);
        assertThatThrownBy(() -> service.delete(8L))
                .isInstanceOf(CandidateNotFoundException.class);
    }

    private CandidateDto dto() {
        return CandidateDto.builder().keycloakId("kc-1").firstName("Ada").lastName("Lovelace")
                .email("ada@test.local").phone("22000000").location("Tunis").available(true)
                .linkedinUrl("https://linkedin.com/in/ada").githubUrl("https://github.com/ada").build();
    }
}
