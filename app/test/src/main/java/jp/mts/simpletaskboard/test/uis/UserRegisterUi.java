package jp.mts.simpletaskboard.test.uis;

import static jp.mts.simpletaskboard.test.inputkeys.UserRegisterKey.*;

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
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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


	public String errorMsg() {
		List<String> errors = userRegisterPage.globalErrors();
		return errors.get(0);
	}

	public boolean canRegister() {
		FluentWebElement btn = userRegisterPage.findFirst("#user-register");
		return !btn.getAttribute("class").contains("disabled");
	}

	public List<String> errorMsg(UserRegisterKey email) {
		FluentWebElement input = userRegisterPage.findFirst("input#email");
		WebElement parantDiv = input.getElement().findElement(By.xpath(".."));
		FluentList<FluentWebElement> list = new FluentWebElement(parantDiv).find("div.tooltip-inner li");

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


}
