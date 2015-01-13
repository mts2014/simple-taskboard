package jp.mts.simpletaskboard.test.inputkeys;

public enum UserRegisterKey implements jp.mts.simpletaskboard.test.base.UserInputKey{
	EMAIL("test@test.jp"),
	ユーザ名("yamada taro"),
	パスワード("pass"),
	確認パスワード("pass"),
	;

	private String defaultValue;

	private UserRegisterKey(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getDefaultValue() {
		return defaultValue;
	}
}