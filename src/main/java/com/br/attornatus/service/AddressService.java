package com.br.attornatus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.attornatus.dto.AddressDTO;
import com.br.attornatus.model.Address;
import com.br.attornatus.model.Person;
import com.br.attornatus.repository.AddressRepository;

@Service
public class AddressService {

	@Autowired
	private AddressRepository repository;
	
	@Autowired
	private PersonService personService;
	
	public AddressDTO create(AddressDTO dto, Integer personId) throws Exception {
		
		Address address = new Address();
		Person person = personService.find(personId);

		address.setCity(dto.getCity());
		address.setNumber(dto.getNumber());
		address.setStreet(dto.getStreet());
		address.setZipCode(dto.getZipCode());
		address.setMainAddress(dto.getMainAddress());
		
		if(dto.getMainAddress()) {
			person.switchMainAddress();
		}
		
		if(person.getAddress().size() == 0) {
			address.setMainAddress(true);
		}
		
		address.setPerson(person);
		address = repository.save(address);
		
		
		
		return new AddressDTO(address);
	}

	public List<AddressDTO> listAddressPerson(Integer personId) throws Exception {
		
		Person person = personService.find(personId);
		
		List<AddressDTO> list = person.getAddress().stream().map(a -> new AddressDTO(a)).toList();
		
		return list;
	}

	public AddressDTO mainAddressPerson(Integer personId) throws Exception {
		Person person = personService.find(personId);
		AddressDTO dto = new AddressDTO(person.getMainAddress());
		
		return dto;
	}

}
