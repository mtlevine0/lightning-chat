package com.mtlevine0.lightningchat.resource;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mtlevine0.lightningchat.model.User;
import com.mtlevine0.lightningchat.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserResource {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<User> create(@RequestBody User user) {
		return new ResponseEntity<User>(userService.add(user), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> findAll() {
		return new ResponseEntity<List<User>>(userService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	@Secured("ROLE_USER")
	public ResponseEntity<Principal> userMethod(Principal principal) {
		return new ResponseEntity<Principal>(principal, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	@Secured("ROLE_ADMIN")
	public ResponseEntity<String> adminMethod() {
		return new ResponseEntity<String>("ADMIN!", HttpStatus.OK);
	}
	
}
