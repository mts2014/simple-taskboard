package jp.mts.simpletaskboard.test.uis;

import jp.mts.simpletaskboard.test.helpers.AcceptanceUi;
import jp.mts.simpletaskboard.test.helpers.Page;
import jp.mts.simpletaskboard.test.uis.pages.LoginPage;

import org.fluentlenium.adapter.FluentTest;

public class LoginUi extends AcceptanceUi {

	@Page
	private LoginPage loginPage;

	public LoginUi(FluentTest fluentTest) {
		super(fluentTest);
	}

	public boolean isAtLogin() {
		return loginPage.hasUserRegisterLink();
	}

}
