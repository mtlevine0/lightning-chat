package com.mtlevine0.lightningchat.resource;

import java.security.Principal;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mtlevine0.lightningchat.model.User;
import com.mtlevine0.lightningchat.service.AsyncService;
import com.mtlevine0.lightningchat.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserResource {
	
	@Autowired
	UserService userService;
	
	@Autowired
	AsyncService asyncService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<User> create(@RequestBody User user) {
		return new ResponseEntity<User>(userService.add(user), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> findAll() {
		return new ResponseEntity<List<User>>(userService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user_test", method = RequestMethod.GET)
	@Secured("ROLE_USER")
	public ResponseEntity<Principal> userMethod(Principal principal) {
		return new ResponseEntity<Principal>(principal, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/admin_test", method = RequestMethod.GET)
	@Secured("ROLE_ADMIN")
	public ResponseEntity<String> adminMethod() {
		return new ResponseEntity<String>("ADMIN!", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/async/{message}", method = RequestMethod.GET)
	public ResponseEntity<String> asyncMethod(@PathVariable String message) {
		
		Future<Long> result = asyncService.asyncMethod(message);
		
		while(!result.isDone()) {
			try {
				System.out.println("Result: " + result.get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return new ResponseEntity<String>("ASYNC!", HttpStatus.OK);
	}
	
}
