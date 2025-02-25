package spring.bbusinsa.product.infra.elasticSearch.application;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring.bbusinsa.global.error.BbusinsaException;
import spring.bbusinsa.global.error.ErrorType;
import spring.bbusinsa.product.application.ProductSearchService;
import spring.bbusinsa.product.domain.dto.response.product.ProductListDto;
import spring.bbusinsa.product.domain.enums.ProductCategory;
import spring.bbusinsa.product.domain.repository.MarketRepository;
import spring.bbusinsa.product.infra.elasticSearch.domain.ProductDocument;
import spring.bbusinsa.product.infra.elasticSearch.domain.ProductSearchRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductSearchServiceImpl implements ProductSearchService {

    private final ProductSearchRepository productSearchRepository;
    private final MarketRepository marketRepository;
    private final ElasticsearchClient elasticsearchClient;

    @Override
    public ProductListDto searchProduct(String name, String category, String market) {
        List<Query> shouldQueries = Stream.of(
                buildMatchQuery("name", name),
                buildTermQuery("category.keyword", category),
                buildTermQuery("market.keyword", market)
        ).flatMap(Optional::stream).toList();

        BoolQuery boolQuery = shouldQueries.isEmpty()
                ? BoolQuery.of(b -> b.must(Query.of(q -> q.matchAll(m -> m))))
                : BoolQuery.of(b -> b.must(shouldQueries));

        SearchRequest searchRequest = SearchRequest.of(s -> s
                .index("product")
                .query(Query.of(q -> q.bool(boolQuery)))
        );

        try {
            SearchResponse<ProductDocument> response = elasticsearchClient.search(searchRequest, ProductDocument.class);
            List<ProductDocument> productDocuments =
                    response.hits().hits().stream()
                            .map(hit -> hit.source())
                            .collect(Collectors.toList());

            return ProductListDto.of(productDocuments);

        } catch (IOException e) {
            e.printStackTrace();
            throw new BbusinsaException(ErrorType.PRODUCT_SEARCH_INTERNAL_SERVER_ERROR);
        }
    }

    private Optional<Query> buildMatchQuery(String field, String value) {
        return (value == null || value.isEmpty()) ? Optional.empty()
                : Optional.of(Query.of(q -> q.match(m -> m.field(field).query(value))));
    }

    private Optional<Query> buildTermQuery(String field, String value) {
        if (value == null || value.isEmpty()) return Optional.empty();

        if ("market.keyword".equals(field) && marketRepository.findByName(value).isEmpty()) {
            throw new BbusinsaException(ErrorType.MARKET_NOT_FOUND);
        } else if ("category.keyword".equals(field)) {
            ProductCategory.valueOf(value);
        }

        return Optional.of(Query.of(q -> q.term(t -> t.field(field).value(FieldValue.of(value)))));
    }

    @Override
    public ProductListDto searchProductByPriceRange(int minPrice, int maxPrice) {
        List<ProductDocument> productDocuments = productSearchRepository.findByPriceIsBetween(minPrice, maxPrice);
        return ProductListDto.of(productDocuments);
    }
}
