package mate.academy.bookstore.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserLoginRequestDto {
    @NotNull
    @Size(min = 2, max = 255)
    @Email
    private String email;
    @NotNull
    @Size(min = 2, max = 255)
    private String password;
}
