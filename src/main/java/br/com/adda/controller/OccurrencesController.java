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

import br.com.adda.model.Occurrences;
import br.com.adda.service.OccurrencesService;

@RestController
public class OccurrencesController {

	@Autowired
	private OccurrencesService occurrencesService;

	@GetMapping("/occurrences")
	public ResponseEntity<?> get(@RequestParam Long userId) {

		if (userId.equals(0L)) {
			return occurrencesService.listOccurrences();
		} else {
			return occurrencesService.listOccurrencesByUserId(userId);
		}
	}

	@PostMapping("/occurrences")
	public ResponseEntity<?> add(@RequestBody Occurrences occurrences) {
		return occurrencesService.addOccurrences(occurrences);
	}

	@DeleteMapping("/occurrences/{id}")
	public ResponseEntity<?> remove(@PathVariable Long id) {
		return occurrencesService.removeOccurrences(id);
	}

	@PutMapping("/occurrences")
	public ResponseEntity<?> update(@RequestBody Occurrences occurrences) {
		return occurrencesService.updateOccurrences(occurrences);
	}
}