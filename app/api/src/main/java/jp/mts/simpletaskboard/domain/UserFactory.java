package jp.mts.simpletaskboard.domain;

import java.util.UUID;

public class UserFactory {

	private IdGenerator idGenerator = new IdGenerator();
	private String email;

	public User create(){
		User user = new User(idGenerator.generate());
		user.setEmail(email);
		return user;
	}

	static class IdGenerator {
		String generate(){
			return UUID.randomUUID().toString();
		}
	}

	public UserFactory email(String email) {
		this.email = email;
		return this;
	}

}
