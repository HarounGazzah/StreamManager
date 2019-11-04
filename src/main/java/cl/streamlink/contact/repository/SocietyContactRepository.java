package cl.streamlink.contact.repository;

import cl.streamlink.contact.domain.SocietyContact;
import cl.streamlink.contact.utils.enums.SocietyStage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface SocietyContactRepository extends JpaRepository<SocietyContact, Long> {

    Optional<SocietyContact> findOneByReference(String reference);

    List<SocietyContact> findBySocietyReference(String societyReference);

    Optional<SocietyContact> findOneByReferenceAndSocietyReference(String reference, String societyReference);

    Page<SocietyContact> findByFirstnameContainingAndStageInAndSocietyReferenceOrLastnameContainingAndStageInAndSocietyReferenceOrTitleContainingAndStageInAndSocietyReference
            (String value1, List<SocietyStage> stages1, String societyReference1,
             String value2, List<SocietyStage> stages2, String societyReference2,
             String value3, List<SocietyStage> stages3, String societyReference3,
             Pageable pageable);

    @Transactional
    long deleteBySocietyReference(String societyReference);


}