package spring.bbusinsa.order.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.bbusinsa.global.base.BaseEntity;

@Builder
@Entity @Getter
@Table(name = "ordered_products")
@AllArgsConstructor
@NoArgsConstructor
public class OrderedProduct extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordered_product_id", nullable = false)
    private Long orderedProductId;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "issued_coupon_id", nullable = true)
    private Long issuedCouponId;

}
