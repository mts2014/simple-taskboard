package jp.mts.simpletaskboard.test.base;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

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
		try {
			for(Field uiField : this.getFieldsIncludeAncestorsWith(this.getClass(), UI.class)){
				uiField.setAccessible(true);
				AcceptanceUiBase ui = (AcceptanceUiBase)uiField.getType().getConstructor(FluentTest.class).newInstance(this);
				uiField.set(this, ui);

				for(Field pageField : this.getFieldsIncludeAncestorsWith(ui.getClass(), Page.class)){
					pageField.setAccessible(true);
					pageField.set(ui, this.createPage((Class<FluentPage>)pageField.getType()));
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private <A extends Annotation> List<Field> getFieldsIncludeAncestorsWith(Class<?> targetClass, Class<A> annotationType){
		List<Field> fields = new ArrayList<>();

		Class<?> clazz = targetClass;
		while(clazz != null && !clazz.equals(Object.class)){
			for(Field f : clazz.getDeclaredFields()){
				if(f.getAnnotation(annotationType) != null){
					fields.add(f);
				}
			}
			clazz = clazz.getSuperclass();
		}

		return fields;
	}
}
