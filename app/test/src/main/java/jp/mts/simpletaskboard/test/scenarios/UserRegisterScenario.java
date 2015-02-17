package jp.mts.simpletaskboard.test.scenarios;

import static jp.mts.simpletaskboard.test.base.UserInputs.*;
import static jp.mts.simpletaskboard.test.inputkeys.UserRegisterKey.*;
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
		UserApi userApi = new UserApi();;
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
		public void ユーザを新規に登録できること(){

			userApi.存在しない("taro@test.jp", "太郎");

			userRegisterUi.ユーザ情報を登録する($in()
					.v(EMAIL,          "taro@test.jp")
					.v(ユーザ名,       "太郎")
					.v(パスワード,     "pass")
					.v(確認パスワード, "pass"));

			userApi.存在する("taro@test.jp", "太郎");
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
		public void すでに存在するメールアドレスを入力するとエラー(){

			userApi.登録する($in()
					.v(EMAIL, "taro2@test.jp"));

			userRegisterUi.ユーザ登録情報を検証する($in()
					.v(EMAIL, "taro2@test.jp"));

			userRegisterUi.エラーメッセージあり(
					EMAIL, "指定されたメールアドレスはすでに登録されています。");
		}

		@Test
		public void メールアドレスが未入力の場合入力チェックエラーになること(){

			userRegisterUi.ユーザ登録情報を検証する($in()
					.v(EMAIL, ""));

			userRegisterUi.エラーメッセージあり(
					EMAIL, "入力してください。");
		}

		@Test
		public void メールアドレスの形式が正しくない場合入力チェックエラーになること(){

			userRegisterUi.ユーザ登録情報を検証する($in()
					.v(EMAIL, "hoge"));

			userRegisterUi.エラーメッセージあり(
					EMAIL, "正しいメールアドレスの形式で入力してください。");
		}

		@Test
		public void ユーザ名が未入力の場合入力チェックエラーになること(){

			userRegisterUi.ユーザ登録情報を検証する($in()
					.v(ユーザ名, ""));

			userRegisterUi.エラーメッセージあり(
					ユーザ名, "入力してください。");
		}
		@Test
		public void パスワードが未入力の場合入力チェックエラーになること(){

			userRegisterUi.ユーザ登録情報を検証する($in()
					.v(パスワード, ""));

			userRegisterUi.エラーメッセージあり(
					パスワード, "入力してください。");
		}
		@Test
		@Ignore
		public void パスワードと確認用パスワードの入力値が異なる場合登録できないこと(){
		}

	}



}
