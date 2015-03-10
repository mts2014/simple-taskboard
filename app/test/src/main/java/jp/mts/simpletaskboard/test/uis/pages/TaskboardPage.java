package jp.mts.simpletaskboard.test.uis.pages;

import static org.fest.assertions.api.Assertions.*;
import jp.mts.simpletaskboard.test.base.PageBase;

public class TaskboardPage extends PageBase {

	@Override
	public void isAt(){
		assertThat(this.getDriver().getCurrentUrl().contains("#/taskboard")).isTrue();
	}

	public String loginUserName() {
		return findFirst("span.login-user").getText();
	}

}
