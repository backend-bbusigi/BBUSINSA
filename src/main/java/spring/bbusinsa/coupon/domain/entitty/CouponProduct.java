package spring.bbusinsa.coupon.domain.entitty;

import jakarta.persistence.*;
import lombok.Getter;
import spring.bbusinsa.global.base.BaseEntity;

@Entity @Getter
@Table(name = "coupon_products")
public class CouponProduct extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_product_id", nullable = false)
    private Long couponProductId;

    @Column(name = "coupon_id", nullable = false)
    private Long couponId;

    @Column(name = "product_id", nullable = false)
    private Long productId;

}
