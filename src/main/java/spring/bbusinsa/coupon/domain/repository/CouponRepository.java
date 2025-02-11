package spring.bbusinsa.coupon.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.bbusinsa.coupon.domain.entitty.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
