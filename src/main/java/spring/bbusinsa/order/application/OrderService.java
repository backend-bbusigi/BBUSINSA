package spring.bbusinsa.order.application;

import spring.bbusinsa.order.domain.dto.request.OrderPostDto;

public interface OrderService {
    void postOrder(OrderPostDto orderPostDto);
}
