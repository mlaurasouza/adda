package br.com.adda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.adda.model.User;
import br.com.adda.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Usuário")
public class UserController {

	@Autowired
	private UserService userService;

	@ApiOperation(value = "Lista todos usuários")
	@GetMapping("/user")
	public ResponseEntity<?> get() {
		return userService.listUsers();
	}

	@ApiOperation(value = "Cria um novo usuário")
	@PostMapping("/user")
	public ResponseEntity<?> add(@RequestBody User user) {
		return userService.addUser(user);
	}

	@ApiOperation(value = "Remove um usuário")
	@DeleteMapping("/user/{id}")
	public ResponseEntity<?> remove(@PathVariable Long id) {
		return userService.removeUser(id);
	}

	@ApiOperation(value = "Atualiza um usuário já cadastrado")
	@PutMapping("/user")
	public ResponseEntity<?> update(@RequestBody User user) {
		return userService.updateUser(user);
	}

	@ApiOperation(value = "Redefine a senha")
	@PutMapping("/user/password/reset")
	public ResponseEntity<?> resetPassword(@RequestParam String email) {
		return userService.resetPassword(email);
	}
}
