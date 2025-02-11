package spring.bbusinsa.order.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.bbusinsa.order.domain.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
