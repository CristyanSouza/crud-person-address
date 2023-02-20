package com.br.attornatus.config;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.br.attornatus.model.Address;
import com.br.attornatus.model.Person;
import com.br.attornatus.repository.AddressRepository;
import com.br.attornatus.repository.PersonRepository;

@Configuration
public class DBService {
	
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private AddressRepository addressRepository;
	
	
	@Bean
	public void seedDb() {
	List<Person> listPerson = new ArrayList<>();
	List<Address> listAddress = new ArrayList<>();
	
	
	listPerson.add(new Person("Cristyan de Souza", LocalDate.of(2001, 3, 28)));
	listPerson.add(new Person("Tainna Oliveira", LocalDate.of(2003, 6, 19)));
	listPerson.add(new Person("Tania Regina", LocalDate.of(1963, 7, 14)));
	listPerson.add(new Person("Francisco Carlos", LocalDate.of(1958, 8, 9)));
	listPerson.add(new Person("Priscila de Souza", LocalDate.of(1991, 9, 14)));
	listPerson.add(new Person("Jeferson Torres", LocalDate.of(1989, 10, 9)));
	listPerson.add(new Person("Jose Bezerra", LocalDate.of(2002, 1, 28)));
	
	listPerson.forEach(p -> {
		this.personRepository.save(p);
	});
	
	
	
	listAddress.add(new Address("Rua 1", "88070100", "1", "Florianópolis", personRepository.findById(1).get()));
	listAddress.add(new Address("Rua 2", "88070100", "2", "Florianópolis", personRepository.findById(2).get()));
	listAddress.add(new Address("Rua 3", "88070100", "3", "Florianópolis", personRepository.findById(3).get()));
	listAddress.add(new Address("Rua 4", "88070100", "4", "Florianópolis", personRepository.findById(4).get()));
	listAddress.add(new Address("Rua 5", "88070100", "5", "Florianópolis", personRepository.findById(5).get()));
	listAddress.add(new Address("Rua 6", "88070100", "6", "Florianópolis", personRepository.findById(6).get()));
	listAddress.add(new Address("Rua 7", "88070100", "7", "Florianópolis", personRepository.findById(7).get()));
	
	listAddress.forEach(a -> {
		a.setMainAddress(true);
		this.addressRepository.save(a);
	});

	}

	
}
