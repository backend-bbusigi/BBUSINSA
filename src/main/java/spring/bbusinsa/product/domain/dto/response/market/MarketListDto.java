package spring.bbusinsa.product.domain.dto.response.market;

import spring.bbusinsa.product.domain.entity.Market;

import java.util.List;

public record MarketListDto(List<MarketDetailDto> marketDetailDtoList) {

    public static MarketListDto of(List<Market> markets) {
        return new MarketListDto(
                markets.stream().
                        map(MarketDetailDto::of)
                        .toList()
        );
    }
}
