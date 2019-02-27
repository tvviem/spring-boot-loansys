package vn.blu.tvviem.loansys.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import vn.blu.tvviem.loansys.web.dto.UserDto;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(final PasswordMatches constraintAnnotation) {
        //
    }

    @Override
    public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
        final UserDto user = (UserDto) obj;
        return user.getPassword().equals(user.getMatchingPassword());
    }

}
