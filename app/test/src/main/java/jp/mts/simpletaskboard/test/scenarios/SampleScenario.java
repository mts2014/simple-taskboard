package jp.mts.simpletaskboard.test.scenarios;

import static org.fest.assertions.api.Assertions.*;

import org.fluentlenium.adapter.FluentTest;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class SampleScenario extends FluentTest {

    @Override
    public WebDriver getDefaultDriver() {
        return new HtmlUnitDriver();
    }

	@Test
	public void test(){
		goTo("http://192.168.55.11:9000");
		assertThat(find("h3").getText()).isEqualTo("webui");
	}
}
