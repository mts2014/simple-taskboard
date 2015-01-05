package jp.mts.simpletaskboard.test.scenarios;

import static jp.mts.simpletaskboard.test.base.UserInputs.*;
import static jp.mts.simpletaskboard.test.uis.UserRegisterUi.UserInputKey.*;
import static org.fest.assertions.api.Assertions.*;
import jp.mts.simpletaskboard.test.base.AcceptanceTestBase;
import jp.mts.simpletaskboard.test.base.UI;
import jp.mts.simpletaskboard.test.uis.LoginUi;
import jp.mts.simpletaskboard.test.uis.UserRegisterUi;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

/**
 * <pre>
 * ユーザストーリ：
 *
 * 新規利用者として
 * ユーザの登録を行いたい
 * タスクボードを利用したいからだ
 * </pre>
 */
@RunWith(Enclosed.class)
public class UserRegisterScenario {

	private static abstract class 共通設定 extends AcceptanceTestBase {
		@UI UserRegisterUi userRegisterUi;
		@UI LoginUi loginUi;
	}

	public static class 登録済みのユーザが存在しない場合 extends 共通設定 {

		@Test
		public void ユーザを新規に登録できること(){

			userRegisterUi.ユーザ情報を登録する($in()
					.v(EMAIL, "taro@test.jp")
					.v(ユーザ名, "太郎")
					.v(パスワード, "pass")
					.v(確認パスワード, "pass"));

			//TODO ユーザ参照機能ができたら、ユーザの存在を検証をする
		}
	}

	public static class 登録済みのユーザが存在する場合 extends 共通設定 {

		@Before
		public void setUp(){
			//TODO サーバAPIの呼び出しに変える
			userRegisterUi.ユーザ情報を登録する($in()
					.v(EMAIL, "taro2@test.jp"));
		}

		@Test
		public void 重複するユーザは登録できないこと(){

			userRegisterUi.ユーザ情報を登録する($in()
					.v(EMAIL, "taro2@test.jp"));

			assertThat(userRegisterUi.errorMsg())
				.isEqualTo("指定されたメールアドレスはすでに登録されています。");
		}

	}

	public static class 入力値不正のテスト extends 共通設定 {
		@Test
		@Ignore
		public void メールアドレスの入力値が不正な形式の場合登録できないこと(){
			userRegisterUi.ユーザ情報を入力する($in()
					.v(EMAIL, "a"));

			assertThat(userRegisterUi.errorMsg(EMAIL))
				.isEqualTo("不正なメールアドレスの形式です。");

			assertThat(userRegisterUi.canRegister())
				.isFalse();
		}

		@Test
		@Ignore
		public void パスワードと確認用パスワードの入力値が異なる場合登録できないこと(){
		}

	}



}
