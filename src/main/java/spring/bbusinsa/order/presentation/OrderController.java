package spring.bbusinsa.order.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.bbusinsa.global.response.ApiResponse;
import spring.bbusinsa.order.application.OrderService;
import spring.bbusinsa.order.domain.dto.request.OrderPostDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("")
    public ApiResponse postOrder(
            @RequestBody OrderPostDto orderPostDto
    ) {
        orderService.postOrder(orderPostDto);
        return ApiResponse.success();
    }
}
