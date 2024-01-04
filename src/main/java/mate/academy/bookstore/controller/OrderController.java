package mate.academy.bookstore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.order.OrderRequestDto;
import mate.academy.bookstore.dto.order.OrderResponseDto;
import mate.academy.bookstore.dto.order.UpdateOrderRequestDto;
import mate.academy.bookstore.dto.order.item.OrderItemResponseDto;
import mate.academy.bookstore.model.User;
import mate.academy.bookstore.service.OrderItemService;
import mate.academy.bookstore.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/orders")
@Tag(name = "Order management", description = "Endpoints for managing orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderItemService orderItemService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new order", description = "Create new order")
    public OrderResponseDto placeOrder(Authentication authentication,
                                        @RequestBody @Valid OrderRequestDto requestDto) {
        User user = (User) authentication.getPrincipal();
        return orderService.placeOrder(user, requestDto);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping
    @Operation(summary = "Get all orders", description = "Return all orders")
    public List<OrderResponseDto> getAllOrders(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return orderService.getAllOrders(user.getId());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
            summary = "Update order status",
            description = "Update order status")
    @ResponseStatus(HttpStatus.CREATED)
    @PatchMapping("/{orderId}")
    public OrderResponseDto updateOrder(
            @PathVariable Long orderId,
            @RequestBody UpdateOrderRequestDto requestDto) {
        return orderService.updateOrder(orderId, requestDto);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{orderId}/items")
    @Operation(
            summary = "Get all items by order id",
            description = "Retrieve all OrderItems for a specific order"
    )
    public List<OrderItemResponseDto> getAllOrderItemsById(@PathVariable Long orderId) {
        return orderItemService.getAllOrderItemsById(orderId);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{orderId}/items/{itemId}")
    public OrderItemResponseDto getItemFromOrderById(
            Authentication authentication,
            @PathVariable Long orderId,
            @PathVariable Long itemId
    ) {
        User user = (User) authentication.getPrincipal();
        return orderItemService.getItemFromOrderById(user.getId(), orderId, itemId);
    }
}
