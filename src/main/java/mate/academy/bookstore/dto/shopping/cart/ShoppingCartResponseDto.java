package mate.academy.bookstore.dto.shopping.cart;

import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import mate.academy.bookstore.dto.shopping.cart.item.CartItemResponseDto;

@Data
public class ShoppingCartResponseDto {
    private Long id;
    private Long userId;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<CartItemResponseDto> cartItems;
}
