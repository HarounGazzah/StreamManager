package cl.streamlink.contact.repository;

import cl.streamlink.contact.domain.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

    Optional<Photo> findOneByReference(String reference);

    Optional<Photo> findOneByUserReference(String userReference);

    Optional<Photo> findOneByReferenceAndUserReference(String reference, String userReference);

    List<Photo> findByUserReference(String userReference);

    long countByUserReference(String userReference);
}
