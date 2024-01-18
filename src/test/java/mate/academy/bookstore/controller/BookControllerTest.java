package mate.academy.bookstore.controller;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;
import javax.sql.DataSource;
import lombok.SneakyThrows;
import mate.academy.bookstore.dto.book.BookResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.shaded.org.apache.commons.lang3.builder.EqualsBuilder;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {
    protected static MockMvc mockMvc;
    private static BookResponseDto bookResponseDto;

    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    @BeforeAll
    static void beforeAll(@Autowired WebApplicationContext applicationContext,
                          @Autowired DataSource dataSource) {
        mockMvc = MockMvcBuilders
            .webAppContextSetup(applicationContext)
            .apply(springSecurity())
            .build();

        bookResponseDto = new BookResponseDto()
                .setId(3L)
                .setTitle("The Great Gatsby")
                .setAuthor("F. Scott Fitzgerald")
                .setIsbn("978-3-16-148410-0")
                .setPrice(BigDecimal.valueOf(19.99));

        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(true);
            ScriptUtils.executeSqlScript(
                    connection,
                    new ClassPathResource("database/books/delete-books.sql")
            );
        }

        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(true);
            ScriptUtils.executeSqlScript(
                    connection,
                    new ClassPathResource("database/books/insert-books.sql")
            );
        }
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    @DisplayName("Get all books")
    @Sql(scripts = "classpath:database/books/delete-books.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void getAllBooks_ValidRequest_GetListValidBookResponseDto() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/books")).andReturn();
        List<BookResponseDto> expected = List.of(bookResponseDto);
        List<BookResponseDto> actual = List.of(objectMapper
                .readValue(mvcResult.getResponse().getContentAsString(),
                BookResponseDto[].class));
        Assertions.assertEquals(1, actual.size());
        EqualsBuilder.reflectionEquals(expected, actual, "id");
    }
}
