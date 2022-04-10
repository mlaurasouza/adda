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

import br.com.adda.model.SosContact;
import br.com.adda.service.SosContactService;

@RestController
public class SosContactController {
	
	@Autowired
	private SosContactService sosContactService;
	
	@GetMapping("/sos_contact")
	public ResponseEntity<?> get() {
		return sosContactService.listSosContact();
	}
	
	@GetMapping("/sos_contact/{userId}")
	public ResponseEntity<?> getById(@PathVariable Long userId) {
		return sosContactService.listSosContactByUserId(userId);
	}
	
	@PostMapping("/sos_contact")
	public ResponseEntity<?> add(@RequestBody SosContact sosContact) {
		return sosContactService.addSosContact(sosContact);
	}
	
	@DeleteMapping("/sos_contact/{id}")
	public ResponseEntity<?> remove(@PathVariable Long id) {
		return sosContactService.removeSosContact(id);
	}
	
	@PutMapping("/sos_contact")
	public ResponseEntity<?> update(@RequestBody SosContact sosContact) {
		return sosContactService.updateSosContact(sosContact);
	}
}
