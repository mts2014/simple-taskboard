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

/**
 * <pre>
 * ユーザストーリ：
 * ユーザとして
 * システムににログインしたい
 * 第三者がタスクボードを閲覧できないようにしたいからだ
 * </pre>
 */
public class LoginScenario extends AcceptanceTestBase {

	@UI LoginUi loginUi;
	@UI TaskboardUi taskboardUi;
	UserApi userApi = new UserApi();;

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
