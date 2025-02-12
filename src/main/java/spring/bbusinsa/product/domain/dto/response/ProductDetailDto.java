package spring.bbusinsa.product.domain.dto.response;

import lombok.Builder;
import spring.bbusinsa.product.domain.entity.Product;

@Builder
public record ProductDetailDto(Long productId,
                               String marketName,
                               String productName,
                               int price,
                               String category,
                               String content) {

    public static ProductDetailDto of(Product product) {
        return ProductDetailDto.builder()
                .productId(product.getProductId())
                .marketName(product.getMarket().getName())
                .productName(product.getName())
                .price(product.getPrice())
                .category(product.getCategory().name())
                .content(product.getContent())
                .build();
    }
}
