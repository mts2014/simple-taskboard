package jp.mts.simpletaskboard.web.request;

import jp.mts.simpletaskboard.web.request.validation.Password;
import jp.mts.simpletaskboard.web.request.validation.ValidPasswordConfirm;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@ValidPasswordConfirm
public class UserRegisterInput implements ValidPasswordConfirm.ConfirmPasswordInput {

	@NotEmpty
	@Email
	public String email;

	@NotEmpty
	public String userName;

	@NotEmpty
	@Password
	public String password;

	@NotEmpty
	public String confirmPassword;

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getConfirmPassword() {
		return this.confirmPassword;
	}

}
