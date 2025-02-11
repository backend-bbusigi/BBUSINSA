package spring.bbusinsa.coupon.domain.entitty;

import jakarta.persistence.*;
import lombok.Getter;

@Entity @Getter
@Table(name = "coupons")
public class Coupon {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id", nullable = false)
    private Long couponId;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "discount_rate", nullable = false)
    private int discountRate; // decimal 5,2

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

}
