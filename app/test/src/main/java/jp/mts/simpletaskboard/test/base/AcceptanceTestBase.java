package jp.mts.simpletaskboard.test.base;

import java.lang.reflect.Field;

import org.fluentlenium.adapter.FluentTest;
import org.fluentlenium.core.FluentPage;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class AcceptanceTestBase extends FluentTest {

    @Override
    public WebDriver getDefaultDriver() {
        return new ChromeDriver();
    }

	@Before
	public void setupUis(){

		for(Field f : this.getClass().getDeclaredFields()){
			if(f.getAnnotation(UI.class) != null){
				try {
					f.setAccessible(true);
					AcceptanceUiBase ui = (AcceptanceUiBase)f.getType().getConstructor(FluentTest.class).newInstance(this);
					f.set(this, ui);

					for(Field uiField : ui.getClass().getDeclaredFields()){
						if(uiField.getAnnotation(Page.class) != null){
							uiField.setAccessible(true);
							uiField.set(ui, this.createPage((Class<FluentPage>)uiField.getType()));
						}
					}

				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
}
