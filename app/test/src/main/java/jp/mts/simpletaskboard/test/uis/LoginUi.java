package jp.mts.simpletaskboard.test.uis;

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

	public void ログインする(UserInputs v) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
