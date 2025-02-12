package spring.bbusinsa.product.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import spring.bbusinsa.global.base.BaseEntity;
import spring.bbusinsa.product.domain.enums.ProductCategory;

@Entity @Getter
@Table(name = "products")
public class Product extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    @Column(name = "content", nullable = false, length = 500)
    private String content;

    @Column(name = "market_id", nullable = false)
    private Long marketId;

}
