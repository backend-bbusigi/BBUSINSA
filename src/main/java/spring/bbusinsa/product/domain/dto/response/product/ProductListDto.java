package spring.bbusinsa.product.domain.dto.response.product;

import lombok.Builder;
import spring.bbusinsa.product.domain.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

@Builder
public record ProductListDto(List<ProductDetailDto> productDetailDtoList) {

    public static ProductListDto of(List<Product> productList) {
        return ProductListDto.builder()
                .productDetailDtoList(productList.stream()
                        .map(ProductDetailDto::of)
                        .collect(Collectors.toList()))
                .build();
    }
}
