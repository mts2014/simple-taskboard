package jp.mts.simpletaskboard.test.scenarios;

import static jp.mts.simpletaskboard.test.base.UserInputs.*;
import jp.mts.simpletaskboard.test.apis.UserApi;
import jp.mts.simpletaskboard.test.base.AcceptanceTestBase;
import jp.mts.simpletaskboard.test.base.UI;
import jp.mts.simpletaskboard.test.inputkeys.LoginKey;
import jp.mts.simpletaskboard.test.inputkeys.UserRegisterKey;
import jp.mts.simpletaskboard.test.uis.LoginUi;
import jp.mts.simpletaskboard.test.uis.TaskboardUi;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

/**
 * <pre>
 * ユーザストーリ：
 * ユーザとして
 * システムににログインしたい
 * 第三者がタスクボードを閲覧できないようにしたいからだ
 * </pre>
 */
@RunWith(Enclosed.class)
public class LoginScenario {

	private static class 共通設定 extends AcceptanceTestBase {
		@UI LoginUi loginUi;
		@UI TaskboardUi taskboardUi;
		UserApi userApi = new UserApi();;
	}

	public static class 正常ケース extends 共通設定{

		@Test public void
		登録済みのユーザに対して_メールアドレスとパスワードでログインできること(){

			userApi.登録する($in()
					.v(UserRegisterKey.EMAIL,          "login-scenario@test.jp")
					.v(UserRegisterKey.ユーザ名,       "ログインテストユーザ")
					.v(UserRegisterKey.パスワード,     "loginpass")
					.v(UserRegisterKey.確認パスワード, "loginpass"));

			loginUi.ログインする($in()
					.v(LoginKey.ログインＩＤ, "login-scenario@test.jp")
					.v(LoginKey.パスワード,   "loginpass"));

			taskboardUi.タスクボードが表示されている();

			taskboardUi.ログインユーザが表示されている("ログインテストユーザ");
		}
	}


	public static class 異状ケース extends 共通設定{

		@Test public void
		未登録のユーザに対して_存在しないエラーが通知されること(){

			loginUi.ログインする($in()
					.v(LoginKey.ログインＩＤ, "not_exist@test.jp")
					.v(LoginKey.パスワード,   "loginpass"));


			loginUi.エラーメッセージあり(
					"IDまたはパスワードが間違っています。");

		}
	}


	/**
	 * サブストーリ
	 *
	 * ユーザとして
     * ログインするときに、不正な入力値があれば知らせてほしい。
     * 入力ミスに気づきたいからだ
	 */
	public static class 入力値検証 extends 共通設定 {

		@Test public void
		メールアドレスが未入力の場合入力チェックエラーになること(){

			loginUi.認証情報を検証する($in()
					.v(LoginKey.ログインＩＤ, "")
					.v(LoginKey.パスワード,   "loginpass"));

			loginUi.エラーメッセージあり(
					LoginKey.ログインＩＤ, "入力してください。");
		}

		@Test public void
		パスワードが未入力の場合入力チェックエラーになること(){

			loginUi.認証情報を検証する($in()
					.v(LoginKey.ログインＩＤ, "test")
					.v(LoginKey.パスワード,   ""));

			loginUi.エラーメッセージあり(
					LoginKey.パスワード, "入力してください。");
		}
	}
}
