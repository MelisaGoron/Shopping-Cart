package Market.Product.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Market.Product.Entities.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
	public Optional<Admin> findByEmail(String adminEmail);
}
