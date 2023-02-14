package Market.Product.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Market.Product.Entities.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

}
