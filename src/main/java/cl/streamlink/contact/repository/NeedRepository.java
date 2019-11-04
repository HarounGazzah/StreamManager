package cl.streamlink.contact.repository;

import cl.streamlink.contact.domain.Need;
import cl.streamlink.contact.utils.enums.ActivityArea;
import cl.streamlink.contact.utils.enums.NeedStage;
import cl.streamlink.contact.utils.enums.NeedType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface NeedRepository extends JpaRepository<Need, Long> {

    Optional<Need> findOneByReference(String reference);

    List<Need> findByTitleContaining(String value);


    Page<Need> findByTitleContainingAndStageInAndTypeInAndNeedInformationActivityAreaIn
            (String value, List<NeedStage> stages, List<NeedType> types,
             List<ActivityArea> activityAreas, Pageable pageable);

    @Transactional
    long deleteBySocietyContactReference(String societyContactReference);

    @Transactional
    long deleteBySocietyContactSocietyReference(String societyReference);
}
