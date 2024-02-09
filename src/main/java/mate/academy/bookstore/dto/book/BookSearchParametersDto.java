package mate.academy.bookstore.dto.book;

import lombok.Data;

@Data
public class BookSearchParametersDto {
    private String[] titles;
    private String[] authors;
    private String[] isbn;
}
