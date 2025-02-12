package spring.bbusinsa.order.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import spring.bbusinsa.global.base.BaseEntity;
import spring.bbusinsa.order.domain.enums.OrderStatus;

@Entity @Getter
@Table(name = "orders")
public class Order extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "member_id", nullable = false)
    private Long memberId;
}
