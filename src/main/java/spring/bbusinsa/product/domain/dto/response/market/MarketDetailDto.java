package spring.bbusinsa.product.domain.dto.response.market;

import spring.bbusinsa.product.domain.entity.Market;

public record MarketDetailDto(Long marketId, String name) {

    public static MarketDetailDto of(Market market) {
        return new MarketDetailDto(
                market.getMarketId(),
                market.getName()
        );
    }
}
