package com.br.attornatus.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Table(name = "person")
@Entity(name = "person")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	

	private String name;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate birthDate;

	@OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
	private List<Address> address = new ArrayList<>();

	public Person() {
	}


	public Person(String name, LocalDate birthDate) {
		this.name = name;
		this.birthDate = birthDate;
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
		return this.address;
	}
	
	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public Address getMainAddress() {
		Address address = this.address.stream().filter(a -> a.getMainAddress() == true).findFirst().get();
		return address;
	}

	public void switchMainAddress() {
		this.address.forEach(a -> a.setMainAddress(false));
	}

}
