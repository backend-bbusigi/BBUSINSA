package spring.bbusinsa.product.application;

import spring.bbusinsa.product.domain.dto.response.product.ProductListDto;

public interface ProductSearchService {

    ProductListDto searchProductByName(String name);

}
