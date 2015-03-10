package jp.mts.simpletaskboard.test.uis;

import static org.fest.assertions.api.Assertions.*;
import jp.mts.simpletaskboard.test.base.AcceptanceUiBase;
import jp.mts.simpletaskboard.test.base.Page;
import jp.mts.simpletaskboard.test.uis.pages.TaskboardPage;

import org.fluentlenium.adapter.FluentTest;

public class TaskboardUi extends AcceptanceUiBase {

	@Page
	private TaskboardPage taskboardPage;


	public TaskboardUi(FluentTest fluentTest) {
		super(fluentTest);
	}

	public void タスクボードが表示されている() {
		taskboardPage.isAt();
	}

	public void ログインユーザが表示されている(String userName) {
		assertThat(taskboardPage.loginUserName()).isEqualTo(userName);
	}

}
