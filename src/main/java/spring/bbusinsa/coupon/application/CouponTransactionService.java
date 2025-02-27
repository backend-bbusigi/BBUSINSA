package spring.bbusinsa.coupon.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import spring.bbusinsa.coupon.domain.entitty.TestCoupon;
import spring.bbusinsa.coupon.domain.repository.TestCouponRepository;
import spring.bbusinsa.global.error.BbusinsaException;
import spring.bbusinsa.global.error.ErrorType;

@Service
@RequiredArgsConstructor
public class CouponTransactionService {

    private final TestCouponRepository testCouponRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void reduceCouponStockWithTransaction(Long couponId) {
        TestCoupon coupon = testCouponRepository.findById(couponId)
                .orElseThrow(() -> new BbusinsaException(ErrorType.COUPON_NOT_FOUND));

        coupon.decrease();
    }
}
