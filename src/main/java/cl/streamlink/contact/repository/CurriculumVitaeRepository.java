package cl.streamlink.contact.repository;

import cl.streamlink.contact.domain.CurriculumVitae;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;



public interface CurriculumVitaeRepository extends JpaRepository<CurriculumVitae, Long> {

    Optional<CurriculumVitae> findOneByReference(String reference);

    Optional<CurriculumVitae> findOneByReferenceAndDeveloperReference(String reference, String developerReference);

    List<CurriculumVitae> findByDeveloperReference(String developerReference);

    long countByDeveloperReference(String developerReference);

}
