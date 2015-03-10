package jp.mts.simpletaskboard.web.response;

import jp.mts.simpletaskboard.domain.User;

public class AuthView {

	private User user;

	public AuthView(User user) {
		this.user = user;
	}

	public String getAuthId(){
		return user.getEmail();
	}
	public String getName(){
		return user.getName();
	}

}
