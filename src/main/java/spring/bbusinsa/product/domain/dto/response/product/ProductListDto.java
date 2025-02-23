package spring.bbusinsa.product.domain.dto.response.product;

import spring.bbusinsa.product.domain.entity.Product;
import spring.bbusinsa.product.infra.elasticSearch.domain.ProductDocument;

import java.util.List;

public record ProductListDto(List<ProductDetailDto> productDetailDtoList) {

    public static <T>ProductListDto of(List<T> productList) {
        return new ProductListDto(
            productList.stream()
                    .map(product -> {
                        if (product instanceof Product p) {
                            return ProductDetailDto.of(p);
                        } else if (product instanceof ProductDocument d) {
                            return ProductDetailDto.of(d);
                        }
                        throw new IllegalArgumentException("Unsupported type: " + product.getClass());
                    }).toList()
        );
    }
}
