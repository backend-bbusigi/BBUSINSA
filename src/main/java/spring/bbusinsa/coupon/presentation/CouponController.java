package spring.bbusinsa.coupon.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.bbusinsa.coupon.application.CouponService;
import spring.bbusinsa.global.response.ApiResponse;

@RestController
@RequestMapping("/coupons")
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;

    @GetMapping("/claim/{couponId}")
    public ApiResponse<?> claim(@PathVariable Long couponId) {
        couponService.reduceCouponStock(couponId);
        return ApiResponse.success();
    }

    @GetMapping("/claim/lock/{couponId}")
    public ApiResponse<?> claimByLock(@PathVariable Long couponId) {
        couponService.reduceCouponStockWithLock(couponId);
        return ApiResponse.success();
    }
}
