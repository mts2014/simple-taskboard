package jp.mts.simpletaskboard.web.controllers;

import jp.mts.simpletaskboard.web.response.RestResponse;
import jp.mts.simpletaskboard.web.response.UserView;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {

	@RequestMapping
	public RestResponse searchByEmail(
			@RequestParam(required=true, value="email") String email){
		UserView userResource = new UserView();
		userResource.setEmail(email);

		RestResponse response = new RestResponse();
		response.addContent("user", userResource);
		return response;
	}

}
