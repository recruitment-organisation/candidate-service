package recruitment.dev.candidateservice.entities;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "candidates")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Candidate {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, unique = true)
    private String keycloakId;


    private String firstName;


    private String lastName;


    @Column(unique = true)
    private String email;


    private String phone;


    private String location;


    private Boolean available;


    private String linkedinUrl;


    private String githubUrl;



}