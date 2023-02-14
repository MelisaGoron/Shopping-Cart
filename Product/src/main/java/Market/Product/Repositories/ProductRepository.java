package Market.Product.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Market.Product.Entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
