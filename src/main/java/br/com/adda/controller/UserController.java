package br.com.adda.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import br.com.adda.model.User;
import br.com.adda.repository.UserRepository;

@RestController
public class UserController {
	
	@Autowired
	UserRepository repository;
	
	@GetMapping("/user")
	public List<User> get(){
		return repository.findAll();
	}
	
	@PostMapping("/user")
	public String add(@RequestBody User user) {
		repository.save(user);
		return "Usuário cadastrado com sucesso";
	}
	
	@DeleteMapping("/user/{id}")
	public String remove(@PathVariable Long id) {
		repository.deleteById(id);
		return "Usuário removido com sucesso";
	}
	
	@PutMapping("/user")
	public String update(@RequestBody User user) {
		repository.save(user); 
		return "Usuário atualizado com sucesso";
	}
}
