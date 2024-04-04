package ng.com.nokt.rentit.repositories;

import ng.com.nokt.rentit.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
