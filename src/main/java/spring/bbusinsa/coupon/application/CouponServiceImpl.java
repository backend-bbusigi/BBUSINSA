package spring.bbusinsa.coupon.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.bbusinsa.coupon.domain.repository.CouponRepository;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;

}
