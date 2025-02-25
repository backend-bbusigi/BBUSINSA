package spring.bbusinsa.product.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring.bbusinsa.global.response.ApiResponse;
import spring.bbusinsa.product.application.ProductSearchService;
import spring.bbusinsa.product.application.ProductService;
import spring.bbusinsa.product.domain.dto.request.MarketPostDto;
import spring.bbusinsa.product.domain.dto.request.ProductPostDto;
import spring.bbusinsa.product.domain.dto.response.market.MarketDetailDto;
import spring.bbusinsa.product.domain.dto.response.market.MarketListDto;
import spring.bbusinsa.product.domain.dto.response.product.ProductDetailDto;
import spring.bbusinsa.product.domain.dto.response.product.ProductListDto;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductSearchService productSearchService;

    @PostMapping("/markets")
    public ApiResponse<MarketDetailDto> postMarket(
            @RequestBody MarketPostDto marketPostDto
    ) {
        return ApiResponse.success(productService.postMarket(marketPostDto));
    }

    @GetMapping("/markets")
    public ApiResponse<MarketListDto> getMarkets() {
        return ApiResponse.success(productService.getMarketList());
    }

    @PostMapping("")
    public ApiResponse<ProductDetailDto> postProduct(
            @RequestBody ProductPostDto productPostDto
    ) {
        return ApiResponse.success(productService.postProduct(productPostDto));
    }

    @GetMapping("/{productId}")
    public ApiResponse<ProductDetailDto> getProductDetail(
            @PathVariable(name = "productId") Long productId
    ) {
        return ApiResponse.success(productService.getProductDetail(productId));
    }

    @GetMapping("/{marketId}")
    public ApiResponse<ProductListDto> getProductListOfMarket(
            @PathVariable(name = "marketId") Long marketId
    ) {
        return ApiResponse.success(productService.getProductListOfMarket(marketId));
    }

    @GetMapping("/search")
    public ApiResponse<ProductListDto> searchProducts(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "category", required = false) String category,
            @RequestParam(name = "market", required = false) String market
    ) {
        return ApiResponse.success(productSearchService.searchProduct(name, category, market));
    }

}
