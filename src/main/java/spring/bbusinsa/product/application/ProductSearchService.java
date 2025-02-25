package spring.bbusinsa.product.application;

import spring.bbusinsa.product.domain.dto.response.product.ProductListDto;

public interface ProductSearchService {

    ProductListDto searchProduct(String name, String category, String market);
    ProductListDto searchProductByPriceRange(int minPrice, int maxPrice);

    }
