package jp.mts.simpletaskboard.test.inputkeys;

import jp.mts.simpletaskboard.test.uis.pages.UserRegisterPage;

public enum UserRegisterKey implements jp.mts.simpletaskboard.test.base.UserInputKey{
	EMAIL         ("test@test.jp", UserRegisterPage.Id.INPUT_EMAIL),
	ユーザ名      ("yamada taro" , UserRegisterPage.Id.INPUT_USER_NAME),
	パスワード    ("pass"        , UserRegisterPage.Id.INPUT_PASSWORD),
	確認パスワード("pass"        , UserRegisterPage.Id.INPUT_CONFIRM_PASSWORD),
	;

	private String defaultValue;
	private UserRegisterPage.Id id;

	private UserRegisterKey(String defaultValue, UserRegisterPage.Id id) {
		this.defaultValue = defaultValue;
		this.id = id;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public UserRegisterPage.Id getId() {
		return id;
	}

}