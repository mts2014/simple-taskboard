package jp.mts.simpletaskboard.test.base;

import static jp.mts.simpletaskboard.test.base.AcceptanceTestConfig.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import jp.mts.simpletaskboard.test.lib.appconfig.AppConfig;

import org.fluentlenium.core.Fluent;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import com.google.common.base.Predicate;

public abstract class PageBase extends FluentPage {

	@Override
	public String getUrl() {
		return AppConfig.value(app_base_url) + "/" + getRelativeUrl();
	}

	protected String getRelativeUrl(){
		return "#/";
	}

	protected FluentWebElement awaitUntilPresent(String cssSelector) {
		await().atMost(5, TimeUnit.SECONDS).until(cssSelector).isPresent();
		return findFirst(cssSelector);
	}

	protected void awaitAndFill(String cssSelector, String value){
		awaitUntilPresent(cssSelector);
		fill(cssSelector).with(value);
		blur();
	}

	public FluentList<FluentWebElement> awaitAndFindMessageOn(String inputId){
		String xpath = "//*[@id=\"" + inputId + "\"]/..//div[contains(@class,'tooltip-inner')]//li";

		await().atMost(5, TimeUnit.SECONDS).until((Predicate<Fluent>) input -> {
			List<WebElement> found = findFirst("body").getElement().findElements(By.xpath(xpath));
			return !found.isEmpty();
		});

		List<FluentWebElement> results = findFirst("body").getElement().findElements(By.xpath(xpath))
			.stream()
			.map(webElement -> new FluentWebElement(webElement))
			.collect(Collectors.toList());

		return new FluentList<FluentWebElement>(results);
	}
	public FluentWebElement findParentById(String id){
		return new FluentWebElement(
			findFirst("body").getElement()
				.findElement(By.xpath("//*[@id=\"" + id + "\"]/.."))
		);
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

	protected void blur(){
		executeScript("$(':focus').blur()");
	}

	protected void forcusTo(String cssSelector){
		executeScript("$('" + cssSelector + "').focus()");
	}

}
