package cl.streamlink.contact.repository;

import cl.streamlink.contact.domain.TimeLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


public interface TimeLineRepository extends JpaRepository<TimeLine, Long> {

    Optional<TimeLine> findOneByReference(String reference);


    @Transactional
    long deleteByReference(String timeLineReference);


}
