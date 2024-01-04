package mate.academy.bookstore.repository;

import java.util.List;
import mate.academy.bookstore.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> getAllByOrderId(Long orderId);
}
