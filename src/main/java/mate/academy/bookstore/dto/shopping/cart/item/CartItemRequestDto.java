package mate.academy.bookstore.dto.shopping.cart.item;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CartItemRequestDto {
    @Positive
    @NotBlank
    private Long bookId;
    @Positive
    private Integer quantity;

}
