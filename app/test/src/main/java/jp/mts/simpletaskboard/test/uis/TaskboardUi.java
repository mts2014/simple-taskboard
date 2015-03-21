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

	public void タスクが表示されている(Task... tasks) {
		// TODO 自動生成されたメソッド・スタブ

	}

	public static Task TODOタスク(){
		return new Task().status(TaskStatus.TODO);
	}
	public static Task DOINGタスク(){
		return new Task().status(TaskStatus.DOING);
	}
	public static Task DONEタスク(){
		return new Task().status(TaskStatus.DONE);
	}

	public static class Task {
		String ID;
		String 概要;
		String 担当;
		String 期限;
		TaskStatus status;

		public Task ID(String ID) {
			this.ID = ID;
			return this;
		}
		public Task 概要(String 概要) {
			this.概要 = 概要;
			return this;
		}
		public Task 担当(String 担当) {
			this.担当 = 担当;
			return this;
		}
		public Task 期限(String 期限) {
			this.期限 = 期限;
			return this;
		}
		public Task status(TaskStatus status) {
			this.status = status;
			return this;
		}

	}

	public enum TaskStatus {
		TODO,
		DOING,
		DONE,
		;
	}
}
