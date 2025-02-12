package spring.bbusinsa.product.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import spring.bbusinsa.product.domain.enums.MarketCategory;

@Entity @Getter
@Table(name = "markets")
public class Market {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "market_id", nullable = false)
    private Long marketId;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    private MarketCategory category;

}
