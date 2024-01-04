package mate.academy.bookstore.repository;

import java.util.List;
import java.util.Optional;
import mate.academy.bookstore.model.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserId(Long id);

    @EntityGraph(attributePaths = {"orderItems"})
    Optional<Order> findByIdAndUserId(Long orderId, Long userId);
}
