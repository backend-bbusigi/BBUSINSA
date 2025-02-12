package spring.bbusinsa.coupon.domain.entitty;

import jakarta.persistence.*;
import lombok.Getter;
import spring.bbusinsa.global.base.BaseEntity;

import java.time.LocalDateTime;
import java.util.List;

@Entity @Getter
@Table(name = "coupons")
public class Coupon extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id", nullable = false)
    private Long couponId;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "discount_rate", nullable = false)
    private int discountRate;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name = "expired_at", nullable = false)
    private LocalDateTime expiredAt;

    @OneToMany(mappedBy = "coupon", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CouponProduct> couponProducts;

    @OneToMany(mappedBy = "coupon", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<IssuedCoupon> issuedCoupons;

}
