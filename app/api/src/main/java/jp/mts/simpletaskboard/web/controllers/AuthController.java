package jp.mts.simpletaskboard.web.controllers;


import jp.mts.simpletaskboard.domain.User;
import jp.mts.simpletaskboard.domain.UserRepository;
import jp.mts.simpletaskboard.web.request.AuthenticateInput;
import jp.mts.simpletaskboard.web.response.AuthView;
import jp.mts.simpletaskboard.web.response.RestResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value="/authenticate", method=RequestMethod.POST)
	public RestResponse authenticate(
			@RequestBody AuthenticateInput input) {

		User user = userRepository.searchByEmailAndPassword(input.authId, input.password);

		return new RestResponse()
			.addContent("auth", new AuthView(user));
	}

}
