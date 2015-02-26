package jp.mts.simpletaskboard.test.uis.pages;

import jp.mts.simpletaskboard.test.base.PageBase;
import jp.mts.simpletaskboard.test.base.PageId;

public class LoginPage extends PageBase {

	public static enum Id implements PageId {
		INPUT_EMAIL           ("loginForm_email"),
		INPUT_PASSWORD        ("loginForm_password"),
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
}
