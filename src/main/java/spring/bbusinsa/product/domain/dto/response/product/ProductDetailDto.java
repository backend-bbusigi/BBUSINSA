package spring.bbusinsa.product.domain.dto.response.product;

import spring.bbusinsa.product.domain.entity.Product;
import spring.bbusinsa.product.infra.elasticSearch.domain.ProductDocument;

public record ProductDetailDto(Long productId,
                               String marketName,
                               String productName,
                               int price,
                               String category,
                               String content) {

    public static ProductDetailDto of(Product product) {
        return new ProductDetailDto(
                product.getProductId(),
                product.getMarket().getName(),
                product.getName(),
                product.getPrice(),
                product.getCategory().name(),
                product.getContent()
        );
    }

    public static ProductDetailDto of(ProductDocument productDocument) {
        return new ProductDetailDto(
                productDocument.getId(),
                productDocument.getMarket(),
                productDocument.getName(),
                productDocument.getPrice(),
                productDocument.getCategory(),
                productDocument.getContent()
        );
    }
}
