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

	private PasswordHashHelper passwordHashHelper = new Sha256PasswordHashHelper();
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcUserRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public User searchByEmail(final String email) {
		List<User> users = jdbcTemplate.query(
			"select id, email, name from users where email = ?",
			new Object[]{ email },
			(rs, rowNum) -> { return mapToUser(rs); }
		);

		return getAtMostOneResult(users);
	}

	@Override
	public void save(User user) {
		jdbcTemplate.update("insert into users(id, email, name, password_hash) values (?,?,?,?)",
				user.getId(), user.getEmail(), user.getName(), passwordHashHelper.hash(user.getPassword()));
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

	@Override
	public User searchByEmailAndPassword(String email, String password) {
		List<User> users = jdbcTemplate.query(
			"select id, email, name from users where email = ? and password_hash = ?",
			new Object[]{ email, passwordHashHelper.hash(password) },
			(rs, rowNum) -> { return mapToUser(rs); }
		);

		return getAtMostOneResult(users);
	}

	private User getAtMostOneResult(List<User> users){
		if(users.isEmpty()){
			return null;
		}
		if(users.size() > 1){
			throw new IllegalStateException();
		}

		return users.get(0);
	}

	@Autowired(required=false)
	public void setPasswordHashHelper(PasswordHashHelper passwordHashHelper) {
		this.passwordHashHelper = passwordHashHelper;
	}

}
