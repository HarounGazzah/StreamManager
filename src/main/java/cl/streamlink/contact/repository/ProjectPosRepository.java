package cl.streamlink.contact.repository;

import cl.streamlink.contact.domain.ProjectPos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectPosRepository extends JpaRepository<ProjectPos, Long> {

    boolean existsByReference(String reference);

    Optional<ProjectPos> findOneByReference(String reference);

    List<ProjectPos> findByNeedContaining(String value);

//    Page<Need> findByTitleContainingAndStageInAndTypeInAndNeedInformationActivityAreaIn
//            (String value, List<NeedStage> stages, List<NeedType> types,
//             List<ActivityArea> activityAreas, Pageable pageable);

    Page<ProjectPos> findByNeedTitleContaining(String value, Pageable pageable);



//    @Transactional
//    long deleteBySocietyContactReference(String societyContactReference);
//
//    @Transactional
//    long deleteBySocietyContactSocietyReference(String societyReference);
}
