package mate.academy.bookstore.repository;

import java.util.Optional;
import mate.academy.bookstore.model.CartItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Page<CartItem> findAll(Pageable pageable);

    Optional<CartItem> findByIdAndShoppingCartId(Long id, Long shoppingCartId);
}
