package jp.mts.simpletaskboard.test.uis.pages;

import jp.mts.simpletaskboard.test.base.PageBase;

public class UserRegisterPage extends PageBase {

	public UserRegisterPage email(String email) {
		fill("input#email").with(email);
		return this;
	}
	public UserRegisterPage userName(String userName) {
		fill("input#userName").with(userName);
		return this;
	}
	public UserRegisterPage password(String password) {
		fill("input#password").with(password);
		return this;
	}
	public UserRegisterPage passwordForConfirm(String passwordForConfirm) {
		fill("input#confirmPassword").with(passwordForConfirm);
		return this;
	}

	public void register() {
		click("button#user-register");
	}

}
