package mate.academy.bookstore.service.impl;

import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.shopping.cart.ShoppingCartResponseDto;
import mate.academy.bookstore.dto.shopping.cart.item.CartItemRequestDto;
import mate.academy.bookstore.dto.shopping.cart.item.UpdateCartItemRequestDto;
import mate.academy.bookstore.exception.EntityNotFoundException;
import mate.academy.bookstore.mapper.CartItemMapper;
import mate.academy.bookstore.mapper.ShoppingCartMapper;
import mate.academy.bookstore.model.CartItem;
import mate.academy.bookstore.model.ShoppingCart;
import mate.academy.bookstore.model.User;
import mate.academy.bookstore.repository.CartItemRepository;
import mate.academy.bookstore.repository.ShoppingCartRepository;
import mate.academy.bookstore.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private static final String UPDATE_ERROR_MSG = "Can't update cart item by id: ";
    private static final String CANT_FIND_SHOPPING_CART_MSG
            = "Can't find shopping cart by user id: ";
    private final ShoppingCartRepository shoppingCartRepository;
    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;
    private final ShoppingCartMapper shoppingCartMapper;

    @Override
    public void createShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCartResponseDto addToShoppingCart(CartItemRequestDto requestDto, Long userId) {
        ShoppingCart shoppingCart = findByUserId(userId);
        CartItem cartItem = cartItemMapper.toModel(requestDto);
        cartItem.setShoppingCart(shoppingCart);
        cartItemRepository.save(cartItem);
        shoppingCart = findByUserId(userId);
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    public ShoppingCartResponseDto getShoppingCart(Long userId) {
        ShoppingCart shoppingCart = findByUserId(userId);
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    public ShoppingCartResponseDto updateShoppingCart(
            Long userId, Long id, UpdateCartItemRequestDto requestUpdateDto) {
        ShoppingCart shoppingCart = findByUserId(userId);
        CartItem cartItem = cartItemRepository.findByIdAndShoppingCartId(id,
                shoppingCart.getId()).orElseThrow(
                    () -> new EntityNotFoundException(UPDATE_ERROR_MSG + id)
        );
        cartItem.setQuantity(requestUpdateDto.getQuantity());
        cartItemRepository.save(cartItem);
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    public void deleteCartItem(Long id) {
        cartItemRepository.deleteById(id);
    }

    private ShoppingCart findByUserId(Long userId) {
        return shoppingCartRepository.findByUserId(userId).orElseThrow(
                () -> new EntityNotFoundException(CANT_FIND_SHOPPING_CART_MSG
                        + userId));
    }
}
