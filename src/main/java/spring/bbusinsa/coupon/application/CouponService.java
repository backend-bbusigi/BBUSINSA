package spring.bbusinsa.coupon.application;

public interface CouponService {

    void reduceCouponStock(Long couponId);

    void reduceCouponStockWithLock(Long couponId);
}
