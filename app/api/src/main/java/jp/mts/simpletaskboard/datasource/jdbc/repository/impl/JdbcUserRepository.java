package jp.mts.simpletaskboard.datasource.jdbc.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import jp.mts.simpletaskboard.domain.User;
import jp.mts.simpletaskboard.domain.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcUserRepository implements UserRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public User searchByEmail(final String email) {
		List<User> users = jdbcTemplate.query(
			"select id, email, name from users where email = ?",
			new Object[]{ email },
			(rs, rowNum) -> { return mapToUser(rs); }
		);

		if(users.isEmpty()){
			return null;
		}
		if(users.size() > 1){
			throw new IllegalStateException();
		}

		return users.get(0);
	}

	@Override
	public void save(User user) {
		jdbcTemplate.update("insert into users(id, email, name) values (?,?,?)", user.getId(), user.getEmail(), user.getName());
	}

	@Override
	public User load(String id) {
		return jdbcTemplate.queryForObject(
			"select id, email, name from users where id = ?",
			new Object[]{ id },
			(rs, rowNum) -> { return mapToUser(rs); }
		);
	}

	private User mapToUser(ResultSet rs) throws SQLException{
		User user = new User(rs.getString("id"));
		user.setEmail(rs.getString("email"));
		user.setName(rs.getString("name"));
		return user;
	}

}
