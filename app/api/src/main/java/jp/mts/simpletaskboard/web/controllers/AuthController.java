package jp.mts.simpletaskboard.web.controllers;


import static jp.mts.simpletaskboard.web.response.ErrorId.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import jp.mts.simpletaskboard.domain.User;
import jp.mts.simpletaskboard.domain.UserRepository;
import jp.mts.simpletaskboard.web.request.AuthenticateInput;
import jp.mts.simpletaskboard.web.response.ApiError;
import jp.mts.simpletaskboard.web.response.AuthView;
import jp.mts.simpletaskboard.web.response.RestResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/authenticate")
public class AuthController extends ControllerBase {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(method=RequestMethod.POST)
	public RestResponse authenticate(
			@RequestBody @Valid  AuthenticateInput input,
			HttpServletResponse res) {

		User user = userRepository.searchByEmailAndPassword(input.authId, input.password);

		if(user == null){
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return new RestResponse().addError(
					ApiError.ofErrorId(e002, "email", "password"));
		}

		return new RestResponse()
			.addContent("auth", new AuthView(user));
	}

	@RequestMapping(params="validate", method=RequestMethod.POST)
	public RestResponse validate(@RequestBody @Valid  AuthenticateInput input) {
		return new RestResponse();
	}
}
