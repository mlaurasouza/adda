package br.com.adda.service;

import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.querydsl.jpa.impl.JPAQueryFactory;

import br.com.adda.model.Occurrences;
import br.com.adda.model.SosContact;
import br.com.adda.repository.SosContactRepository;

@Service
public class SosContactService {
	
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	
	@Autowired
	SosContactRepository repository;
	
	public ResponseEntity<?> listSosContact() {
		try {
			List<SosContact> sosContact = repository.findAll();

			return new ResponseEntity<>(sosContact, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Não foi possível buscar os contatos de emergência cadastrados! Motivo: " + e.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<?> listSosContactByUserId(Long userId) {
		try {
			JPAQueryFactory query = new JPAQueryFactory(entityManager);

			br.com.adda.model.QSosContact sosContact = br.com.adda.model.QSosContact.sosContact;
			List<SosContact> sosContactList = query.selectFrom(sosContact).where(sosContact.userId.eq(userId))
					.fetch();

			return new ResponseEntity<>(sosContactList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Não foi possível buscar os contatos de emergência cadastrados! Motivo: " + e.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<?> addSosContact(SosContact sosContact) {
		try {

			SosContact registeredSosContact = repository.findByUserIdAndPhone(sosContact.getUserId(), sosContact.getPhone());

			if (Objects.isNull(registeredSosContact)) {
				repository.save(sosContact);
				return new ResponseEntity<>("Contato de emergência cadastrado com sucesso!", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Contato de emergência já cadastrado!", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Não foi possível cadastrar este contato de emergência! Motivo: " + e.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<?> removeSosContact(Long id) {
		try {
			repository.deleteById(id);
			return new ResponseEntity<>("Contato de emergência removido com sucesso!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Não foi possível remover este contato de emergência! Motivo: " + e.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<?> updateSosContact(SosContact sosContact) {
		try {
			repository.save(sosContact);
			return new ResponseEntity<>("Contato de emergência atualizado com sucesso!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Não foi possível atualizar este contato de emergência! Motivo: " + e.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}
}
