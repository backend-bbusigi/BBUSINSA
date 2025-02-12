package spring.bbusinsa.product.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.bbusinsa.global.base.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Builder
@Entity @Getter
@Table(name = "markets")
@AllArgsConstructor
@NoArgsConstructor
public class Market extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "market_id", nullable = false)
    private Long marketId;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @OneToMany(mappedBy = "market", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();

}
