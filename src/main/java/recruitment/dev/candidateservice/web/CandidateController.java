package recruitment.dev.candidateservice.web;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recruitment.dev.candidateservice.dto.CandidateDto;
import recruitment.dev.candidateservice.repositories.CandidateRepository;
import recruitment.dev.candidateservice.service.CandidateService;

@RestController
@RequestMapping("/candidate")
@RequiredArgsConstructor
public class CandidateController {
    private final CandidateService candidateService;


    @PostMapping("/create")
    public ResponseEntity <CandidateDto> createCandidate(@Valid @RequestBody CandidateDto dto) {
        CandidateDto candidateDto = candidateService.create(dto);
        return ResponseEntity.ok(candidateDto);
    }



    @PutMapping("/update/{id}")
    public ResponseEntity <CandidateDto> updateCandidate( @PathVariable Long id,
                                                          @Valid @RequestBody CandidateDto dto) {
        CandidateDto candidateDto = candidateService.update(id, dto);
        return ResponseEntity.ok(candidateDto);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCandidate(@PathVariable Long id) {
        candidateService.delete(id);
        return ResponseEntity.noContent().build(); // 204
    }
    @GetMapping("/get/{id}")
    public ResponseEntity <CandidateDto> findCandidateById(@PathVariable Long id) {
        CandidateDto candidateDto = candidateService.findById(id);
        return ResponseEntity.ok(candidateDto);
    }

    @GetMapping("/getkeycloakId")

    public ResponseEntity <CandidateDto> findCandidateByKeycloakId(@RequestParam String keycloakId) {
        CandidateDto candidateDto = candidateService.findByKeycloakId(keycloakId);
        return ResponseEntity.ok(candidateDto);
    }

    @GetMapping("/get-all")

    public ResponseEntity<Page<CandidateDto>> findAllCandidates(  @RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "10") int size) {
        Page<CandidateDto> candidateDtoPage = candidateService.findAll(Pageable.ofSize(size).withPage(page));
        return ResponseEntity.ok(candidateDtoPage);

    }






}
