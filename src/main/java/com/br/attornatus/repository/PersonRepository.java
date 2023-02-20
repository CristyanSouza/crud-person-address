package com.br.attornatus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.attornatus.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>{

}
