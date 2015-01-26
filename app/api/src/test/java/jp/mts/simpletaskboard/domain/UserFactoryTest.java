package jp.mts.simpletaskboard.domain;

import static org.fest.assertions.api.Assertions.*;
import mockit.Deencapsulation;
import mockit.NonStrictExpectations;

import org.junit.Test;

public class UserFactoryTest {

	UserFactory sut = new UserFactory();

	@Test
	public void test_ユーザIDを生成できる() {
		final UserFactory.IdGenerator idGen = new UserFactory.IdGenerator();
		new NonStrictExpectations(idGen) {{
			idGen.generate(); result = "hoge";
		}};
		Deencapsulation.setField(sut, idGen);

		User user = sut.create();

		assertThat(user.getId()).isEqualTo("hoge");
	}

	@Test
	public void test_指定したプロパティで生成できること() {

		User user = sut.email("hoge@test.jp").create();

		assertThat(user.getEmail()).isEqualTo("hoge@test.jp");
	}

}
