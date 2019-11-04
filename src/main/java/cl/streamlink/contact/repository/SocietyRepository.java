package cl.streamlink.contact.repository;

import cl.streamlink.contact.domain.Society;
import cl.streamlink.contact.utils.enums.ActivityArea;
import cl.streamlink.contact.utils.enums.SocietyStage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SocietyRepository extends JpaRepository<Society, Long> {

    Optional<Society> findOneByReference(String reference);

    Page<Society> findByLabelContainingAndStageInAndActivityAreaIn
            (String value, List<SocietyStage> stages, List<ActivityArea> activityAreas, Pageable pageable);

    List<Society> findByLabelContaining(String value);
}
