package cl.streamlink.contact.repository;

import cl.streamlink.contact.domain.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LanguageRepository extends JpaRepository<Language, Long> {

    Optional<Language> findOneByReference(String reference);
}
