package jp.mts.simpletaskboard.test.uis.pages;

import jp.mts.simpletaskboard.test.base.PageBase;
import jp.mts.simpletaskboard.test.base.PageId;

public class UserRegisterPage extends PageBase {
	public static enum Id implements PageId {
		INPUT_EMAIL           ("userRegisterForm_email"),
		INPUT_USER_NAME       ("userRegisterForm_userName"),
		INPUT_PASSWORD        ("userRegisterForm_password"),
		INPUT_CONFIRM_PASSWORD("userRegisterForm_confirmPassword"),
		ACTION_REGISTER       ("user-register"),
		;
		private String id;

		private Id(String id) {
			this.id = id;
		}

		public String getIdValue(){
			return this.id;
		}
	}

	public UserRegisterPage email(String email) {
		awaitAndFill(Id.INPUT_EMAIL.getIdSelector(), email);
		return this;
	}
	public UserRegisterPage userName(String userName) {
		awaitAndFill(Id.INPUT_USER_NAME.getIdSelector(), userName);
		return this;
	}
	public UserRegisterPage password(String password) {
		awaitAndFill(Id.INPUT_PASSWORD.getIdSelector(), password);
		return this;
	}
	public UserRegisterPage passwordForConfirm(String passwordForConfirm) {
		awaitAndFill(Id.INPUT_CONFIRM_PASSWORD.getIdSelector(), passwordForConfirm);
		return this;
	}

	public void register() {
		click(Id.ACTION_REGISTER.getIdSelector());
		awaitForSeconds(3);
	}

	public void valudateWithForcusOut() {
		blur();
		awaitForSeconds(3);
	}
	public void forcusOn(Id inputId) {
		forcusTo(inputId.getIdSelector());
	}

}
