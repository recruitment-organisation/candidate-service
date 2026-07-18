package recruitment.dev.candidateservice.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import java.util.List;


@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateDto {


    private Long id;


    private String keycloakId;

    @NotBlank(message = "Le prénom est obligatoire")
    @Size(max = 50, message = "Le prénom ne doit pas dépasser 50 caractères")
    private String firstName;


    @NotBlank(message = "Le nom est obligatoire")
    @Size(max = 50, message = "Le nom ne doit pas dépasser 50 caractères")
    private String lastName;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Format d'email invalide")
    private String email;


    @Pattern(
            regexp = "^\\+?[0-9]{8,15}$",
            message = "Numéro de téléphone invalide"
    )

    private String phone;

    @Size(max = 100, message = "La localisation ne doit pas dépasser 100 caractères")

    private String location;


    private Boolean available;

    @URL(message = "URL LinkedIn invalide")

    private String linkedinUrl;

    @URL(message = "URL GitHub invalide")

    private String githubUrl;


    private CVDto cv;
}