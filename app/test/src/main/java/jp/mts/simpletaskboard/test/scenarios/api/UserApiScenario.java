package jp.mts.simpletaskboard.test.scenarios.api;

import static jp.mts.simpletaskboard.test.base.UserInputs.*;
import static jp.mts.simpletaskboard.test.inputkeys.UserRegisterKey.*;
import static org.fest.assertions.api.Assertions.*;
import jp.mts.simpletaskboard.test.apis.UserApi;

import org.junit.Test;

public class UserApiScenario {

	private UserApi userApi = new UserApi();

	@Test
	public void test_ユーザをメールアドレスで検索できること(){

		assertThat(userApi.存在するか("hoge@test.jp"))
			.isTrue();
	}
	@Test
	public void test_存在しないメールアドレスの場合検索結果なし(){

		assertThat(userApi.存在するか("bar@test.jp"))
			.isFalse();

	}
	@Test
	public void test_ユーザを登録できること(){

		userApi.登録する($in().v(EMAIL, "foo@test.jp"));

		assertThat(userApi.存在するか("foo@test.jp"))
			.isTrue();
	}
}
