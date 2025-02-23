package spring.bbusinsa.product.infra.elasticSearch.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "product")
@Mapping(mappingPath = "/elasticsearch-mappings.json")
public class ProductDocument {

    @Id
    @Field(name = "productId", type = FieldType.Long)
    private Long productId;

    @Field(type = FieldType.Keyword) // text multi field
    private String name;

    @Field(type = FieldType.Integer)
    private int price;

    @Field(type = FieldType.Keyword)
    private String category;

    @Field(type = FieldType.Text)
    private String content;

    @Field(type = FieldType.Keyword)
    private String market;

}
