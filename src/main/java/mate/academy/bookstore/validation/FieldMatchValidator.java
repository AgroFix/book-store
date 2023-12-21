package mate.academy.bookstore.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import mate.academy.bookstore.dto.user.UserRegistrationRequestDto;

public class FieldMatchValidator
        implements ConstraintValidator<FieldMatch, UserRegistrationRequestDto> {
    @Override
    public boolean isValid(UserRegistrationRequestDto requestDto,
            ConstraintValidatorContext constraintValidatorContext) {
        return requestDto.getPassword().equals(requestDto.getRepeatPassword());
    }
}
