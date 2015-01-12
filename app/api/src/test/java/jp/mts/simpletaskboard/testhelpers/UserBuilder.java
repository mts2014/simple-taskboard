package jp.mts.simpletaskboard.testhelpers;

import jp.mts.simpletaskboard.domain.User;

public class UserBuilder {
	private User user;

	public UserBuilder(){
		this(1);
	}
	public UserBuilder(int id){
		user = new User(id);
		user.setEmail("hoge@test.jp");
	}

	public UserBuilder email(String email){
		this.user.setEmail(email);
		return this;
	}

	public User build(){
		return user;
	}

}
