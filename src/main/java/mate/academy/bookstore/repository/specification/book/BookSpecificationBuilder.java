package mate.academy.bookstore.repository.specification.book;

import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.book.BookSearchParametersDto;
import mate.academy.bookstore.model.Book;
import mate.academy.bookstore.repository.specification.SpecificationBuilder;
import mate.academy.bookstore.repository.specification.SpecificationProviderManager;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookSpecificationBuilder implements SpecificationBuilder<Book> {
    private static final String KEY_TITLE = "title";
    private static final String KEY_AUTHOR = "author";
    private final SpecificationProviderManager<Book> bookSpecificationProviderManager;

    @Override
    public Specification<Book> build(BookSearchParametersDto searchParameters) {
        Specification<Book> spec = Specification.where(null);
        spec = searchTitle(searchParameters, spec);
        spec = searchAuthor(searchParameters, spec);
        return spec;
    }

    private Specification<Book> searchTitle(
            BookSearchParametersDto searchParameters,
            Specification<Book> spec
    ) {
        if (searchParameters.getTitles() != null
                && searchParameters.getTitles().length > 0) {
            spec = spec.and(
                    bookSpecificationProviderManager.getSpecificationProvider(KEY_TITLE)
                            .getSpecification(searchParameters.getTitles())
            );
        }
        return spec;
    }

    private Specification<Book> searchAuthor(
            BookSearchParametersDto searchParameters,
            Specification<Book> spec
    ) {
        if (searchParameters.getAuthors() != null
                && searchParameters.getAuthors().length > 0) {
            spec = spec.and(
                    bookSpecificationProviderManager.getSpecificationProvider(KEY_AUTHOR)
                            .getSpecification(searchParameters.getAuthors())
            );
        }
        return spec;
    }
}
