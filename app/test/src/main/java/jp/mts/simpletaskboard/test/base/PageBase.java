package jp.mts.simpletaskboard.test.base;

import static jp.mts.simpletaskboard.test.base.AcceptanceTestConfig.*;

import java.util.concurrent.TimeUnit;

import jp.mts.simpletaskboard.test.lib.appconfig.AppConfig;

import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentWebElement;

public abstract class PageBase extends FluentPage {

	@Override
	public String getUrl() {
		return AppConfig.value(app_base_url) + "/" + getRelativeUrl();
	}

	protected String getRelativeUrl(){
		return "index.html";
	}

	protected FluentWebElement awaitUntilPresent(String cssSelector) {
		await().atMost(3, TimeUnit.SECONDS).until(cssSelector).isPresent();
		return findFirst(cssSelector);
	}

}
