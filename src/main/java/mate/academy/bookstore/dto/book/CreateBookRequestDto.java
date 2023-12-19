package mate.academy.bookstore.dto.book;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class CreateBookRequestDto {
    @NotNull
    @Size(max = 255, message = "Text limit 255 symbols")
    private String title;
    @NotNull
    @Size(max = 255, message = "Text limit 255 symbols")
    private String author;
    @NotNull
    @Pattern(regexp = "^(?:[0-9-]{10}|[0-9-]{13})$",
            message = "Text limit 10 or 13 symbols, without any letters!")
    private String isbn;
    @NotNull
    @Min(0)
    private BigDecimal price;
    private String description;
    private String coverImage;
}
