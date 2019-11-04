package cl.streamlink.contact.repository;

import cl.streamlink.contact.domain.Project;
import cl.streamlink.contact.utils.enums.ActivityArea;
import cl.streamlink.contact.utils.enums.ProjectStage;
import cl.streamlink.contact.utils.enums.ProjectType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    boolean existsByReference(String reference);

    Optional<Project> findOneByReference(String reference);

    List<Project> findByTitleContaining(String value);


    Page<Project> findByTitleContainingAndStageInAndTypeInAndProjectInformationActivityAreaIn
            (String value, List<ProjectStage> stages, List<ProjectType> types,
             List<ActivityArea> activityAreas, Pageable pageable);

    @Transactional
    long deleteBySocietyContactReference(String societyContactReference);

    @Transactional
    long deleteBySocietyContactSocietyReference(String societyReference);

}