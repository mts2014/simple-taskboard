package jp.mts.simpletaskboard.test.scenarios;

import static jp.mts.simpletaskboard.test.uis.UserRegisterUi.*;
import jp.mts.simpletaskboard.test.apis.UserApi;
import jp.mts.simpletaskboard.test.base.AcceptanceTestBase;
import jp.mts.simpletaskboard.test.base.UI;
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

			userApi.登録する(入力()
					.EMAIL         ("login-scenario@test.jp")
					.ユーザ名      ("ログインテストユーザ")
					.パスワード    ("loginpass")
					.確認パスワード("loginpass"));

			loginUi.ログインする(LoginUi.入力()
					.ログインＩＤ("login-scenario@test.jp")
					.パスワード  ("loginpass"));

			taskboardUi.タスクボードが表示されている();

			taskboardUi.ログインユーザが表示されている("ログインテストユーザ");
		}
	}


	public static class 異状ケース extends 共通設定{

		@Test public void
		未登録のユーザに対して_存在しないエラーが通知されること(){

			loginUi.ログインする(LoginUi.入力()
					.ログインＩＤ("not_exist@test.jp")
					.パスワード  ("loginpass"));


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

			loginUi.認証情報を検証する(LoginUi.入力()
					.ログインＩＤ("")
					.パスワード  ("loginpass"));

			loginUi.エラーメッセージあり(
					LoginUi.InputKey.ログインＩＤ, "入力してください。");
		}

		@Test public void
		パスワードが未入力の場合入力チェックエラーになること(){

			loginUi.認証情報を検証する(LoginUi.入力()
					.ログインＩＤ("test")
					.パスワード  ("loginpass"));

			loginUi.エラーメッセージあり(
					LoginUi.InputKey.パスワード, "入力してください。");
		}
	}
}
