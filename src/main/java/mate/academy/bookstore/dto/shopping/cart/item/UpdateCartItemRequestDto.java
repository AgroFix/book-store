package mate.academy.bookstore.dto.shopping.cart.item;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class UpdateCartItemRequestDto {
    @Positive
    private Integer quantity;
}
