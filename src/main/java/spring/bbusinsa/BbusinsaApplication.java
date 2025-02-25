package spring.bbusinsa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableElasticsearchRepositories(basePackages = "spring.bbusinsa.product.infra.elasticSearch.domain")
public class BbusinsaApplication {

  public static void main(String[] args) {
    SpringApplication.run(BbusinsaApplication.class, args);
  }

}
