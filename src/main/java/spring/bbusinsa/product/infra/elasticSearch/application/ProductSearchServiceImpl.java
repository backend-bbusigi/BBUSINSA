package spring.bbusinsa.product.infra.elasticSearch.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.bbusinsa.product.application.ProductSearchService;
import spring.bbusinsa.product.domain.dto.response.product.ProductListDto;
import spring.bbusinsa.product.infra.elasticSearch.domain.ProductDocument;
import spring.bbusinsa.product.infra.elasticSearch.domain.ProductSearchRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductSearchServiceImpl implements ProductSearchService {

    private final ProductSearchRepository productSearchRepository;


    @Override
    public ProductListDto searchProductByName(String name) {
        List<ProductDocument> productDocuments = productSearchRepository.findByName(name);
        return ProductListDto.of(productDocuments);
    }

    public ProductListDto searchProductByCategory(String category) {
        List<ProductDocument> productDocuments = productSearchRepository.findByCategory(category);
        return ProductListDto.of(productDocuments);
    }

    public ProductListDto searchProductByMarket(String market) {
        List<ProductDocument> productDocuments = productSearchRepository.findByMarket(market);
        return ProductListDto.of(productDocuments);
    }

    public ProductListDto searchProductByPriceRange(int minPrice, int maxPrice) {
        List<ProductDocument> productDocuments = productSearchRepository.findByPriceIsBetween(minPrice, maxPrice);
        return ProductListDto.of(productDocuments);
    }
}
