package spring.bbusinsa.product.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.bbusinsa.global.error.BbusinsaException;
import spring.bbusinsa.global.error.ErrorType;
import spring.bbusinsa.product.domain.dto.request.MarketPostDto;
import spring.bbusinsa.product.domain.dto.request.ProductPostDto;
import spring.bbusinsa.product.domain.dto.response.market.MarketDetailDto;
import spring.bbusinsa.product.domain.dto.response.market.MarketListDto;
import spring.bbusinsa.product.domain.dto.response.product.ProductDetailDto;
import spring.bbusinsa.product.domain.dto.response.product.ProductListDto;
import spring.bbusinsa.product.domain.entity.Market;
import spring.bbusinsa.product.domain.entity.Product;
import spring.bbusinsa.product.domain.enums.ProductCategory;
import spring.bbusinsa.product.domain.repository.MarketRepository;
import spring.bbusinsa.product.domain.repository.ProductRepository;
import spring.bbusinsa.product.infra.elasticSearch.domain.ProductDocument;
import spring.bbusinsa.product.infra.elasticSearch.domain.ProductSearchRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductSearchRepository productSearchRepository;
    private final MarketRepository marketRepository;

    @Transactional
    @Override
    public MarketDetailDto postMarket(MarketPostDto marketPostDto) {

        if (isExistingMarket(marketPostDto.name())) {
            throw new BbusinsaException(ErrorType.DUPLICATE_MARKET_NAME);
        }

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

        ProductDocument productDocs = ProductDocument.builder()
                .productId(product.getProductId())
                .price(product.getPrice())
                .name(product.getName())
                .category(product.getCategory().name())
                .content(product.getContent())
                .market(market.getName())
                .build();
        productSearchRepository.save(productDocs);

        return ProductDetailDto.of(product);
    }

    @Override
    public ProductDetailDto getProductDetail(Long productId) {
        Product product = findProductById(productId);
        return ProductDetailDto.of(product);
    }

    @Override
    public ProductListDto getProductListOfMarket(Long marketId) {
        Market market = findMarketById(marketId);
        List<Product> productList = market.getProducts();
        return ProductListDto.of(productList);
    }

    private Market findMarketById(Long marketId) {
        return marketRepository.findById(marketId)
                .orElseThrow(() -> new BbusinsaException(ErrorType.MARKET_NOT_FOUND));
    }

    private Market findMarketByName(String name) {
        return marketRepository.findByName(name)
                .orElseThrow(() -> new BbusinsaException(ErrorType.MARKET_NOT_FOUND));
    }

    private boolean isExistingMarket(String name) {
        return marketRepository.findByName(name).isPresent();
    }

    private Product findProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new BbusinsaException(ErrorType.PRODUCT_NOT_FOUND));
    }
}
