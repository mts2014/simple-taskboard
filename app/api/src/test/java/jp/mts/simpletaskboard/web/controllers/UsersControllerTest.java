package jp.mts.simpletaskboard.web.controllers;

import static org.fest.assertions.api.Assertions.*;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jp.mts.simpletaskboard.domain.User;
import jp.mts.simpletaskboard.domain.UserRepository;
import jp.mts.simpletaskboard.testhelpers.UserBuilder;
import jp.mts.simpletaskboard.web.request.UserRegisterInput;
import jp.mts.simpletaskboard.web.response.ApiError;
import jp.mts.simpletaskboard.web.response.RestResponse;
import jp.mts.simpletaskboard.web.response.UserView;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import mockit.NonStrictExpectations;

import org.fest.util.Lists;
import org.junit.Before;
import org.junit.Test;

public class UsersControllerTest {

	UsersController sut = new UsersController();
	@Mocked UserRepository userRepository;
	@Mocked HttpServletResponse res;

	@Before
	public void setup(){
		Deencapsulation.setField(sut, userRepository);
	}

	@Test
	public void test_存在するemailを指定して検索できる() {

		final String email = "hoge@test.jp";
		new NonStrictExpectations() {{

			userRepository.searchByEmail(email);
				result = new UserBuilder().email(email).build();
				times = 1;
		}};

		RestResponse response = sut.searchByEmail(email, res);
		UserView user = (UserView)response.getContents().get("user");

		assertThat(user.getEmail()).isEqualTo(email);
	}

	@Test
	public void test_存在しないemailの場合はnull() {

		final String email = "foo@test.jp";
		new NonStrictExpectations() {{
			userRepository.searchByEmail(email);
				result = null;
				times = 1;

			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
				times = 1;
		}};

		RestResponse response = sut.searchByEmail(email, res);
		UserView user = (UserView)response.getContents().get("user");
		assertThat(user).isNull();
	}

	@Test
	public void test_正しいユーザ情報で登録できること(){

		UserRegisterInput userRegisterInput = new UserRegisterInput();
		userRegisterInput.email = "hoge@test.jp";
		userRegisterInput.userName = "taro";
		userRegisterInput.password = "password";
		userRegisterInput.confirmPassword = "confirmPassword";

		new NonStrictExpectations() {{

			userRepository.save((User)any);
				times = 1;
		}};

		RestResponse response = sut.register(userRegisterInput);

		UserView registerdUser = (UserView)response.getContents().get("user");

		assertThat(registerdUser.getEmail()).isEqualTo(userRegisterInput.email);
		assertThat(registerdUser.getName()).isEqualTo(userRegisterInput.userName);
	}

	@Test
	public void test_登録時の入力値検証_すでに存在するメールアドレスの場合エラー(){

		UserRegisterInput userRegisterInput = new UserRegisterInput();
		userRegisterInput.email = "hoge@test.jp";

		new Expectations() {{
			userRepository.searchByEmail(userRegisterInput.email);
				result = new UserBuilder().build();
		}};

		RestResponse response = sut.validate(userRegisterInput, res);

		List<ApiError> errors = response.getErrors();
		assertThat(errors.size()).isEqualTo(1);
		assertThat(errors.get(0).getErrorId()).isEqualTo("e001");
		assertThat(errors.get(0).getFields()).isEqualTo(Lists.newArrayList("email"));
	}
	@Test
	public void test_登録時の入力値検証_存在しないメールアドレスの場合エラーにならない(){

		UserRegisterInput userRegisterInput = new UserRegisterInput();
		userRegisterInput.email = "hoge@test.jp";

		new Expectations() {{
			userRepository.searchByEmail(userRegisterInput.email);
				result = null;
		}};

		RestResponse response = sut.validate(userRegisterInput, res);

		List<ApiError> errors = response.getErrors();
		assertThat(errors.size()).isEqualTo(0);
	}

}
