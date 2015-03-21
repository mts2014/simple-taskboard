package jp.mts.simpletaskboard.test.scenarios;

import static jp.mts.simpletaskboard.test.base.UserInputs.*;
import static jp.mts.simpletaskboard.test.uis.TaskboardUi.*;
import jp.mts.simpletaskboard.test.apis.UserApi;
import jp.mts.simpletaskboard.test.base.UI;
import jp.mts.simpletaskboard.test.inputkeys.LoginKey;
import jp.mts.simpletaskboard.test.uis.LoginUi;
import jp.mts.simpletaskboard.test.uis.TaskboardUi;

import org.junit.Test;

/**
 * <pre>
 * ユーザストーリ：
 *
 * 作業グループのメンバーとして
 * 選択中の所属グループのタスクボードを閲覧したい
 * タスクの状況を確認したいからだ
 *
 * </pre>
 */
public class TaskboardDisplayScenario {

	@UI LoginUi loginUi;
	@UI TaskboardUi taskboardUi;
	UserApi userApi = new UserApi();;

	@Test public void
	登録されているタスクがタスクボード上に表示されること(){

		loginUi.ログインする($in()
				.v(LoginKey.ログインＩＤ, "hoge@test.jp")
				.v(LoginKey.パスワード,   "pass"));

		taskboardUi.タスクが表示されている(
				TODOタスク()
					.ID("1")
					.概要("タスクボードの画面モックアップを作成する")
					.担当("山田太郎")
					.期限("2015/04/01(水)"),
				DOINGタスク()
					.ID("2")
					.概要("タスクボードのテーブルを設計する")
					.担当("山田次郎")
					.期限("2015/04/02(木)"),
				DONEタスク()
					.ID("3")
					.概要("タスクボードのサーバAPIのインターフェース仕様を作成する")
					.担当("山田三郎")
					.期限("2015/04/03(金)"));

	}


}
