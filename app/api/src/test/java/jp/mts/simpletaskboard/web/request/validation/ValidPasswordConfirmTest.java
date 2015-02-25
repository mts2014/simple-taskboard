package jp.mts.simpletaskboard.web.request.validation;

import static jp.mts.simpletaskboard.matchers.ConstraintViolationsMatchers.*;
import static org.fest.assertions.api.Assertions.*;
import static org.junit.Assert.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

public class ValidPasswordConfirmTest {

	private Validator validator;

	@Before
	public void setup(){
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void パスワードが異なる場合エラー() {
		Hoge hoge = new Hoge();
		hoge.password = "a";
		hoge.confirmPassword = "b";

		Set<ConstraintViolation<Hoge>> constraintViolations = validator.validate(hoge);
		assertThat(constraintViolations.size()).isEqualTo(1);
		assertThat(constraintViolations, hasFieldMessage("foo", "{jp.mts.simpletaskboard.validation.ValidPasswordConfirm.message}"));
	}

	@ValidPasswordConfirm(confirmPasswordField="foo")
	public static class Hoge implements ValidPasswordConfirm.ConfirmPasswordInput {

		String password;
		String confirmPassword;

		@Override
		public String getPassword() {
			return password;
		}

		@Override
		public String getConfirmPassword() {
			return confirmPassword;
		}
	}
}
