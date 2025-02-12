package spring.bbusinsa.product.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.bbusinsa.global.error.BbusinsaException;
import spring.bbusinsa.global.error.ErrorType;
import spring.bbusinsa.product.domain.dto.request.MarketPostDto;
import spring.bbusinsa.product.domain.dto.request.ProductPostDto;
import spring.bbusinsa.product.domain.dto.response.MarketDetailDto;
import spring.bbusinsa.product.domain.dto.response.MarketListDto;
import spring.bbusinsa.product.domain.dto.response.ProductDetailDto;
import spring.bbusinsa.product.domain.entity.Market;
import spring.bbusinsa.product.domain.entity.Product;
import spring.bbusinsa.product.domain.enums.ProductCategory;
import spring.bbusinsa.product.domain.repository.MarketRepository;
import spring.bbusinsa.product.domain.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final MarketRepository marketRepository;

    @Transactional
    @Override
    public MarketDetailDto postMarket(MarketPostDto marketPostDto) {

        Market market = Market.builder()
                .name(marketPostDto.name())
                .build();

        marketRepository.save(market);

        return MarketDetailDto.of(market);
    }

    @Override
    public MarketListDto getMarketList() {
        return MarketListDto.of(marketRepository.findAll());
    }

    @Transactional
    @Override
    public ProductDetailDto postProduct(ProductPostDto productPostDto) {

        Market market = findMarketByName(productPostDto.marketName());

        Product product = Product.builder()
                .name(productPostDto.name())
                .price(productPostDto.price())
                .category(ProductCategory.getName(productPostDto.category()))
                .content(productPostDto.content())
                .market(market)
                .build();

        productRepository.save(product);

        return ProductDetailDto.of(product);
    }

    @Override
    public ProductDetailDto getProductDetail(Long productId) {
        Product product = findProductById(productId);
        return ProductDetailDto.of(product);
    }

    private Market findMarketByName(String name) {
        return marketRepository.findByName(name)
                .orElseThrow(() -> new BbusinsaException(ErrorType.MARKET_NOT_FOUND));
    }

    private Product findProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new BbusinsaException(ErrorType.PRODUCT_NOT_FOUND));
    }
}
