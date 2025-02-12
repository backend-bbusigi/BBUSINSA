package spring.bbusinsa.product.application;

import spring.bbusinsa.product.domain.dto.request.MarketPostDto;
import spring.bbusinsa.product.domain.dto.request.ProductPostDto;
import spring.bbusinsa.product.domain.dto.response.market.MarketDetailDto;
import spring.bbusinsa.product.domain.dto.response.market.MarketListDto;
import spring.bbusinsa.product.domain.dto.response.product.ProductDetailDto;
import spring.bbusinsa.product.domain.dto.response.product.ProductListDto;

public interface ProductService {
    MarketDetailDto postMarket(MarketPostDto marketPostDto);
    MarketListDto getMarketList();
    ProductDetailDto postProduct(ProductPostDto productPostDto);
    ProductDetailDto getProductDetail(Long productId);
    ProductListDto getProductListOfMarket(Long marketId);
}
