package com.br.attornatus.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.br.attornatus.dto.AddressDTO;
import com.br.attornatus.model.Address;
import com.br.attornatus.model.Person;
import com.br.attornatus.repository.AddressRepository;

class AddressServiceTest {
	
	@Mock
	private AddressRepository repository;
	
	@Mock
	private PersonService personService;

	@InjectMocks
	private AddressService service;

	//Models
	
	private Person person;
	private AddressDTO addressDto;
	private Address address;
	
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		this.person = Mockito.spy(new Person("Cristyan test", LocalDate.of(2001, 03, 28)));
		this.address = Mockito.spy(new Address());
		this.address.setCity("Florianópolis");
		this.address.setNumber("147");
		this.address.setStreet("Servidão Antônio Goulart");
		this.address.setZipCode("88070155");
		this.address.setMainAddress(false);
		this.address.setPerson(person);
		this.addressDto = new AddressDTO(address);
	}
	
	@Test
	void ShouldReturnAnAddressDto() throws Exception {
		when(personService.find(anyInt())).thenReturn(person);
		when(repository.save(any())).thenReturn(address);
		
		
		AddressDTO returnOfMethod = service.create(addressDto, 1);
				
		assertNotNull(returnOfMethod);
		assertEquals(returnOfMethod.getCity(), "Florianópolis");
		assertEquals(returnOfMethod.getNumber(), "147");
		assertEquals(returnOfMethod.getStreet(), "Servidão Antônio Goulart");
		assertEquals(returnOfMethod.getZipCode(), "88070155");
		assertEquals(returnOfMethod.getClass(), AddressDTO.class);
	}
	
	@Test
	void ShouldSwitchTheMainAddressOfPerson() throws Exception {
		this.addressDto.setMainAddress(true);
		when(personService.find(anyInt())).thenReturn(person);
		when(repository.save(any())).thenReturn(address);
		
		
		service.create(addressDto, 1);
		
		
		verify(person, times(1)).switchMainAddress();
				
	}
	
	
	@Test
	void ShouldReturnAnAddressDtoList() throws Exception {
		this.person.setAddress(List.of(address, address, address));
		when(personService.find(anyInt())).thenReturn(person);
		
		
		List<AddressDTO> returnOfMethod = service.listAddressPerson(1);
		
		assertEquals(returnOfMethod.size(), 3);
	}

	
	@Test
	void ShouldReturnMainAddressOfPerson() throws Exception {
		Address main = new Address();
		main.setMainAddress(true);
		main.setCity("main address test");
		this.person.setAddress(List.of(address, address, main));
		when(personService.find(anyInt())).thenReturn(person);
		
		
		AddressDTO returnOfMethod = service.mainAddressPerson(1);
		
		assertNotNull(returnOfMethod);
		assertEquals(returnOfMethod.getCity(), main.getCity());
	}

	
	

}
