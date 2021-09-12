package br.com.adda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import br.com.adda.model.User;
import br.com.adda.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/user")
	public ResponseEntity<?> get() {
		return userService.listUsers();
	}

	@PostMapping("/user")
	public ResponseEntity<?> add(@RequestBody User user) {
		return userService.addUser(user);
	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<?> remove(@PathVariable Long id) {
		return userService.removeUser(id);
	}

	@PutMapping("/user")
	public ResponseEntity<?> update(@RequestBody User user) {
		return userService.updateUser(user);
	}
}
