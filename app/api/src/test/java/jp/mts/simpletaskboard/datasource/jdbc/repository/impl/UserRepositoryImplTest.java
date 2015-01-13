package jp.mts.simpletaskboard.datasource.jdbc.repository.impl;

import static org.fest.assertions.api.Assertions.*;
import jp.mts.simpletaskboard.domain.User;
import jp.mts.simpletaskboard.testhelpers.UserBuilder;
import mockit.Deencapsulation;

import org.junit.Before;
import org.junit.Test;


public class UserRepositoryImplTest extends JdbcRepositoryImplTestBase{

	JdbcUserRepository sut;

	@Before
	public void setup(){
		sut = new JdbcUserRepository();
		Deencapsulation.setField(sut, jdbcTemplate);
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

		User user = new User("2");
		user.setEmail("baz@test.jp");

		sut.save(user);

		User actual = sut.load("2");
		assertThat(actual).isNotNull();
		assertThat(actual.getEmail()).isEqualTo("baz@test.jp");

	}

}
