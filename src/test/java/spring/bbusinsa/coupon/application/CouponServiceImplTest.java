package spring.bbusinsa.coupon.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.bbusinsa.coupon.domain.entitty.TestCoupon;
import spring.bbusinsa.coupon.domain.repository.TestCouponRepository;
import spring.bbusinsa.global.error.BbusinsaException;
import spring.bbusinsa.global.error.ErrorType;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CouponServiceImplTest {

    @Autowired
    TestCouponRepository testCouponRepository;

    @Autowired
    CouponService couponService;

    TestCoupon coupon;

    @BeforeEach
    void setUp() {
        coupon = new TestCoupon("BBUSINSA_001", 100L);
        testCouponRepository.save(coupon);
    }

    @Test
    void 쿠폰차감_분산락_미적용_동시성100명_테스트() throws InterruptedException {
        int numberOfThreads = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(() -> {
                try {
                    couponService.reduceCouponStock(coupon.getId());
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        TestCoupon persistCoupon = testCouponRepository.findById(coupon.getId())
                .orElseThrow(() -> new BbusinsaException(ErrorType.COUPON_NOT_FOUND));

        assertThat(persistCoupon.getAvailableStock()).isZero();
        System.out.println("잔여 쿠폰 갯수 = " + persistCoupon.getAvailableStock());
    }

    @Test
    void 분산락_쿠폰차감_동시성100명_테스트() throws InterruptedException {
        int numberOfThreads = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(() -> {
                try {
                    // 분산락 적용 메서드 호출 (락의 key는 쿠폰의 id로 설정)
                    couponService.reduceCouponStockWithDistributedLock(coupon.getId());
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        TestCoupon persistCoupon = testCouponRepository.findById(coupon.getId())
                .orElseThrow(() -> new BbusinsaException(ErrorType.COUPON_NOT_FOUND));

        assertThat(persistCoupon.getAvailableStock()).isZero();
        System.out.println("잔여 쿠폰 갯수 = " + persistCoupon.getAvailableStock());
    }

    @Test
    void 스핀락_쿠폰차감_동시성100명_테스트() throws InterruptedException {
        int numberOfThreads = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(() -> {
                try {
                    // 스핀락 적용 메서드 호출 (락의 key는 쿠폰의 id로 설정)
                    couponService.reduceCouponStockWithSpinLock(coupon.getId());
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        TestCoupon persistCoupon = testCouponRepository.findById(coupon.getId())
                .orElseThrow(() -> new BbusinsaException(ErrorType.COUPON_NOT_FOUND));

        assertThat(persistCoupon.getAvailableStock()).isZero();
        System.out.println("잔여 쿠폰 갯수 = " + persistCoupon.getAvailableStock());
    }
}
