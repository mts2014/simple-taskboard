package jp.mts.simpletaskboard.web.request.validation;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import jp.mts.simpletaskboard.web.request.validation.Password.PasswordValidator;

import org.springframework.util.StringUtils;

@Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
@Documented
public @interface Password {
	String message() default "{jp.mts.simpletaskboard.validation.Password.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    @Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })
    @Retention(RUNTIME)
    @Documented
    @interface List {
    	Password[] value();
    }

    public static class PasswordValidator
    	implements ConstraintValidator<Password, String> {

		@Override
		public void initialize(Password constraintAnnotation) {
		}

		@Override
		public boolean isValid(String target,
				ConstraintValidatorContext constraintContext) {

			if(StringUtils.isEmpty(target)){
				return true;
			}

			return target.matches("^[0-9a-zA-Z]{4,}$");
		}

    }
}
