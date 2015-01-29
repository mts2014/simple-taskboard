package jp.mts.simpletaskboard.test.base;

import static jp.mts.simpletaskboard.test.base.AcceptanceTestConfig.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import jp.mts.simpletaskboard.test.lib.appconfig.AppConfig;

import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.TimeoutException;

public abstract class PageBase extends FluentPage {

	@Override
	public String getUrl() {
		return AppConfig.value(app_base_url) + "/" + getRelativeUrl();
	}

	protected String getRelativeUrl(){
		return "index.html";
	}

	protected FluentWebElement awaitUntilPresent(String cssSelector) {
		await().atMost(5, TimeUnit.SECONDS).until(cssSelector).isPresent();
		return findFirst(cssSelector);
	}

	protected void awaitForSeconds(int timeInSeconds) {
		try{
			await().atMost(timeInSeconds * 1000)
				.until("#selenium-dummy-selector").isPresent();
		}catch(TimeoutException e){}
	}

	public List<String> globalErrors() {
		awaitUntilPresent("ul.global-error-msgs p");

		FluentList<FluentWebElement> errorMsgs = findFirst("ul.global-error-msgs").find("p");
		List<String> errors = new ArrayList<>();
		for(FluentWebElement errorMsg : errorMsgs){
			errors.add(errorMsg.getText());
		}
		return errors;
	}

	protected void forcusTo(String cssSelector){
		executeScript("$('" + cssSelector + "').focus()");
	}

}
