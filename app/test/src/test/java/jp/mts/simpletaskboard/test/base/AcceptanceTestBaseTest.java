package jp.mts.simpletaskboard.test.base;

import static org.fest.assertions.api.Assertions.*;

import org.fluentlenium.adapter.FluentTest;
import org.fluentlenium.core.FluentPage;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class AcceptanceTestBaseTest {

	public static class 継承のないテストクラス {

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
	}

	public static class 継承したテストクラス {

		private FugaTest sut;

		@Before
		public void setup(){
			sut = new FugaTest();
			sut.setupUis();
		}

		@Test
		public void test_UIインスタンスをDIできること() {
			assertThat(sut.fooUi).isNotNull();
			assertThat(sut.bazUi).isNotNull();
		}

		@Test
		public void DIされたUIインスタンスは_元のテストインスタンスをもっていること() {
			assertThat(sut.getFooUi().getFluentTest()).isSameAs(sut);
			assertThat(sut.getBazUi().getFluentTest()).isSameAs(sut);
		}

		@Test
		public void DIされたUIインスタンスには_PageインスタンスがDIされていること() {
			assertThat(sut.getFooUi().getHogePage()).isNotNull();

			assertThat(sut.getBazUi().getHogePage()).isNotNull();
			assertThat(sut.getBazUi().getBarPage()).isNotNull();
		}
	}


	private static class HogeTest extends AcceptanceTestBase {
		@UI
		FooUi fooUi;

		public FooUi getFooUi() {
			return fooUi;
		}
	}
	private static class FugaTest extends HogeTest {
		@UI
		BazUi bazUi;

		public BazUi getBazUi() {
			return bazUi;
		}
	}

	private static class FooUi extends AcceptanceUiBase {

		@Page
		HogePage hogePage;

		public FooUi(FluentTest fluentTest) {
			super(fluentTest);
		}

		public HogePage getHogePage() {
			return hogePage;
		}
	}

	private static class BazUi extends FooUi {

		@Page
		BarPage barPage;

		public BazUi(FluentTest fluentTest) {
			super(fluentTest);
		}

		public BarPage getBarPage() {
			return barPage;
		}

	}

	private static class HogePage extends FluentPage { }
	private static class BarPage extends FluentPage { }
}
