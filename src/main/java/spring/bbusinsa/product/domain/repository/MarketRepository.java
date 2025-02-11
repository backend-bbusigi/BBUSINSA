package spring.bbusinsa.product.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.bbusinsa.product.domain.entity.Market;

@Repository
public interface MarketRepository extends JpaRepository<Market, Long> {
}
