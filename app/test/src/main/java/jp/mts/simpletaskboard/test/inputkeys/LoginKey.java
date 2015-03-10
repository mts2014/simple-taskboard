package jp.mts.simpletaskboard.test.inputkeys;

import jp.mts.simpletaskboard.test.uis.pages.LoginPage;

public enum LoginKey implements jp.mts.simpletaskboard.test.base.UserInputKey{
	ログインＩＤ  ("test@test.jp", LoginPage.Id.INPUT_LOGINID),
	パスワード    ("pass"        , LoginPage.Id.INPUT_PASSWORD),
	;

	private String defaultValue;
	private LoginPage.Id id;

	private LoginKey(String defaultValue, LoginPage.Id id) {
		this.defaultValue = defaultValue;
		this.id = id;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public LoginPage.Id getId() {
		return id;
	}

}