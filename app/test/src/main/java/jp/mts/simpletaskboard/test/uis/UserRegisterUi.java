package jp.mts.simpletaskboard.test.uis;

import jp.mts.simpletaskboard.test.helpers.UserInputs;

public class UserRegisterUi {

	public UserRegisterUi load() {
		return this;
	}

	public void ユーザ情報を登録する(UserInputs inputs) {

	}

	public enum UserInputKey implements jp.mts.simpletaskboard.test.helpers.UserInputKey{
		ユーザID,
		ユーザ名,
		パスワード,
		;
	}


}
