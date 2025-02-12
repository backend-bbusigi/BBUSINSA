package spring.bbusinsa.order.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.bbusinsa.order.domain.repository.OrderRepository;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

}
