package recruitment.dev.candidateservice.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import recruitment.dev.candidateservice.dto.CandidateDto;
import recruitment.dev.candidateservice.service.CandidateService;

@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
public class InternalCandidateController {

    private final CandidateService candidateService;

    @GetMapping("/{candidateId}")
    public ResponseEntity<CandidateDto> getCandidateById(
            @PathVariable Long candidateId
    ) {
        return ResponseEntity.ok(
                candidateService.findById(candidateId)
        );
    }
}
