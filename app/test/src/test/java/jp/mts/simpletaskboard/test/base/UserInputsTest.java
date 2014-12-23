package jp.mts.simpletaskboard.test.base;

import static org.fest.assertions.api.Assertions.*;

import org.junit.Test;

public class UserInputsTest {

	@Test
	public void test_設定した入力値を参照できること() {
		UserInputs actual = UserInputs.$in().v(TestKey.HOGE, "hoge");
		assertThat(actual.v(TestKey.HOGE)).isEqualTo("hoge");
	}
	@Test
	public void test_設定していない場合はデフォルト値を参照できること() {
		UserInputs actual = UserInputs.$in();
		assertThat(actual.v(TestKey.HOGE)).isEqualTo("default");
	}

	enum TestKey implements UserInputKey {
		HOGE;

		@Override
		public String getDefaultValue() {
			return "default";
		}
	}

}
