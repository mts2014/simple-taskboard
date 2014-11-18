package jp.mts.simpletaskboard.test.scenarios;

import static jp.mts.simpletaskboard.test.helpers.UserInputs.*;
import static jp.mts.simpletaskboard.test.uis.UserRegisterUi.UserInputKey.*;
import static org.fest.assertions.api.Assertions.*;
import jp.mts.simpletaskboard.test.helpers.AcceptanceTest;
import jp.mts.simpletaskboard.test.helpers.UI;
import jp.mts.simpletaskboard.test.uis.LoginUi;
import jp.mts.simpletaskboard.test.uis.UserRegisterUi;

import org.junit.Test;

/**
 * <pre>
 * 新規利用者として
 * ユーザの登録を行いたい
 * タスクボードを利用したいからだ
 * </pre>
 */
public class UserRegisterScenario extends AcceptanceTest {

	@UI
	private UserRegisterUi userRegisterUi;
	@UI
	private LoginUi loginUi;

    @Test
    public void ユーザを新規に登録できること(){

    	userRegisterUi.load()
    		.ユーザ情報を登録する($in()
    			.v(ユーザID, "taro")
    			.v(ユーザ名, "太郎")
    			.v(パスワード, "pass"));

    	assertThat(loginUi.isAtLogin()).isEqualTo(true);

    	//TODO ログイン機能ができたら下記検証をする
//    	boolean isLogined = loginUi.ログインする();
//    	assertThat(isLogined).isEqualTo(true);
    }

    /**
     * 重複したユーザは登録できないこと
     */


}
