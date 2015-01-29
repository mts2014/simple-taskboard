package jp.mts.simpletaskboard.test.scenarios.api;

import static jp.mts.simpletaskboard.test.base.UserInputs.*;
import static jp.mts.simpletaskboard.test.inputkeys.UserRegisterKey.*;
import static org.fest.assertions.api.Assertions.*;

import java.util.List;

import jp.mts.simpletaskboard.test.apis.UserApi;

import org.junit.Test;

public class UserApiScenario {

	private UserApi userApi = new UserApi();

	@Test public void
	ユーザをメールアドレスで検索できること(){

		userApi.存在する("hoge@test.jp");
	}

	@Test public void
	存在しないメールアドレスの場合検索結果なし(){

		userApi.存在しない("bar@test.jp");
	}

	@Test public void
	ユーザを登録できること(){

		userApi.登録する($in()
			.v(EMAIL, "foo@test.jp")
			.v(ユーザ名, "taro")
			.v(パスワード, "pass")
			.v(確認パスワード, "pass"));

		userApi.存在する("foo@test.jp", "taro");
	}

	@Test public void
	ユーザの登録情報を検証できること_すでに存在するメールアドレスの場合エラー(){

		userApi.登録する($in()
			.v(EMAIL, "baz@text.jp"));

		List<String> errorMsgs = userApi.登録時の検証をする($in()
			.v(EMAIL, "baz@text.jp"));

		assertThat(errorMsgs).contains("指定されたメールアドレスはすでに登録されています。");
	}
}
