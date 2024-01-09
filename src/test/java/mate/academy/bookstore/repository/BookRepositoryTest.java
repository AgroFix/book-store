package mate.academy.bookstore.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.bookstore.model.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("Find books by category")
    void findAllByCategoryId_ValidCategoryId_ReturnOneBook() {
        Book firstBook = new Book()
                .setId(1L)
                .setTitle("The Great Gatsby")
                .setAuthor("F. Scott Fitzgerald")
                .setIsbn("978-3-16-148410-0")
                .setPrice(BigDecimal.valueOf(19.99))
                .setDescription("A novel about the American Dream.")
                .setCoverImage("gatsby.jpg");
        Book secondBook = new Book()
                .setId(2L)
                .setTitle("To Kill a Mockingbird")
                .setAuthor("Harper Lee")
                .setIsbn("978-0-06-112008-4")
                .setPrice(BigDecimal.valueOf(24.99))
                .setDescription("A classic of modern American literature.")
                .setCoverImage("gatsby.jpg");

        List<Book> books = List.of(firstBook, secondBook);
        List<Book> expected = List.of(books.get(0));
        List<Book> actual = bookRepository.findAllByCategoryId(2L);

        assertEquals(1, actual.size());
        assertEquals(expected, actual);
    }
}
