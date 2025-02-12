package spring.bbusinsa.product.domain.dto.response.market;

import lombok.Builder;
import spring.bbusinsa.product.domain.entity.Market;

import java.util.List;
import java.util.stream.Collectors;

@Builder
public record MarketListDto(List<MarketDetailDto> marketDetailDtoList) {

    public static MarketListDto of(List<Market> markets) {
        return MarketListDto.builder()
                .marketDetailDtoList(markets.stream().
                        map(MarketDetailDto::of)
                        .collect(Collectors.toList()))
                .build();
    }

}
