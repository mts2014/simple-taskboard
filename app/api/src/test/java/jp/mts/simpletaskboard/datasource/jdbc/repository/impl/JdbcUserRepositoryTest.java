package jp.mts.simpletaskboard.datasource.jdbc.repository.impl;

import static org.fest.assertions.api.Assertions.*;
import jp.mts.simpletaskboard.domain.User;
import jp.mts.simpletaskboard.testhelpers.UserBuilder;
import mockit.Expectations;
import mockit.Mocked;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class JdbcUserRepositoryTest extends JdbcRepositoryTestBase{

	JdbcUserRepository sut;
	@Mocked PasswordHashHelper passwordHashHelper;

	@Before
	public void setup(){
		sut = new JdbcUserRepository(jdbcTemplate);
		sut.setPasswordHashHelper(passwordHashHelper);
	}

	@Test
	public void test_emailで検索できること() {
		User user = sut.searchByEmail("hoge@test.jp");

		assertThat(user.getId()).isEqualTo("1");
		assertThat(user.getEmail()).isEqualTo("hoge@test.jp");
	}

	@Test
	public void test_emailで検索ヒットしない場合はnull() {
		User user = sut.searchByEmail("foo@test.jp");

		assertThat(user).isNull();
	}

	@Test(expected = IllegalStateException.class)
	public void test_emailで複数件ヒットする場合は例外() {
		sut.save(new UserBuilder("100").email("bar@test.jp").build());
		sut.save(new UserBuilder("101").email("bar@test.jp").build());

		sut.searchByEmail("bar@test.jp");
	}

	@Test
	public void test_ユーザ情報を登録できる(){

		new Expectations() {{
			passwordHashHelper.hash("pass");
				result = "password_hashed_value";
		}};

		User user = new User("2");
		user.setEmail("baz@test.jp");
		user.setName("taro");
		user.setPassword("pass");

		sut.save(user);

		User actual = sut.load("2");
		assertThat(actual.getEmail()).isEqualTo("baz@test.jp");
		assertThat(actual.getName()).isEqualTo("taro");

	}

	@Test
	public void test_emailとパスワードで検索できる(){

		new Expectations() {{
			passwordHashHelper.hash("pass");
				result = "password_hashed_value";
		}};

		User user = sut.searchByEmailAndPassword("hoge@test.jp", "pass");

		assertThat(user.getId()).isEqualTo("1");
		assertThat(user.getEmail()).isEqualTo("hoge@test.jp");

	}

}
