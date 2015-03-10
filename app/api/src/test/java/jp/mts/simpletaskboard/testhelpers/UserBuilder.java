package jp.mts.simpletaskboard.testhelpers;

import jp.mts.simpletaskboard.domain.User;

public class UserBuilder {
	private User user;

	public UserBuilder(){
		this("0");
	}
	public UserBuilder(String id){
		user = new User(id);
		user.setEmail("hoge@test.jp");
	}

	public UserBuilder email(String email){
		this.user.setEmail(email);
		return this;
	}
	public UserBuilder name(String name) {
		this.user.setName(name);
		return this;
	}

	public User build(){
		return user;
	}

}
