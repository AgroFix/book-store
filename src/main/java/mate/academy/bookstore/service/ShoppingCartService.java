package mate.academy.bookstore.service;

import mate.academy.bookstore.dto.shopping.cart.ShoppingCartResponseDto;
import mate.academy.bookstore.dto.shopping.cart.item.CartItemRequestDto;
import mate.academy.bookstore.dto.shopping.cart.item.UpdateCartItemRequestDto;
import mate.academy.bookstore.model.User;

public interface ShoppingCartService {

    void createShoppingCart(User user);

    ShoppingCartResponseDto addToShoppingCart(CartItemRequestDto requestDto, Long userId);

    ShoppingCartResponseDto getShoppingCart(Long userId);

    ShoppingCartResponseDto updateShoppingCart(
            Long userId, Long id, UpdateCartItemRequestDto requestUpdateDto);

    void deleteCartItem(Long id);

}
