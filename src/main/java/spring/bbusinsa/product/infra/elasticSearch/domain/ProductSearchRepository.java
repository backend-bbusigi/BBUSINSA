package spring.bbusinsa.product.infra.elasticSearch.domain;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductSearchRepository extends ElasticsearchRepository<ProductDocument, Long> {

    List<ProductDocument> findByName(String name);
    List<ProductDocument> findByCategory(String category);
    List<ProductDocument> findByMarket(String market);
    List<ProductDocument> findByPriceIsBetween(int min, int max);

}
