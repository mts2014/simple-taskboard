package jp.mts.simpletaskboard.web.controllers;

import jp.mts.simpletaskboard.web.resources.UserResource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {

	@RequestMapping
	public UserResource searchByEmail(
			@RequestParam(required=true, value="email") String email){
		UserResource userResource = new UserResource();
		userResource.setEmail(email);
		return userResource;
	}

}
