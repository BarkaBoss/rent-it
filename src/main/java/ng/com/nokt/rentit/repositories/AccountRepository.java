package ng.com.nokt.rentit.repositories;


import ng.com.nokt.rentit.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
    Optional<Account> findByEmailIgnoreCase(String email);
}
