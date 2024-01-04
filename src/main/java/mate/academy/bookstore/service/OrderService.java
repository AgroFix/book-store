package mate.academy.bookstore.service;

import java.util.List;
import mate.academy.bookstore.dto.order.OrderRequestDto;
import mate.academy.bookstore.dto.order.OrderResponseDto;
import mate.academy.bookstore.dto.order.UpdateOrderRequestDto;
import mate.academy.bookstore.model.User;

public interface OrderService {
    List<OrderResponseDto> getAllOrders(Long userId);

    OrderResponseDto placeOrder(User user, OrderRequestDto dto);

    OrderResponseDto updateOrder(Long orderId, UpdateOrderRequestDto requestDto);
}
