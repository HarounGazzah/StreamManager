package cl.streamlink.contact.repository;

import cl.streamlink.contact.domain.TimeList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


public interface TimeListRepository extends JpaRepository<TimeList, Long> {


    Optional<TimeList> findOneByReference(String reference);

    @Transactional
    long deleteByReference(String timeListReference);

}
