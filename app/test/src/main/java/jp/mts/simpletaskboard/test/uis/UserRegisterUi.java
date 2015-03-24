package jp.mts.simpletaskboard.test.uis;

import static org.fest.assertions.api.Assertions.*;

import java.util.List;

import jp.mts.simpletaskboard.test.base.AcceptanceUiBase;
import jp.mts.simpletaskboard.test.base.Page;
import jp.mts.simpletaskboard.test.uis.pages.LoginPage;
import jp.mts.simpletaskboard.test.uis.pages.UserRegisterPage;

import org.fluentlenium.adapter.FluentTest;
import org.fluentlenium.core.domain.FluentWebElement;

public class UserRegisterUi extends AcceptanceUiBase {

	@Page
	private LoginPage loginPage;
	@Page
	private UserRegisterPage userRegisterPage;

	public UserRegisterUi(FluentTest fluentTest) {
		super(fluentTest);
	}

	public void ユーザ情報を登録する(Input input) {
		loginPage.go();
		loginPage.goToUserRegister();

		userRegisterPage
			.email(input.email)
			.userName(input.userName)
			.password(input.password)
			.passwordForConfirm(input.confirmPassword)
			.register();

	}

	public void ユーザ登録情報を検証する(Input input) {
		loginPage.go();
		loginPage.goToUserRegister();

		userRegisterPage
			.email(input.email)
			.userName(input.userName)
			.password(input.password)
			.passwordForConfirm(input.confirmPassword);

		userRegisterPage.validateInputs();
	}


	public String errorMsg() {
		List<String> errors = userRegisterPage.globalErrors();
		return errors.get(0);
	}

	public boolean canRegister() {
		FluentWebElement btn = userRegisterPage.findFirst("#user-register");
		return !btn.getAttribute("class").contains("disabled");
	}

	public void エラーメッセージあり(InputKey key, String message) {
		assertThat(userRegisterPage.errorMsg(key.getId())).contains(message);
	}


	public static Input 入力(){
		return new Input();
	}

	public static class Input {
		public String email = "test@test.jp";
		public String userName = "yamada taro";
		public String password = "pass";
		public String confirmPassword = "pass";

		public Input EMAIL(String email){
			this.email = email;
			return this;
		}
		public Input ユーザ名(String userName){
			this.userName = userName;
			return this;
		}
		public Input パスワード(String password){
			this.password = password;
			return this;
		}
		public Input 確認パスワード(String confirmPassword){
			this.confirmPassword = confirmPassword;
			return this;
		}
	}

	public enum InputKey {
		EMAIL         (UserRegisterPage.Id.INPUT_EMAIL),
		ユーザ名      (UserRegisterPage.Id.INPUT_USER_NAME),
		パスワード    (UserRegisterPage.Id.INPUT_PASSWORD),
		確認パスワード(UserRegisterPage.Id.INPUT_CONFIRM_PASSWORD),
		;

		private UserRegisterPage.Id id;

		private InputKey(UserRegisterPage.Id id) {
			this.id = id;
		}

		public UserRegisterPage.Id getId() {
			return id;
		}
	}

}
