package jp.mts.simpletaskboard.web.request;

import org.hibernate.validator.constraints.NotEmpty;


public class AuthenticateInput {

	@NotEmpty
	public String authId;

	@NotEmpty
	public String password;

}
