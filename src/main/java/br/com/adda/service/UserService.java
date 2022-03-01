package br.com.adda.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.adda.model.Email;
import br.com.adda.model.User;
import br.com.adda.repository.UserRepository;
import br.com.adda.util.EmailSenderUtil;

@Service
public class UserService {

	@Autowired
	UserRepository repository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	EmailSenderUtil emailSenderUtil;

	public ResponseEntity<?> listUsers() {
		try {
			List<User> users = repository.findAll();

			return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Não foi possível buscar os usuários cadastrados! Motivo: " + e.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<?> getUserByEmail(String email) {
		try {
			Optional<User> user = repository.findByEmail(email);
			
			if (user.isEmpty()) {
				return new ResponseEntity<>("O usuário com email " + email + " não foi encontrado!",
						HttpStatus.BAD_REQUEST);
			}

			return new ResponseEntity<>(user.get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Não foi possível consultar usuário pelo email! Motivo: " + e.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<?> addUser(User user) {
		try {

			User registeredUser = repository.findByEmailAndPhone(user.getEmail(), user.getPhone());

			if (Objects.isNull(registeredUser)) {
				user.setPassword(encoder.encode(user.getPassword()));

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

			user.setPassword(encoder.encode(user.getPassword()));

			repository.save(user);
			return new ResponseEntity<>("Usuário atualizado com sucesso!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Não foi possível atualizar este usuário! Motivo: " + e.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<?> resetPassword(String email) {
		try {

			Optional<User> user = repository.findByEmail(email);

			if (user.isEmpty()) {
				return new ResponseEntity<>("Usuário com e-mail " + email + " não cadastrado!", HttpStatus.BAD_REQUEST);
			}

			String random = generateRandomString();

			String newPass = encoder.encode(random);

			user.get().setPassword(newPass);

			repository.save(user.get());

			sendEmail(email, random);

			return new ResponseEntity<>("Senha temporária gerada! Verifique seu e-mail.", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>("Usuário com e-mail " + email + " não cadastrado!", HttpStatus.BAD_REQUEST);
		}
	}

	private void sendEmail(String email, String newPassword) throws Exception {
		Email newEmail = new Email();
		newEmail.setSender("Atendimento ADDA<atendimento.adda@gmail.com>");
		newEmail.setReceiver(email);
		newEmail.setSubject("[ADDA] Senha temporária");
		newEmail.setBody("Sua senha temporária: " + newPassword);

		emailSenderUtil.sendMail(newEmail);
	}

	private static String generateRandomString() {
		int qtdeMaximaCaracteres = 8;
		String[] caracteres = { "0", "1", "b", "2", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g",
				"h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };

		StringBuilder senha = new StringBuilder();

		for (int i = 0; i < qtdeMaximaCaracteres; i++) {
			int posicao = (int) (Math.random() * caracteres.length);
			senha.append(caracteres[posicao]);
		}
		return senha.toString();

	}

}
