package mate.academy.bookstore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.shopping.cart.ShoppingCartResponseDto;
import mate.academy.bookstore.dto.shopping.cart.item.CartItemRequestDto;
import mate.academy.bookstore.dto.shopping.cart.item.UpdateCartItemRequestDto;
import mate.academy.bookstore.model.User;
import mate.academy.bookstore.service.ShoppingCartService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/cart")
@Tag(name = "Shopping cart management", description = "Endpoints for shopping cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add book to cart", description = "Add book to cart")
    public ShoppingCartResponseDto addToShoppingCart(
            Authentication authentication, @RequestBody @Valid CartItemRequestDto requestDto) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.addToShoppingCart(requestDto, user.getId());
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    @Operation(summary = "Get shopping cart", description = "Get all shopping cart details")
    public ShoppingCartResponseDto getShoppingCart(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.getShoppingCart(user.getId());
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/cart-items/{id}")
    @Operation(summary = "Update books quantity",
            description = "Update quantity of a book in the shopping cart")
    public ShoppingCartResponseDto updateQuantityOfBooksInShoppingCart(
            Authentication authentication, @PathVariable Long id,
            @RequestBody @Valid UpdateCartItemRequestDto requestUpdateDto) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.updateCartItem(user.getId(), id,
                requestUpdateDto);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/cart-items/{id}")
    @Operation(summary = "Delete book from shopping cart",
            description = "Delete book from shopping cart")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        shoppingCartService.deleteCartItem(id);
    }
}
