package jp.mts.simpletaskboard.test.base;

import static org.fest.assertions.api.Assertions.*;
import jp.mts.simpletaskboard.test.base.AcceptanceTestBase;
import jp.mts.simpletaskboard.test.base.AcceptanceUiBase;
import jp.mts.simpletaskboard.test.base.Page;
import jp.mts.simpletaskboard.test.base.UI;

import org.fluentlenium.adapter.FluentTest;
import org.fluentlenium.core.FluentPage;
import org.junit.Before;
import org.junit.Test;

public class AcceptanceTestBaseTest {

	private HogeTest sut;

	@Before
	public void setup(){
		sut = new HogeTest();
		sut.setupUis();
	}

	@Test
	public void test_UIインスタンスをDIできること() {
		assertThat(sut.fooUi).isNotNull();
	}

	@Test
	public void DIされたUIインスタンスは_元のテストインスタンスをもっていること() {
		assertThat(sut.getFooUi().getFluentTest()).isSameAs(sut);
	}

	@Test
	public void DIされたUIインスタンスには_PageインスタンスがDIされていること() {
		assertThat(sut.getFooUi().getHogePage()).isNotNull();
	}


	public static class HogeTest extends AcceptanceTestBase {
		@UI
		private FooUi fooUi;

		public FooUi getFooUi() {
			return fooUi;
		}
	}

	public static class FooUi extends AcceptanceUiBase {

		@Page
		private HogePage hogePage;

		public FooUi(FluentTest fluentTest) {
			super(fluentTest);
		}

		public HogePage getHogePage() {
			return hogePage;
		}
	}

	public static class HogePage extends FluentPage { }
}