package jp.mts.simpletaskboard.web.controllers;

import static org.fest.assertions.api.Assertions.*;
import jp.mts.simpletaskboard.domain.UserRepository;
import jp.mts.simpletaskboard.testhelpers.UserBuilder;
import jp.mts.simpletaskboard.web.request.AuthenticateInput;
import jp.mts.simpletaskboard.web.response.AuthView;
import jp.mts.simpletaskboard.web.response.RestResponse;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;

import org.junit.Before;
import org.junit.Test;

public class AuthControllerTest {

	AuthController sut;
	@Mocked UserRepository userRepository;

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

		AuthenticateInput input = new AuthenticateInput();
		input.authId = "taro@test.jp";
		input.password = "pass";

		RestResponse response =	sut.authenticate(input);

		AuthView authView = (AuthView)response.getContents().get("auth");
		assertThat(authView.getAuthId()).isEqualTo("taro@test.jp");
		assertThat(authView.getName()).isEqualTo("yamada taro");
	}

}
