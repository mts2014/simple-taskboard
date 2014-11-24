package jp.mts.simpletaskboard.test.helpers;

import static org.fest.assertions.api.Assertions.*;

import org.junit.Test;

public class AcceptanceTestTest {

	@Test
	public void test_UIインスタンスをDIできること() {

		HogeTest sut = new HogeTest();
		sut.setupUis();
		assertThat(sut.fooUi).isNotNull();
	}


	public static class HogeTest extends AcceptanceTest {
		@UI
		private FooUi fooUi;
	}

	public static class FooUi {}
}
