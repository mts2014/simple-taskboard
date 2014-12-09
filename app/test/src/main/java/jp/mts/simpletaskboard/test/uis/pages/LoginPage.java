package jp.mts.simpletaskboard.test.uis.pages;

import jp.mts.simpletaskboard.test.base.PageBase;

public class LoginPage extends PageBase {

	public void goToUserRegister(){
		awaitUntilPresent("button#register-user-link").click();
		awaitUntilPresent("form[name='userRegisterForm']");
	}

	public boolean hasUserRegisterLink() {
		return findFirst("button#register-user-link") != null;
	}
}
