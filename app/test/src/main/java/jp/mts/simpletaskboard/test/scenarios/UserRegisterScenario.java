package jp.mts.simpletaskboard.test.scenarios;

import static jp.mts.simpletaskboard.test.base.UserInputs.*;
import static jp.mts.simpletaskboard.test.inputkeys.UserRegisterKey.*;
import static org.fest.assertions.api.Assertions.*;
import jp.mts.simpletaskboard.test.apis.UserApi;
import jp.mts.simpletaskboard.test.base.AcceptanceTestBase;
import jp.mts.simpletaskboard.test.base.UI;
import jp.mts.simpletaskboard.test.uis.LoginUi;
import jp.mts.simpletaskboard.test.uis.UserRegisterUi;

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
		UserApi userApi;
	}

	/**
	 * サブストーリ
	 *
     * 新規利用者として
     * ユーザの登録を正常に行いたい
     * タスクボードを利用したいからだ
	 */
	public static class 正常にユーザ登録できる extends 共通設定 {

		@Test
		@Ignore
		public void ユーザを新規に登録できること(){

			assertThat(userApi.存在するか？("taro@test.jp", "太郎"))
				.isFalse();

			userRegisterUi.ユーザ情報を登録する($in()
					.v(EMAIL,          "taro@test.jp")
					.v(ユーザ名,       "太郎")
					.v(パスワード,     "pass")
					.v(確認パスワード, "pass"));

			assertThat(userApi.存在するか？("taro@test.jp", "太郎"))
				.isTrue();
		}
	}

	/**
	 * サブストーリ
	 *
	 * 新規利用者として
	 * ユーザを登録するときに、すでに同じメールアドレスのユーザが存在するかを知りたい
	 * 誤登録の可能性があり心配だからだ。
	 */
	public static class 重複メールアドレスの場合ユーザを登録できない extends 共通設定 {

		@Test
		@Ignore
		public void 重複するユーザは登録できないこと(){

			userApi.登録する($in()
					.v(EMAIL, "taro2@test.jp"));

			userRegisterUi.ユーザ情報を登録する($in()
					.v(EMAIL, "taro2@test.jp"));

			assertThat(userRegisterUi.errorMsg())
				.contains("指定されたメールアドレスはすでに登録されています。");
		}

	}


	/**
	 * サブストーリ
	 *
	 * 新規利用者として
	 * ユーザを登録するときに、不正な入力値があれば知らせてほしい。
	 * 間違った情報を登録したくないからだ。
	 */
	public static class 入力値検証 extends 共通設定 {

		@Test
		@Ignore
		public void メールアドレスが未入力の場合入力チェックエラーになること(){

			userRegisterUi.ユーザ情報を入力する($in()
					.v(EMAIL, ""));

			assertThat(userRegisterUi.errorMsg(EMAIL))
				.contains("メールアドレスは必須です。");

			assertThat(userRegisterUi.canRegister())
				.isFalse();
		}

		@Test
		@Ignore
		public void パスワードと確認用パスワードの入力値が異なる場合登録できないこと(){
		}

	}



}
