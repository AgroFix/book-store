package mate.academy.bookstore.mapper;

import java.util.Set;
import java.util.stream.Collectors;
import mate.academy.bookstore.config.MapperConfig;
import mate.academy.bookstore.dto.book.BookDtoWithoutCategoryIds;
import mate.academy.bookstore.dto.book.BookRequestDto;
import mate.academy.bookstore.dto.book.BookResponseDto;
import mate.academy.bookstore.model.Book;
import mate.academy.bookstore.model.Category;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookResponseDto toDto(Book book);

    Book toBook(BookRequestDto requestDto);

    BookDtoWithoutCategoryIds toDtoWithoutCategories(Book book);

    @AfterMapping
    default void setCategoryIds(@MappingTarget BookResponseDto bookResponseDto, Book book) {
        Set<Long> categories = book.getCategories().stream()
                .map(Category::getId)
                .collect(Collectors.toSet());
        bookResponseDto.setCategoryIds(categories);
    }
}
