package mate.academy.bookstore.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.order.OrderRequestDto;
import mate.academy.bookstore.dto.order.OrderResponseDto;
import mate.academy.bookstore.dto.order.UpdateOrderRequestDto;
import mate.academy.bookstore.exception.EntityNotFoundException;
import mate.academy.bookstore.mapper.OrderMapper;
import mate.academy.bookstore.model.Order;
import mate.academy.bookstore.model.OrderItem;
import mate.academy.bookstore.model.ShoppingCart;
import mate.academy.bookstore.model.User;
import mate.academy.bookstore.repository.CartItemRepository;
import mate.academy.bookstore.repository.OrderItemRepository;
import mate.academy.bookstore.repository.OrderRepository;
import mate.academy.bookstore.repository.ShoppingCartRepository;
import mate.academy.bookstore.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private static final String CANT_FIND_ORDER_MSG = "Can't find order by id: ";
    private static final String CANT_FIND_SHOPPING_CART_MSG = "Can't find shopping cart by id: ";
    private static final String EMPTY_SHOPPING_CART_MSG = "Shopping cart is empty ";
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderMapper orderMapper;
    private final ShoppingCartRepository shoppingCartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public List<OrderResponseDto> getAllOrders(Long userId) {
        List<Order> orderList = orderRepository.findAllByUserId(userId);
        return orderList.stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public OrderResponseDto placeOrder(User user, OrderRequestDto dto) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(user.getId()).orElseThrow(
                () -> new EntityNotFoundException(CANT_FIND_SHOPPING_CART_MSG
                        + user.getId()));
        if (shoppingCart.getCartItems().isEmpty()) {
            throw new EntityNotFoundException(EMPTY_SHOPPING_CART_MSG);
        }

        BigDecimal total = calculateTotal(shoppingCart);
        Order savedOrder = orderRepository.save(newOrder(user, dto.getShippingAddress(), total));
        List<OrderItem> orderItems = createOrderItems(savedOrder, shoppingCart);

        orderItemRepository.saveAll(orderItems);
        savedOrder.setOrderItems(new HashSet<>(orderItems));
        cartItemRepository.deleteAll(shoppingCart.getCartItems());
        return orderMapper.toDto(savedOrder);
    }

    @Override
    @Transactional
    public OrderResponseDto updateOrder(Long orderId, UpdateOrderRequestDto requestDto) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new EntityNotFoundException(CANT_FIND_ORDER_MSG + orderId)
        );
        order.setStatus(requestDto.getStatus());
        orderRepository.save(order);
        return orderMapper.toDto(order);
    }

    private BigDecimal calculateTotal(ShoppingCart shoppingCart) {
        return shoppingCart.getCartItems().stream()
                .map(cartItem -> cartItem.getBook().getPrice()
                        .multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                .reduce(BigDecimal.valueOf(0), BigDecimal::add);
    }

    private List<OrderItem> createOrderItems(Order order, ShoppingCart shoppingCart) {
        return shoppingCart.getCartItems().stream()
                .map(cartItem -> new OrderItem(order, cartItem.getBook(),
                        cartItem.getQuantity(), cartItem.getBook().getPrice()))
                .collect(Collectors.toList());
    }

    private Order newOrder(User user, String shippingAddress, BigDecimal total) {
        Order order = new Order();
        order.setTotal(total);
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(Order.Status.PENDING);
        order.setShippingAddress(shippingAddress);
        return order;
    }
}
