package jp.mts.simpletaskboard.web.controllers;

import static org.fest.assertions.api.Assertions.*;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jp.mts.simpletaskboard.domain.UserRepository;
import jp.mts.simpletaskboard.testhelpers.UserBuilder;
import jp.mts.simpletaskboard.web.request.AuthenticateInput;
import jp.mts.simpletaskboard.web.response.ApiError;
import jp.mts.simpletaskboard.web.response.AuthView;
import jp.mts.simpletaskboard.web.response.RestResponse;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;

import org.fest.util.Lists;
import org.junit.Before;
import org.junit.Test;

public class AuthControllerTest {

	AuthController sut;
	@Mocked UserRepository userRepository;
	@Mocked HttpServletResponse httpServletResponse;

	@Before
	public void setup(){
		sut = new AuthController();
		Deencapsulation.setField(sut, userRepository);
	}

	@Test
	public void ユーザ情報が取得できる場合_認証情報が返却されること() {
		new Expectations() {{
			userRepository.searchByEmailAndPassword("taro@test.jp", "pass");
				result = new UserBuilder().email("taro@test.jp").name("yamada taro").build();
		}};

		RestResponse response =	sut.authenticate(input("taro@test.jp", "pass"), httpServletResponse);

		AuthView authView = (AuthView)response.getContents().get("auth");
		assertThat(authView.getAuthId()).isEqualTo("taro@test.jp");
		assertThat(authView.getName()).isEqualTo("yamada taro");
	}

	@Test
	public void ユーザ情報が取得できない場合_エラーが返却されること() {
		new Expectations() {{
			userRepository.searchByEmailAndPassword("taro@test.jp", "pass");
				result = null;

			httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}};

		RestResponse response =	sut.authenticate(input("taro@test.jp", "pass"), httpServletResponse);

		List<ApiError> errors = response.getErrors();
		assertThat(errors.size()).isEqualTo(1);
		assertThat(errors.get(0).getErrorId()).isEqualTo("e002");
		assertThat(errors.get(0).getFields()).isEqualTo(Lists.newArrayList("email", "password"));
	}

	private AuthenticateInput input(String authId, String password) {
		AuthenticateInput input = new AuthenticateInput();
		input.authId = authId;
		input.password = password;
		return input;
	}

}
