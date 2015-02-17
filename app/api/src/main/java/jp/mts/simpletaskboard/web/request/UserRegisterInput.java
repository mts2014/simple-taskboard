package jp.mts.simpletaskboard.web.request;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class UserRegisterInput {

	@NotEmpty
	@Email
	public String email;

	@NotEmpty
	public String userName;

	@NotEmpty
	public String password;


	public String confirmPassword;

}
