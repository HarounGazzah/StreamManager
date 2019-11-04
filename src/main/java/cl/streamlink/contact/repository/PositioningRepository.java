package cl.streamlink.contact.repository;

import cl.streamlink.contact.domain.Positioning;
import cl.streamlink.contact.utils.enums.NeedStage;
import cl.streamlink.contact.utils.enums.NeedType;
import cl.streamlink.contact.utils.enums.PositioningStage;
import cl.streamlink.contact.utils.enums.ResourceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PositioningRepository extends JpaRepository<Positioning, Long> {

     Optional<Positioning> findOneByReference(String reference);

//    List<Positioning> findByProjectReference(String projectReference);

    List<Positioning> findByNeedReference(String needReference);

    List<Positioning> findByResourceReference(String ressourceReference);

//    Page<Positioning> findByProjectTitleContainingAndProjectStageInAndProjectTypeInAndResourceResourceTypeInAndStageIn
//            (String value, 
//            List<ProjectStage> projectStages, 
//            List<ProjectType> projectTypes,
//            List<ResourceType> resourceTypes, 
//            List<PositioningStage> stages, 
//            Pageable pageable);

    Page<Positioning> findByNeedTitleContainingAndNeedStageInAndNeedTypeInAndResourceResourceTypeInAndStageIn
            (String value,
             List<NeedStage> needStages,
             List<NeedType> needTypes,
             List<ResourceType> resourceTypes,
             List<PositioningStage> stages,
             Pageable pageable);
}
