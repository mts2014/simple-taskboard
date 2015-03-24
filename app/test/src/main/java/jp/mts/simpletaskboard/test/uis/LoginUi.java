package jp.mts.simpletaskboard.test.uis;

import static org.fest.assertions.api.Assertions.*;
import jp.mts.simpletaskboard.test.base.AcceptanceUiBase;
import jp.mts.simpletaskboard.test.base.Page;
import jp.mts.simpletaskboard.test.uis.pages.LoginPage;

import org.fluentlenium.adapter.FluentTest;

public class LoginUi extends AcceptanceUiBase {

	@Page
	private LoginPage loginPage;

	public LoginUi(FluentTest fluentTest) {
		super(fluentTest);
	}

	public boolean isAtLogin() {
		return loginPage.hasUserRegisterLink();
	}

	public void ログインする(Input input) {
		loginPage.go();

		loginPage
			.email(input.loginId)
			.password(input.password)
			.login();
	}

	public void エラーメッセージあり(String message) {
		assertThat(loginPage.globalErrors()).contains(message);
	}

	public void 認証情報を検証する(Input input) {
		loginPage.go();

		loginPage
			.email(input.loginId)
			.password(input.password);

		loginPage
			.validateInputs();

	}

	public void エラーメッセージあり(LoginUi.InputKey key, String message) {
		assertThat(loginPage.errorMsg(key.getId())).contains(message);
	}

	public static Input 入力(){
		return new Input();
	}

	public static class Input {
		String loginId = "test@test.jp";
		String password = "pass";

		public Input ログインＩＤ(String loginId){
			this.loginId = loginId;
			return this;
		}
		public Input パスワード(String password){
			this.password = password;
			return this;
		}
	}

	public enum InputKey {
		ログインＩＤ (LoginPage.Id.INPUT_LOGINID),
		パスワード   (LoginPage.Id.INPUT_PASSWORD),
		;

		private LoginPage.Id id;

		private InputKey(LoginPage.Id id) {
			this.id = id;
		}
		public LoginPage.Id getId() {
			return id;
		}
	}

}
