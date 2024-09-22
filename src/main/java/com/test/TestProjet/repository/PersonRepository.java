package com.test.TestProjet.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.test.TestProjet.entity.Person;

public interface PersonRepository extends CrudRepository<Person, String> {

	public Optional<Person> findByUsername(String username);
	
	
}
