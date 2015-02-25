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

public class PasswordTest{

	private Validator validator;

	@Before
	public void setup(){
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void 不正な形式の場合にエラー() {

		Hoge hoge = new Hoge();
		hoge.passsword = "1";

		Set<ConstraintViolation<Hoge>> constraintViolations = validator.validate(hoge);

		assertThat(constraintViolations.size()).isEqualTo(1);
		assertThat(constraintViolations, hasMessage("{jp.mts.simpletaskboard.validation.Password.message}"));
	}
	@Test
	public void 正しい形式の場合にエラーにならない() {

		Hoge hoge = new Hoge();
		hoge.passsword = "12345678";

		Set<ConstraintViolation<Hoge>> constraintVaiolations = validator.validate(hoge);

		assertThat(constraintVaiolations.size()).isEqualTo(0);
	}

}

class Hoge {

	@Password
	public String passsword;
}
