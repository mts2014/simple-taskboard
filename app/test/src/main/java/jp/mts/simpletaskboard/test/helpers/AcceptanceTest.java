package jp.mts.simpletaskboard.test.helpers;

import java.lang.reflect.Field;

import org.fluentlenium.adapter.FluentTest;
import org.junit.Before;

public abstract class AcceptanceTest extends FluentTest {

	@Before
	public void setupUis(){

		for(Field f : this.getClass().getDeclaredFields()){
			if(f.getAnnotation(UI.class) != null){
				try {
					f.setAccessible(true);
					f.set(this, f.getType().newInstance());
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
}
