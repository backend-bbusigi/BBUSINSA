package spring.bbusinsa.product.domain.dto.response.market;

import lombok.Builder;
import spring.bbusinsa.product.domain.entity.Market;

@Builder
public record MarketDetailDto(Long marketId, String name) {

    public static MarketDetailDto of(Market market) {
        return MarketDetailDto.builder()
                .marketId(market.getMarketId())
                .name(market.getName())
                .build();
    }
}
