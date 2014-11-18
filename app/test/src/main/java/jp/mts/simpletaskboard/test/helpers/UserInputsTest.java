package jp.mts.simpletaskboard.test.helpers;

import static org.fest.assertions.api.Assertions.*;

import org.junit.Test;

public class UserInputsTest {

	@Test
	public void test_設定した入力値を参照できること() {
		UserInputs actual = UserInputs.$in().v(TestKey.HOGE, "hoge");
		assertThat(actual.v(TestKey.HOGE)).isEqualTo("hoge");
	}

	enum TestKey implements UserInputKey {
		HOGE
	}

}
