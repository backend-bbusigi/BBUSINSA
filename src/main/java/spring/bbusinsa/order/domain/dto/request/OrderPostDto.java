package spring.bbusinsa.order.domain.dto.request;

public record OrderPostDto(int price, Long memberId, Long productId, Long issuedCouponId) {
    // TODO: IssuedCoupon nullable
}
