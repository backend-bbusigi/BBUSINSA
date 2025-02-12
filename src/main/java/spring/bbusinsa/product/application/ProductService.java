package spring.bbusinsa.product.application;

import spring.bbusinsa.product.domain.dto.request.MarketPostDto;
import spring.bbusinsa.product.domain.dto.request.ProductPostDto;
import spring.bbusinsa.product.domain.dto.response.MarketDetailDto;
import spring.bbusinsa.product.domain.dto.response.MarketListDto;
import spring.bbusinsa.product.domain.dto.response.ProductDetailDto;

public interface ProductService {
    MarketDetailDto postMarket(MarketPostDto marketPostDto);
    MarketListDto getMarketList();
    ProductDetailDto postProduct(ProductPostDto productPostDto);
    ProductDetailDto getProductDetail(Long productId);
}
