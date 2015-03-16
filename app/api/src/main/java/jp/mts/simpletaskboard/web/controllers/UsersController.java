package jp.mts.simpletaskboard.web.controllers;


import static jp.mts.simpletaskboard.web.response.ErrorId.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import jp.mts.simpletaskboard.domain.User;
import jp.mts.simpletaskboard.domain.UserFactory;
import jp.mts.simpletaskboard.domain.UserRepository;
import jp.mts.simpletaskboard.web.request.UserRegisterInput;
import jp.mts.simpletaskboard.web.response.ApiError;
import jp.mts.simpletaskboard.web.response.RestResponse;
import jp.mts.simpletaskboard.web.response.UserView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController extends ControllerBase {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(method=RequestMethod.GET)
	public RestResponse searchByEmail(
			@RequestParam(required=true, value="email") String email,
			HttpServletResponse res){

		User user = userRepository.searchByEmail(email);

		if(user == null){
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return new RestResponse();
		}

		return new RestResponse()
				.addContent("user", new UserView(user));
	}


	@RequestMapping(method=RequestMethod.POST)
	public RestResponse register(
			@RequestBody @Valid UserRegisterInput userRegisterInput) {

		User user = new UserFactory().create();
		user.setEmail(userRegisterInput.email);
		user.setName(userRegisterInput.userName);
		user.setPassword(userRegisterInput.password);
		userRepository.save(user);

		return new RestResponse()
			.addContent("user", new UserView(user));
	}

	@RequestMapping(method=RequestMethod.POST, params="validate")
	public RestResponse validate(
			@RequestBody @Valid UserRegisterInput userRegisterInput,
			HttpServletResponse res) {

		User user = userRepository.searchByEmail(userRegisterInput.email);

		RestResponse response = new RestResponse();
		if(user != null){
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.addError(ApiError.ofErrorId(e001, "email"));
		}

		return response;
	}
}
