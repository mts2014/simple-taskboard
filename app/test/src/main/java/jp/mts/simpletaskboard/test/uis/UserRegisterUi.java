package jp.mts.simpletaskboard.test.uis;

import static jp.mts.simpletaskboard.test.uis.UserRegisterUi.UserInputKey.*;

import java.util.List;

import jp.mts.simpletaskboard.test.base.AcceptanceUiBase;
import jp.mts.simpletaskboard.test.base.Page;
import jp.mts.simpletaskboard.test.base.UserInputs;
import jp.mts.simpletaskboard.test.uis.pages.LoginPage;
import jp.mts.simpletaskboard.test.uis.pages.UserRegisterPage;

import org.fluentlenium.adapter.FluentTest;

public class UserRegisterUi extends AcceptanceUiBase {

	public enum UserInputKey implements jp.mts.simpletaskboard.test.base.UserInputKey{
		EMAIL("test@test.jp"),
		ユーザ名("yamada taro"),
		パスワード("pass"),
		確認パスワード("pass"),
		;

		private String defaultValue;

		private UserInputKey(String defaultValue) {
			this.defaultValue = defaultValue;
		}

		public String getDefaultValue() {
			return defaultValue;
		}
	}

	@Page
	private LoginPage loginPage;
	@Page
	private UserRegisterPage userRegisterPage;

	public UserRegisterUi(FluentTest fluentTest) {
		super(fluentTest);
	}

	public void ユーザ情報を登録する(UserInputs inputs) {
		loginPage.go();
		loginPage.goToUserRegister();

		userRegisterPage
			.email(inputs.v(EMAIL))
			.userName(inputs.v(ユーザ名))
			.password(inputs.v(パスワード))
			.passwordForConfirm(inputs.v(確認パスワード))
			.register();

	}


	public String errorMsg() {
		List<String> errors = userRegisterPage.globalErrors();
		return errors.get(0);
	}


}
