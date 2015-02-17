package jp.mts.simpletaskboard.test.inputkeys;

public enum UserRegisterKey implements jp.mts.simpletaskboard.test.base.UserInputKey{
	EMAIL         ("test@test.jp", "email"),
	ユーザ名      ("yamada taro" , "userName"),
	パスワード    ("pass"        , "password"),
	確認パスワード("pass"        , "confirmPassword"),
	;

	private String defaultValue;
	private String id;

	private UserRegisterKey(String defaultValue, String id) {
		this.defaultValue = defaultValue;
		this.id = id;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public String getId() {
		return id;
	}

}