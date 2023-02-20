package com.br.attornatus.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.attornatus.dto.AddressDTO;
import com.br.attornatus.service.AddressService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/person/address")
public class AddressController {

	@Autowired
	private AddressService service;
	
	@PostMapping("/{personId}")
	public ResponseEntity<AddressDTO> create(@RequestBody @Valid AddressDTO dto, @PathVariable Integer personId) throws Exception {
		AddressDTO addressDto = service.create(dto, personId);
		
		URI uri = ServletUriComponentsBuilder.fromPath("/person/{id}").buildAndExpand(personId).toUri();
		
		return ResponseEntity.created(uri).body(addressDto);
	}
	
	
	@GetMapping("/{personId}")
	public ResponseEntity<List<AddressDTO>> listAddress(@PathVariable Integer personId) throws Exception{
		List<AddressDTO> list = service.listAddressPerson(personId);
		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/main/{personId}")
	public ResponseEntity<AddressDTO> mainAddress(@PathVariable Integer personId) throws Exception{
		AddressDTO mainAddressDto = service.mainAddressPerson(personId);
		
		return ResponseEntity.ok().body(mainAddressDto);
	}
	
	
}
