package mate.academy.bookstore.service.impl;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;
import mate.academy.bookstore.dto.book.BookResponseDto;
import mate.academy.bookstore.mapper.BookMapper;
import mate.academy.bookstore.model.Book;
import mate.academy.bookstore.model.Category;
import mate.academy.bookstore.repository.BookRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testcontainers.shaded.org.apache.commons.lang3.builder.EqualsBuilder;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    private static Book book;
    private static BookResponseDto bookResponseDto;

    @Mock
    private BookRepository bookRepository;
    @Mock
    private BookMapper bookMapper;
    @InjectMocks
    private BookServiceImpl bookService;

    @BeforeAll
    static void beforeAll() {
        Category category = new Category()
                .setId(2L)
                .setName("Tragedy")
                .setDescription("Tragedy is a genre of drama based on human "
                        + "suffering and, mainly, the terrible or sorrowful events "
                        + "that befall a main character.");

        book = new Book()
                .setId(1L)
                .setTitle("The Great Gatsby")
                .setAuthor("F. Scott Fitzgerald")
                .setIsbn("978-3-16-148410-0")
                .setPrice(BigDecimal.valueOf(19.99))
                .setDescription("A novel about the American Dream.")
                .setCoverImage("gatsby.jpg")
                .setCategories(Set.of(category));

        bookResponseDto = new BookResponseDto()
                .setId(1L)
                .setTitle("The Great Gatsby")
                .setAuthor("F. Scott Fitzgerald")
                .setIsbn("978-3-16-148410-0")
                .setPrice(BigDecimal.valueOf(19.99))
                .setDescription("A novel about the American Dream.")
                .setCoverImage("gatsby.jpg")
                .setCategoryIds(Set.of(2L));
    }

    @Test
    @DisplayName("Find book by id")
    void findById_ValidId_ReturnBookResponseDto() {

        Mockito.when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        Mockito.when(bookMapper.toDto(book)).thenReturn(bookResponseDto);

        BookResponseDto expected = bookResponseDto;
        BookResponseDto actual = bookService.getBookById(book.getId());
        EqualsBuilder.reflectionEquals(expected, actual);
    }
}
