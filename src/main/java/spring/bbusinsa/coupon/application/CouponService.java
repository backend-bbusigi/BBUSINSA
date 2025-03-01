package spring.bbusinsa.coupon.application;

public interface CouponService {

    void reduceCouponStock(Long couponId);

    void reduceCouponStockWithDistributedLock(Long couponId);

    void reduceCouponStockWithSpinLock(Long couponId);
}
