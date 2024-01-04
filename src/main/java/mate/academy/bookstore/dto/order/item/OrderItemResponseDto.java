package mate.academy.bookstore.dto.order.item;

import lombok.Data;

@Data
public class OrderItemResponseDto {
    private Long id;
    private Long bookId;
    private Integer quantity;
}
