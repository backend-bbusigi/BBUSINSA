package spring.bbusinsa.order.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.bbusinsa.global.error.BbusinsaException;
import spring.bbusinsa.global.error.ErrorType;
import spring.bbusinsa.order.domain.dto.request.OrderPostDto;
import spring.bbusinsa.order.domain.entity.Order;
import spring.bbusinsa.order.domain.entity.OrderedProduct;
import spring.bbusinsa.order.domain.enums.OrderStatus;
import spring.bbusinsa.order.domain.repository.OrderRepository;
import spring.bbusinsa.order.domain.repository.OrderedProductRepository;
import spring.bbusinsa.product.domain.entity.Product;
import spring.bbusinsa.product.domain.repository.ProductRepository;
import spring.bbusinsa.user.domain.entity.Member;
import spring.bbusinsa.user.domain.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderedProductRepository orderedProductRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    @Transactional
    @Override
    public void postOrder(OrderPostDto orderPostDto) {

        Member member = findMemberById(orderPostDto.memberId());
        Product product = findProductById(orderPostDto.productId());

        Order order = Order.builder()
                .status(OrderStatus.PAYMENT)
                .price(orderPostDto.price())
                .memberId(member.getMemberId())
                .build();

        OrderedProduct orderedProduct = OrderedProduct.builder()
                .order(order)
                .productId(product.getProductId())
                .issuedCouponId(orderPostDto.issuedCouponId()) // TODO: nullable
                .build();

        orderRepository.save(order);
        orderedProductRepository.save(orderedProduct);
    }

    private Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new BbusinsaException(ErrorType.MEMBER_NOT_FOUND));
    }

    private Product findProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new BbusinsaException(ErrorType.PRODUCT_NOT_FOUND));
    }

}
