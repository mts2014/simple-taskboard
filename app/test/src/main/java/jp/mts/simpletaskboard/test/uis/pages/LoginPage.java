package jp.mts.simpletaskboard.test.uis.pages;

import jp.mts.simpletaskboard.test.helpers.PageBase;

public class LoginPage extends PageBase {

	@Override
	public String getUrl() {
		return "http://192.168.77.11/index.html#/";
	}

	public void goToUserRegister(){
		awaitUntilPresent("button#register-user-link").click();
		awaitUntilPresent("form[name='userRegisterForm']");
	}

	public boolean hasUserRegisterLink() {
		return findFirst("button#register-user-link") != null;
	}
}
