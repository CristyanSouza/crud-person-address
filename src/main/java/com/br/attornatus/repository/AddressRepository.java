package com.br.attornatus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.attornatus.model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>{

}
