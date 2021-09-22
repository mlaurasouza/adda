package br.com.adda.service;

import java.util.List;
import java.util.Objects;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.adda.model.User;
import br.com.adda.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository repository;

	public ResponseEntity<?> listUsers() {
		try {
			List<User> users = repository.findAll();

			return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Não foi possível buscar os usuários cadastrados! Motivo: " + e.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<?> addUser(User user) {
		try {

			User registeredUser = repository.findByEmailAndPhone(user.getEmail(), user.getPhone());

			if (Objects.isNull(registeredUser)) {
				user.setPassword(encodedPassword(user.getPassword()));
				repository.save(user);
				return new ResponseEntity<>("Usuário cadastrado com sucesso!", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Usuário já cadastrado!", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Não foi possível cadastrar este usuário! Motivo: " + e.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<?> removeUser(Long id) {
		try {
			repository.deleteById(id);
			return new ResponseEntity<>("Usuário removido com sucesso!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Não foi possível remover este usuário! Motivo: " + e.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<?> updateUser(User user) {
		try {

			User registeredUser = repository.findByEmailAndPhone(user.getEmail(), user.getPhone());
			
			if (Objects.isNull(user.getId())) {
				user.setId(registeredUser.getId());
			}
			
			String decodedPassword = new String(Base64.decodeBase64(registeredUser.getPassword().getBytes()));

			if (decodedPassword.equals(user.getPassword())) {
				user.setPassword(registeredUser.getPassword());
			} else {
				user.setPassword(encodedPassword(user.getPassword()));
			}

			repository.save(user);
			return new ResponseEntity<>("Usuário atualizado com sucesso!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Não foi possível atualizar este usuário! Motivo: " + e.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	private String encodedPassword(String password) {
		return new String(Base64.encodeBase64(password.getBytes()));

	}
	
	public User getByEmail(String email) {
		User user = new User();
		return user;
	}

}
