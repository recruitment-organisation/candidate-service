package recruitment.dev.candidateservice.mapper;



import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import recruitment.dev.candidateservice.dto.CandidateDto;
import recruitment.dev.candidateservice.entities.Candidate;


@Mapper(
        componentModel = "spring",
        uses = CVMapper.class
)
public interface CandidateMapper {



    @Mapping(source = "id", target = "id")
    @Mapping(source = "keycloakId", target = "keycloakId")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "location", target = "location")
    @Mapping(source = "available", target = "available")
    @Mapping(source = "linkedinUrl", target = "linkedinUrl")
    @Mapping(source = "githubUrl", target = "githubUrl")
    @Mapping(source = "cv", target = "cv")
    CandidateDto toDto(Candidate candidate);



    @Mapping(source = "id", target = "id")
    @Mapping(source = "keycloakId", target = "keycloakId")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "location", target = "location")
    @Mapping(source = "available", target = "available")
    @Mapping(source = "linkedinUrl", target = "linkedinUrl")
    @Mapping(source = "githubUrl", target = "githubUrl")
    @Mapping(source = "cv", target = "cv")
    Candidate toEntity(CandidateDto dto);

}