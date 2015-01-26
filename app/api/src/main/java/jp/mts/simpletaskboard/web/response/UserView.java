package jp.mts.simpletaskboard.web.response;

import jp.mts.simpletaskboard.domain.User;

public class UserView {

	private User user;

	public UserView(User user) {
		this.user = user;
	}

	public String getEmail(){
		return user.getEmail();
	}
	public String getName(){
		return user.getName();
	}

}
