package com.br.attornatus.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.attornatus.dto.PersonDTO;
import com.br.attornatus.dto.UpdatePersonDTO;
import com.br.attornatus.model.Person;
import com.br.attornatus.service.PersonService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonService service;
	
	
	
	@GetMapping
	public ResponseEntity <List<PersonDTO>> findAll() throws Exception{
		List<PersonDTO> list = service.findAll();
		
		return ResponseEntity.ok().body(list);	
}
	
	
	
	@GetMapping("/{id}")
	public ResponseEntity <PersonDTO> find(@PathVariable Integer id) throws Exception{
		Person person = service.find(id);
		PersonDTO personReturn = new PersonDTO(person);
		
		return ResponseEntity.ok().body(personReturn);
	}
	

	@PostMapping
	public ResponseEntity<PersonDTO> create(@RequestBody @Valid PersonDTO dto) {

		Person person = service.create(dto);
		
		PersonDTO personReturn = new PersonDTO(person);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(person.getId()).toUri();
		
		return ResponseEntity.created(uri).body(personReturn);
	}
	
	
	
	@PutMapping("/{id}")
	public ResponseEntity<PersonDTO> update(@RequestBody UpdatePersonDTO dto, @PathVariable Integer id) throws Exception {

		Person person = service.update(id, dto);
		
		PersonDTO personReturn = new PersonDTO(person);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(person.getId()).toUri();
		
		return ResponseEntity.created(uri).body(personReturn);
	}
	
}
