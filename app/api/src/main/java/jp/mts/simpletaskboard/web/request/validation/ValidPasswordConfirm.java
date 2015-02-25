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

import jp.mts.simpletaskboard.web.request.validation.ValidPasswordConfirm.PasswordConfirmValidator;

import org.apache.commons.lang3.StringUtils;

@Target({ TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordConfirmValidator.class)
@Documented
public @interface ValidPasswordConfirm {
	String confirmPasswordField() default "confirmPassword";

	String message() default "{jp.mts.simpletaskboard.validation.ValidPasswordConfirm.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    public static class PasswordConfirmValidator
    	implements ConstraintValidator<ValidPasswordConfirm, ConfirmPasswordInput> {

    	private ValidPasswordConfirm annotation;

		@Override
		public void initialize(ValidPasswordConfirm constraintAnnotation) {
			this.annotation = constraintAnnotation;
		}

		@Override
		public boolean isValid(ConfirmPasswordInput target,
				ConstraintValidatorContext constrantContext) {

			if(StringUtils.isEmpty(target.getPassword())
					|| StringUtils.isEmpty(target.getConfirmPassword())){
				return true;
			}

			if(StringUtils.equals(target.getPassword(), target.getConfirmPassword())){
				return true;
			}

			constrantContext.disableDefaultConstraintViolation();
			constrantContext
				.buildConstraintViolationWithTemplate(this.annotation.message())
				.addPropertyNode(this.annotation.confirmPasswordField())
				.addConstraintViolation();
			return false;
		}

    }

    interface ConfirmPasswordInput {
    	String getPassword();
    	String getConfirmPassword();
    }
}
