package jp.mts.simpletaskboard.datasource.jdbc.repository.impl;

import static org.fest.assertions.api.Assertions.*;
import jp.mts.simpletaskboard.domain.User;
import mockit.Deencapsulation;

import org.junit.Test;


public class UserRepositoryImplTest extends JdbcRepositoryImplTestBase{

	UserRepositoryImpl sut;

	public void setup(){
		sut = new UserRepositoryImpl();
		Deencapsulation.setField(sut, jdbcTemplate);
	}

	@Test
	public void test_emailで検索できること() {
		User user = sut.searchByEmail("hoge@test.jp");

		assertThat(user).isNotNull();
	}

	@Test
	public void test_emailで検索ヒットしない場合はnull() {
		User user = sut.searchByEmail("foo@test.jp");

		assertThat(user).isNull();
	}

	@Test(expected = IllegalStateException.class)
	public void test_emailで複数件ヒットする場合は例外() {
		//TODO repository の登録APIにする
		jdbcTemplate.update("insert into users values (1, 'bar@test.jp')");
		jdbcTemplate.update("insert into users values (2, 'bar@test.jp')");

		sut.searchByEmail("bar@test.jp");

	}

}
