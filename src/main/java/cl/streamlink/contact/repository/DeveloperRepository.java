package cl.streamlink.contact.repository;

import cl.streamlink.contact.domain.Contact;
import cl.streamlink.contact.domain.Developer;
import cl.streamlink.contact.domain.Resource;
import cl.streamlink.contact.utils.enums.Experience;
import cl.streamlink.contact.utils.enums.Formation;
import cl.streamlink.contact.utils.enums.Stage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeveloperRepository extends JpaRepository<Developer, Long> {

    Optional<Developer> findOneByReference(String reference);
    Optional<Developer> findByContact_Email1 (String email);
    List<Developer> findByManagerReference(String managerReference);


    Page<Developer> findByFirstnameContainingAndStageInAndSkillsInformationFormationInAndSkillsInformationExperienceInOrLastnameContainingAndStageInAndSkillsInformationFormationInAndSkillsInformationExperienceInOrSkillsInformationTitleContainingAndStageInAndSkillsInformationFormationInAndSkillsInformationExperienceInOrSkillsInformationLanguagesContainingAndStageInAndSkillsInformationFormationInAndSkillsInformationExperienceIn
            (String value, List<Stage> stages, List<Formation> formations, List<Experience> experiences,
             String value1, List<Stage> stages1, List<Formation> formations1, List<Experience> experiences1,
             String value2, List<Stage> stages2, List<Formation> formations2, List<Experience> experiences2,
             String value3, List<Stage> stages3, List<Formation> formations3, List<Experience> experiences3,
             Pageable pageable);
}
