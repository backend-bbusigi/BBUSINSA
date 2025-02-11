package spring.bbusinsa.coupon.domain.entitty;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity @Getter
@Table(name = "issued_coupons")
public class IssuedCoupon {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "issued_coupon_id", nullable = false)
    private Long issuedCouponId;

    @Column(name = "is_used", nullable = false)
    private boolean isUsed;

    @Column(name = "used_at", nullable = true)
    private LocalDateTime usedAt;

    @Column(name = "expired_at", nullable = false)
    private LocalDateTime expiredAt;

}
