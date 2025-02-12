package spring.bbusinsa.product.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.bbusinsa.product.domain.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
