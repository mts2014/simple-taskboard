package jp.mts.simpletaskboard.test.uis.pages;

import jp.mts.simpletaskboard.test.base.PageBase;

public class UserRegisterPage extends PageBase {

	public UserRegisterPage email(String email) {
		awaitAndFill("input#email", email);
		return this;
	}
	public UserRegisterPage userName(String userName) {
		awaitAndFill("input#userName", userName);
		return this;
	}
	public UserRegisterPage password(String password) {
		awaitAndFill("input#password", password);
		return this;
	}
	public UserRegisterPage passwordForConfirm(String passwordForConfirm) {
		awaitAndFill("input#confirmPassword", passwordForConfirm);
		return this;
	}

	public void register() {
		click("button#user-register");
		awaitForSeconds(1);
	}

	public void forcusOnRegister() {
		forcusTo("button#user-register");
		awaitForSeconds(3);
	}

}
