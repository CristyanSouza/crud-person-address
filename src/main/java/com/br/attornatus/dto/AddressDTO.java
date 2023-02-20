package com.br.attornatus.dto;

import com.br.attornatus.model.Address;

import jakarta.validation.constraints.NotEmpty;

public class AddressDTO {
	
	private Integer id;
	@NotEmpty(message = "O campo rua é obrigatório")
	private String street;
	@NotEmpty(message = "O campo cep é obrigatório")
	private String zipCode;
	@NotEmpty(message = "O campo número é obrigatório")
	private String number;
	@NotEmpty(message = "O campo cidade é obrigatório")
	private String city;
	private Boolean mainAddress = false;
	
	
	public AddressDTO() {}
	
	public AddressDTO(Address address){
		this.street = address.getStreet();
		this.zipCode = address.getZipCode();
		this.number = address.getNumber();
		this.city = address.getCity();
		this.mainAddress = address.getMainAddress();
		
		this.id = address.getId();

	}

	
	
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}



	public Boolean getMainAddress() {
		return mainAddress;
	}



	public void setMainAddress(Boolean mainAddress) {
		this.mainAddress = mainAddress;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
