package spring.bbusinsa.product.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import spring.bbusinsa.product.domain.enums.ProductCategory;

@Entity @Getter
@Table(name = "products")
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "price", nullable = false) //decimal 14,4
    private int price;

    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductCategory category;

//    private json content;

}
