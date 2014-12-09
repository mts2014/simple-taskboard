package jp.mts.simpletaskboard.test.lib.appconfig;

import java.io.IOException;
import java.util.Properties;

public class AppConfig {

	public static String value(AppConfigKey key) {
		AppConfigProperties annotation = key.getClass().getAnnotation(AppConfigProperties.class);
		if(annotation == null){
			throw new IllegalArgumentException("key must have AppConfigProperties");
		}
		try {
			Properties prop = new Properties();
			prop.load(AppConfig.class.getResourceAsStream(annotation.value()));
			return prop.getProperty(key.toString());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
