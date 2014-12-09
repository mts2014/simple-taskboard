package jp.mts.simpletaskboard.test.helpers;

import java.util.concurrent.TimeUnit;

import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentWebElement;

public abstract class PageBase extends FluentPage {

	protected FluentWebElement awaitUntilPresent(String cssSelector) {
		await().atMost(3, TimeUnit.SECONDS).until(cssSelector).isPresent();
		return findFirst(cssSelector);
	}

}
