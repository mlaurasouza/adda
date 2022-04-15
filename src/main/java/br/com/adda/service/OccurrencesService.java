package br.com.adda.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.querydsl.jpa.impl.JPAQueryFactory;

import br.com.adda.enums.OccurrenceEnum;
import br.com.adda.model.Occurrences;
import br.com.adda.repository.OccurrencesRepository;

@Service
public class OccurrencesService {

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	@Autowired
	OccurrencesRepository repository;
	
	public ResponseEntity<?> listOccurrences() {
		try {
			List<Occurrences> occurrences = repository.findAll();
			
			for (Occurrences oc : occurrences) {
				oc.setCategoryName(OccurrenceEnum.valueOf("O" + oc.getCategoryId()).getOccurenceName());
			}

			return new ResponseEntity<>(occurrences, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Não foi possível buscar as ocorrências cadastradas! Motivo: " + e.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<?> listOccurrencesByUserId(Long userId) {
		try {

			JPAQueryFactory query = new JPAQueryFactory(entityManager);

			br.com.adda.model.QOccurrences occurrence = br.com.adda.model.QOccurrences.occurrences;
			List<Occurrences> occurrencesList = query.selectFrom(occurrence).where(occurrence.userId.eq(userId))
					.fetch();

			return new ResponseEntity<>(occurrencesList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Não foi possível buscar as ocorrências cadastradas! Motivo: " + e.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<?> addOccurrences(Occurrences occurrences) {
		try {
			repository.save(occurrences);
			return new ResponseEntity<>("Ocorrência cadastrada com sucesso!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Não foi possível cadastrar esta ocorrência! Motivo: " + e.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<?> removeOccurrences(Long id) {
		try {
			repository.deleteById(id);
			return new ResponseEntity<>("Ocorrência removida com sucesso!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Não foi possível remover esta ocorrência! Motivo: " + e.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<?> updateOccurrences(Occurrences occurrences) {
		try {
			repository.save(occurrences);
			return new ResponseEntity<>("Ocorrência atualizada com sucesso!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Não foi possível atualizar esta ocorrência! Motivo: " + e.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}
}
