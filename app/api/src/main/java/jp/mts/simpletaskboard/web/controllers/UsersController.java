package jp.mts.simpletaskboard.web.controllers;

import javax.servlet.http.HttpServletResponse;

import jp.mts.simpletaskboard.domain.User;
import jp.mts.simpletaskboard.domain.UserFactory;
import jp.mts.simpletaskboard.domain.UserRepository;
import jp.mts.simpletaskboard.web.request.UserRegisterInput;
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
public class UsersController {

	@Autowired
	private UserRepository UserRepository;

	@RequestMapping(method=RequestMethod.GET)
	public RestResponse searchByEmail(
			@RequestParam(required=true, value="email") String email,
			HttpServletResponse res){

		User user = UserRepository.searchByEmail(email);

		if(user == null){
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return new RestResponse();
		}

		RestResponse response = new RestResponse();
		response.addContent("user", new UserView(user));
		return response;
	}


	@RequestMapping(method=RequestMethod.POST)
	public RestResponse register(
			@RequestBody UserRegisterInput userReauest) {

		User user = new UserFactory().create();
		user.setEmail(userReauest.email);
		UserRepository.save(user);

		RestResponse response = new RestResponse();
		response.addContent("user", new UserView(user));
		return response;
	}

}
