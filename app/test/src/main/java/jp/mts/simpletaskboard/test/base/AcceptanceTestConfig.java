package jp.mts.simpletaskboard.test.base;

import jp.mts.simpletaskboard.test.lib.appconfig.AppConfigKey;
import jp.mts.simpletaskboard.test.lib.appconfig.AppConfigProperties;

@AppConfigProperties("/acceptance-test.properties")
public enum AcceptanceTestConfig implements AppConfigKey {
	app_base_url,
	api_base_url,
	;
}
