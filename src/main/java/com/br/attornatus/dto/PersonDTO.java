package com.br.attornatus.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.br.attornatus.model.Address;
import com.br.attornatus.model.Person;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class PersonDTO {
	
	private Integer id;
	@NotEmpty(message = "Nome é obrigatório")
	private String name;
	@JsonFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "Data de nascimento é obrigatória")
	private LocalDate birthDate;
	private List<Address> address = new ArrayList<>();
	
	public PersonDTO() {
		
	}
	
	public PersonDTO(Person person) {
		this.id = person.getId();
		this.name = person.getName();
		this.birthDate = person.getBirthDate();
		this.setAddress(person.getAddress());
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}
}
