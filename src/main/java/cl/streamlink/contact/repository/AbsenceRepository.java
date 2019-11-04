package cl.streamlink.contact.repository;

import cl.streamlink.contact.domain.Absence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface AbsenceRepository extends JpaRepository<Absence, Long> {


    Optional<Absence> findOneByReference(String reference);
    Optional<Absence> findOneByAbsenceList(String reference);
    List<Absence> findByAbsenceListManagerReferenceAndAbsenceListResourceReference(String managerReference, String resourceReference);
    List<Absence> findByAbsenceListManagerReference(String managerReference);

//to do
    List<Absence> findByAbsenceList_Reference(String ref);
}
