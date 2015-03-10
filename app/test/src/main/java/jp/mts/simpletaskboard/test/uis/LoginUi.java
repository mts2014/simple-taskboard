package jp.mts.simpletaskboard.test.uis;

import static jp.mts.simpletaskboard.test.inputkeys.LoginKey.*;
import jp.mts.simpletaskboard.test.base.AcceptanceUiBase;
import jp.mts.simpletaskboard.test.base.Page;
import jp.mts.simpletaskboard.test.base.UserInputs;
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

	public void ログインする(UserInputs inputs) {
		loginPage.go();

		loginPage
			.email(inputs.v(ログインＩＤ))
			.password(inputs.v(パスワード))
			.login();
	}

}
