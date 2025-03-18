package spring.bbusinsa.coupon.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.bbusinsa.coupon.domain.entitty.TestCoupon;
import spring.bbusinsa.coupon.domain.repository.TestCouponRepository;
import spring.bbusinsa.global.error.BbusinsaException;
import spring.bbusinsa.global.error.ErrorType;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Log4j2
public class CouponServiceImpl implements CouponService {

    private final TestCouponRepository testCouponRepository;
    private final CouponTransactionService couponTransactionService;

    private final RedissonClient redissonClient;

    @Override
    @Transactional
    public void reduceCouponStock(Long couponId) {
        log.info("🚀 [START] 쿠폰 차감 요청: couponId={}, thread={}", couponId, Thread.currentThread().getName());

        TestCoupon coupon = testCouponRepository.findById(couponId)
                .orElseThrow(() -> {
                    log.error("❌ [ERROR] 쿠폰 조회 실패: couponId={}", couponId);
                    return new BbusinsaException(ErrorType.COUPON_NOT_FOUND);
                });

        log.info("✅ [SUCCESS] 쿠폰 조회 완료: couponId={}, 현재 재고={}", couponId, coupon.getAvailableStock());

        coupon.decrease();

        log.info("📉 [UPDATE] 쿠폰 차감 완료: couponId={}, 차감 후 재고={}", couponId, coupon.getAvailableStock());

        log.info("🎯 [END] 트랜잭션 종료: couponId={}", couponId);
    }

    @Override
    public void reduceCouponStockWithLock(Long couponId) {
        RLock lock = redissonClient.getLock("coupon:" + couponId);
        boolean isLocked = false;

        try {
            log.info("🔐 [쿠폰 차감] 쿠폰 ID: {} - 락 획득 시도 중...", couponId);
            isLocked = lock.tryLock(5L, 10L, TimeUnit.SECONDS);

            if (!isLocked) {
                log.warn("🚨 [쿠폰 차감] 쿠폰 ID: {} - 락 획득 실패: 다른 스레드가 이미 사용 중", couponId);
                return;
            }

            log.info("✅ [쿠폰 차감] 쿠폰 ID: {} - 락 획득 성공! 처리 시작", couponId);
            couponTransactionService.reduceCouponStockWithTransaction(couponId);
            log.info("🎉 [쿠폰 차감] 쿠폰 ID: {} - 차감 완료!", couponId);

        } catch (InterruptedException e) {
            log.error("❌ [쿠폰 차감] 쿠폰 ID: {} - 락 획득 중 인터럽트 발생!", couponId, e);
            throw new BbusinsaException(ErrorType.LOCK_ACQUISITION_FAILED);
        } finally {
            if (isLocked && lock.isHeldByCurrentThread()) {
                lock.unlock();
                log.info("🔓 [쿠폰 차감] 쿠폰 ID: {} - 락 해제 완료", couponId);
            }
        }
    }
}
