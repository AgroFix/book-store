package mate.academy.bookstore.dto.order;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import mate.academy.bookstore.model.Order;

@Data
public class UpdateOrderRequestDto {
    @NotNull
    @Size(min = 1, max = 255)
    private Order.Status status;
}
