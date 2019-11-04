package cl.streamlink.contact.repository;

import cl.streamlink.contact.domain.Action;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ActionRepository extends JpaRepository<Action, Long> {

    Optional<Action> findOneByReference(String reference);

    Optional<Action> findOneByReferenceAndDeveloperReference(String reference, String developerReference);

    List<Action> findByDeveloperReference(String developerReference);

    Optional<Action> findOneByReferenceAndProjectReference(String reference, String projectReference);

    Optional<Action> findOneByReferenceAndNeedReference(String reference, String needReference);

    List<Action> findByProjectReference(String projectReference);

    List<Action> findByNeedReference(String needReference);

    Optional<Action> findOneByReferenceAndSocietyContactReferenceAndProjectIsNull(String reference, String societyContactReference);

    Optional<Action> findOneByReferenceAndSocietyContactSocietyReferenceAndProjectIsNull(String reference, String societyReference);

    List<Action> findBySocietyContactReferenceAndProjectIsNull(String societyContactReference);

    List<Action> findBySocietyContactSocietyReferenceAndProjectIsNull(String societyReference);


}
