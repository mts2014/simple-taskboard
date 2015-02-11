package jp.mts.simpletaskboard.test.uis;

import static jp.mts.simpletaskboard.test.inputkeys.UserRegisterKey.*;
import static org.fest.assertions.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import jp.mts.simpletaskboard.test.base.AcceptanceUiBase;
import jp.mts.simpletaskboard.test.base.Page;
import jp.mts.simpletaskboard.test.base.UserInputs;
import jp.mts.simpletaskboard.test.inputkeys.UserRegisterKey;
import jp.mts.simpletaskboard.test.uis.pages.LoginPage;
import jp.mts.simpletaskboard.test.uis.pages.UserRegisterPage;

import org.fluentlenium.adapter.FluentTest;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;

public class UserRegisterUi extends AcceptanceUiBase {

	@Page
	private LoginPage loginPage;
	@Page
	private UserRegisterPage userRegisterPage;

	public UserRegisterUi(FluentTest fluentTest) {
		super(fluentTest);
	}

	public void ユーザ情報を登録する(UserInputs inputs) {
		loginPage.go();
		loginPage.goToUserRegister();

		userRegisterPage
			.email(inputs.v(EMAIL))
			.userName(inputs.v(ユーザ名))
			.password(inputs.v(パスワード))
			.passwordForConfirm(inputs.v(確認パスワード))
			.register();

	}

	public void ユーザ登録情報を検証する(UserInputs inputs) {
		loginPage.go();
		loginPage.goToUserRegister();

		if(inputs.isSpecified(EMAIL)){
			userRegisterPage.email(inputs.v(EMAIL));
		}

		userRegisterPage.forcusOnRegister();
	}


	public String errorMsg() {
		List<String> errors = userRegisterPage.globalErrors();
		return errors.get(0);
	}

	public boolean canRegister() {
		FluentWebElement btn = userRegisterPage.findFirst("#user-register");
		return !btn.getAttribute("class").contains("disabled");
	}

	public List<String> errorMsg(UserRegisterKey email) {

		FluentList<FluentWebElement> list = userRegisterPage.awaitAndFind(
				userRegisterPage.findParent("input#email"), "div.tooltip-inner li");

		List<String> msgs = new ArrayList<>();
		for(FluentWebElement e : list){
			msgs.add(e.getText());
		}
		return msgs;
	}

	public void ユーザ情報を入力する(UserInputs inputs) {
		loginPage.go();
		loginPage.goToUserRegister();

		userRegisterPage
			.email(inputs.v(EMAIL))
			.userName(inputs.v(ユーザ名))
			.password(inputs.v(パスワード))
			.passwordForConfirm(inputs.v(確認パスワード));

	}

	public void エラーメッセージあり(UserRegisterKey key, String message) {
		assertThat(errorMsg(key)).contains(message);
	}


}
