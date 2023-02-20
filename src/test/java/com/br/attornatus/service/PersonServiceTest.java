package com.br.attornatus.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.br.attornatus.dto.PersonDTO;
import com.br.attornatus.exception.PersonNotFoundException;
import com.br.attornatus.model.Person;
import com.br.attornatus.repository.PersonRepository;


class PersonServiceTest {

	@Mock
	private PersonRepository repository;
	
	@InjectMocks
	private PersonService service;
	
	//Models
	private Person person;
	private PersonDTO personDTO;
	private Optional<Person> optionalPerson;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		this.person = Mockito.spy(new Person("Cristyan de Souza", LocalDate.of(2001, 03, 28)));
		this.optionalPerson = Optional.of(this.person);
		this.personDTO = new PersonDTO(person);
	}
	
	@Test
	void ShouldReturnAPersonDtoList(){
		when(repository.findAll()).thenReturn(List.of(person, person, person));
		
		List<PersonDTO> returnOfMethod = service.findAll();
		
		assertTrue(!returnOfMethod.isEmpty());
		assertEquals(returnOfMethod.size(), 3);
	}
	
	@Test
	void ShouldReturnAPerson() throws Exception {
		when(repository.findById(any())).thenReturn(optionalPerson);
		
		Person returnOfMethod = service.find(1);
		
		assertNotNull(returnOfMethod);
		assertEquals(returnOfMethod.getName(), person.getName());
		assertEquals(returnOfMethod.getBirthDate(), person.getBirthDate());
	}
	
	
	@Test
	void ShouldThrowExceptionWhenPersonNotExists() throws Exception {
		when(repository.findById(any())).thenReturn(Optional.empty());
		
		assertThrows(PersonNotFoundException.class, () -> service.find(1));
	}
	
	@Test
	void ShouldCreateAPerson() {
		when(repository.save(any())).thenReturn(this.person);

		Person returnOfMethod = service.create(this.personDTO);
		
		assertNotNull(returnOfMethod);
		assertEquals(returnOfMethod.getName(), this.personDTO.getName());
		assertEquals(returnOfMethod.getBirthDate(), this.personDTO.getBirthDate());
		verify(repository, times(1)).save(any());
	}
	
}
