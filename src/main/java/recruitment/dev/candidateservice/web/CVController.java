package recruitment.dev.candidateservice.web;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import recruitment.dev.candidateservice.dto.CVDto;
import recruitment.dev.candidateservice.service.CVService;
import org.springframework.core.io.Resource;
@RestController
@RequestMapping("/cv")
@RequiredArgsConstructor
public class CVController {

    private final CVService cvService;

    @PostMapping("/upload")
    public ResponseEntity<CVDto> uploadCV(
            @RequestParam("file") MultipartFile file,
            @RequestParam("candidateId") Long candidateId) {
        return ResponseEntity.ok(cvService.upload(candidateId, file));
    }
    @GetMapping("/get-by-candidateId")

    public ResponseEntity<Iterable<CVDto>> findByCandidateId(Long candidateId){
        return ResponseEntity.ok(cvService.findByCandidate(candidateId));
    }


    @GetMapping("/get-by-id")

    public ResponseEntity<CVDto> findById(Long id){
        return ResponseEntity.ok(cvService.findById(id));
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadCV(@RequestParam Long id) {
        Resource file = cvService.download(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"cv.pdf\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(file);
    }


}
