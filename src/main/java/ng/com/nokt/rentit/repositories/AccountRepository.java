package ng.com.nokt.rentit.repositories;


import ng.com.nokt.rentit.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
}
