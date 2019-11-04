package cl.streamlink.contact.repository;

import cl.streamlink.contact.domain.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContractRepository extends JpaRepository<Contract, Long> {

    Optional<Contract> findOneByReference(String reference);

    Optional<Contract> findOneByDeveloperReference(String developerReference);

}
