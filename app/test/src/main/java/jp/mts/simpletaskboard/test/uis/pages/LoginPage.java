package jp.mts.simpletaskboard.test.uis.pages;

import jp.mts.simpletaskboard.test.base.PageBase;
import jp.mts.simpletaskboard.test.base.PageId;

public class LoginPage extends PageBase {

	public static enum Id implements PageId {
		INPUT_LOGINID  ("loginForm_loginId"),
		INPUT_PASSWORD ("loginForm_password"),
		;
		private String id;

		private Id(String id) {
			this.id = id;
		}

		public String getIdValue(){
			return this.id;
		}
	}

	public void goToUserRegister(){
		awaitUntilPresent("button#register-user-link").click();
		awaitUntilPresent("form[name='userRegisterForm']");
	}

	public boolean hasUserRegisterLink() {
		return findFirst("button#register-user-link") != null;
	}

	public LoginPage email(String email) {
		awaitAndFill(Id.INPUT_LOGINID.getIdSelector(), email);
		return this;
	}

	public LoginPage password(String password) {
		awaitAndFill(Id.INPUT_PASSWORD.getIdSelector(), password);
		return this;
	}

	public void login() {
		click("#login-action");
		awaitForSeconds(3);
	}
}
