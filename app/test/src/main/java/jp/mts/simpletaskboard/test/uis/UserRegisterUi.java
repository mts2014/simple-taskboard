package jp.mts.simpletaskboard.test.uis;

import static jp.mts.simpletaskboard.test.uis.UserRegisterUi.UserInputKey.*;
import jp.mts.simpletaskboard.test.base.AcceptanceUiBase;
import jp.mts.simpletaskboard.test.base.Page;
import jp.mts.simpletaskboard.test.base.UserInputs;
import jp.mts.simpletaskboard.test.uis.pages.LoginPage;
import jp.mts.simpletaskboard.test.uis.pages.UserRegisterPage;

import org.fluentlenium.adapter.FluentTest;

public class UserRegisterUi extends AcceptanceUiBase {

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

	public enum UserInputKey implements jp.mts.simpletaskboard.test.base.UserInputKey{
		EMAIL,
		ユーザ名,
		パスワード,
		確認パスワード,
		;
	}


}
