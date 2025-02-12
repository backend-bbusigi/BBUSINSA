package spring.bbusinsa.product.domain.dto.request;

public record ProductPostDto(String name,
                             int price,
                             String category,
                             String content, String marketName) {
}
