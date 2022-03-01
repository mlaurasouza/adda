package br.com.adda.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.adda.model.Occurrences;
import br.com.adda.repository.OccurrencesRepository;

@Service
public class OccurrencesService {

	@Autowired
	OccurrencesRepository repository;

	public ResponseEntity<?> listOccurrences() {
		try {
			List<Occurrences> occurrences = repository.findAll();
			
			Calendar calendar = Calendar.getInstance();
			
			for (Occurrences occurrence: occurrences) {
				calendar.setTime(occurrence.getOccurrenceDate());
				Date date = calendar.getTime();
				occurrence.setOccurrenceDate(date);
			}

			return new ResponseEntity<>(occurrences, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Não foi possível buscar as ocorrências cadastradas! Motivo: " + e.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<?> addOccurrences(Occurrences occurrences) {
		try {
//			occurrences.getOccurrenceDate().setTime(occurrences.getOccurrenceDate().getTime() + 10800000);
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
			occurrences.getOccurrenceDate().setTime(occurrences.getOccurrenceDate().getTime() + 10800000);
			repository.save(occurrences);
			return new ResponseEntity<>("Ocorrência atualizada com sucesso!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Não foi possível atualizar esta ocorrência! Motivo: " + e.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}
}
