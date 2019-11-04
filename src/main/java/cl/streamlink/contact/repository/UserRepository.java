package cl.streamlink.contact.repository;

import cl.streamlink.contact.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByEmail(String username);

    Optional<User> findOneByEmail(String username);

    Optional<User> findOneByReference(String reference);

    Page<User> findByFirstnameContainingOrLastnameContainingOrEmailContaining(String value, String value1, String value2, Pageable pageable);

    @Transactional
    void deleteByEmail(String username);

}
