package spring.bbusinsa.product.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring.bbusinsa.global.response.ApiResponse;
import spring.bbusinsa.product.application.ProductService;
import spring.bbusinsa.product.domain.dto.request.MarketPostDto;
import spring.bbusinsa.product.domain.dto.request.ProductPostDto;
import spring.bbusinsa.product.domain.dto.response.MarketDetailDto;
import spring.bbusinsa.product.domain.dto.response.MarketListDto;
import spring.bbusinsa.product.domain.dto.response.ProductDetailDto;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

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

}
