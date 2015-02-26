package jp.mts.simpletaskboard.test.scenarios.api;

import static jp.mts.simpletaskboard.test.base.UserInputs.*;
import static jp.mts.simpletaskboard.test.inputkeys.UserRegisterKey.*;
import jp.mts.simpletaskboard.test.apis.UserApi;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class UserApiScenario {

	private static abstract class 共通設定 {
		UserApi userApi = new UserApi();
	}

	public static class 検索 extends 共通設定 {

		@Test public void
		ユーザをメールアドレスで検索できること(){

			userApi.存在する("hoge@test.jp");
		}

		@Test public void
		存在しないメールアドレスの場合検索結果なし(){

			userApi.存在しない("bar@test.jp");
		}
	}

	public static class 登録 extends 共通設定 {

		@Test public void
		ユーザを登録できること(){

			userApi.登録する($in()
				.v(EMAIL,          "foo@test.jp")
				.v(ユーザ名,       "taro")
				.v(パスワード,     "pass")
				.v(確認パスワード, "pass"));

			userApi.存在する("foo@test.jp", "taro");
		}

	}

	public static class 登録情報の検証 extends 共通設定 {

		@Test public void
		メールアドレスが空の場合エラー(){

			userApi.登録時の検証をする($in()
					.v(EMAIL, ""))
				.assertHasError("email", "入力してください。");

		}
	}

}
