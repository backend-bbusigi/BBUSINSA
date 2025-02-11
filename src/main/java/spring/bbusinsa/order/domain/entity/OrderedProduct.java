package spring.bbusinsa.order.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ordered_products")
public class OrderedProduct {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordered_product_id", nullable = false)
    private Long orderedProductId;

}
