package jp.mts.simpletaskboard.datasource.jdbc.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import jp.mts.simpletaskboard.domain.User;
import jp.mts.simpletaskboard.domain.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public User searchByEmail(final String email) {
		List<User> users = jdbcTemplate.query(
				"select id, email from users where email = ?", new Object[]{ email },
				new RowMapper<User>(){
					@Override
					public User mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						User user = new User(rs.getString("id"));
						user.setEmail(rs.getString("email"));
						return user;
					}
				});

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
		jdbcTemplate.update("insert into users values (?,?)", user.getId(), user.getEmail());
	}

	@Override
	public User load(int id) {
		return jdbcTemplate.queryForObject("select id, email from users where id = ?",
				new Object[]{id},
				new RowMapper<User>(){
					@Override
					public User mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						User user = new User(rs.getString("id"));
						user.setEmail(rs.getString("email"));
						return user;
					}
				});
	}


}
