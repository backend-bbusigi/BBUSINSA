package spring.bbusinsa.order.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity @Getter
@Table(name = "ordered_products")
public class OrderedProduct {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordered_product_id", nullable = false)
    private Long orderedProductId;

    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "issued_coupon_id", nullable = true)
    private Long issuedCouponId;

}
