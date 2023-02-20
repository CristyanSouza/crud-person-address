package com.br.attornatus.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.attornatus.dto.PersonDTO;
import com.br.attornatus.dto.UpdatePersonDTO;
import com.br.attornatus.exception.PersonNotFoundException;
import com.br.attornatus.model.Person;
import com.br.attornatus.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository repository;
	
	

	public List<PersonDTO> findAll() {
		List<PersonDTO> returnList = new ArrayList<>();
		
		repository.findAll().forEach(p -> {
			returnList.add(new PersonDTO(p));
		});
		
		return returnList;
	}
	
	
	public Person find (Integer id) throws Exception {
		
		Optional<Person> person = repository.findById(id);
		
		return person.orElseThrow(() -> new PersonNotFoundException("Pessoa não encontrada"));
	}
	
	
	
	public Person update(Integer id, UpdatePersonDTO dto) throws Exception {
		Person person = this.find(Integer.valueOf(id));
		
		if(dto.getBirthDate() != null) {
			person.setBirthDate(dto.getBirthDate());
		}
		
		if(dto.getName() != null) {
			person.setName(dto.getName());
		}
		
		return repository.save(person);
	}
	
	
	public Person create(PersonDTO dto) {
		Person person = new Person();
		person.setBirthDate(dto.getBirthDate());
		person.setName(dto.getName());
	
		return repository.save(person);
	}



	
	 

	
	
}
