package mate.academy.bookstore.service;

import java.util.List;
import mate.academy.bookstore.dto.book.BookDtoWithoutCategoryIds;
import mate.academy.bookstore.dto.book.BookRequestDto;
import mate.academy.bookstore.dto.book.BookResponseDto;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookResponseDto createBook(BookRequestDto bookRequestDto);

    List<BookResponseDto> getAll(Pageable pageable);

    BookResponseDto getBookById(Long id);

    BookResponseDto updateBookById(Long id, BookRequestDto bookDto);

    void deleteBookById(Long id);

    List<BookDtoWithoutCategoryIds> getBooksByCategoryId(Long id);
}
