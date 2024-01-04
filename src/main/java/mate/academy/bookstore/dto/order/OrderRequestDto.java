package mate.academy.bookstore.dto.order;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class OrderRequestDto {
    @NotNull
    @Size(min = 1, max = 255)
    private String shippingAddress;
}
