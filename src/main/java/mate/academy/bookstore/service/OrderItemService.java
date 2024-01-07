package mate.academy.bookstore.service;

import java.util.List;
import mate.academy.bookstore.dto.order.item.OrderItemResponseDto;

public interface OrderItemService {
    List<OrderItemResponseDto> getAllOrderItemsById(Long orderId);

    OrderItemResponseDto getItemFromOrderById(Long userId, Long orderId, Long itemId);
}
