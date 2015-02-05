package jp.mts.simpletaskboard.web.request;

import org.hibernate.validator.constraints.NotEmpty;

public class UserRegisterInput {

	@NotEmpty
	public String email;
	public String name;
	public String password;
	public String confirmPassword;

}
