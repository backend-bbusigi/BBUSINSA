package spring.bbusinsa.coupon.domain.entitty;

import jakarta.persistence.*;
import lombok.Getter;

@Entity @Getter
@Table(name = "coupon_products")
public class CouponProduct {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_product_id", nullable = false)
    private Long couponProductId;

}
