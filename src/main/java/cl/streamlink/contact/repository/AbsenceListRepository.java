package cl.streamlink.contact.repository;

import cl.streamlink.contact.domain.Absence;
import cl.streamlink.contact.domain.AbsenceList;
import cl.streamlink.contact.domain.Action;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface AbsenceListRepository extends JpaRepository<AbsenceList, Long> {
    Optional<AbsenceList> findOneByReference(String reference);
    Optional<AbsenceList> findOneByManager(String reference);

    List<AbsenceList> findByManagerAndResource(String managerReference, String resourceReference);

}
