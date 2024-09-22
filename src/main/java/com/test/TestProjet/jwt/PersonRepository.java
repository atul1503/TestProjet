package com.test.TestProjet.jwt;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, String> {

	public Optional<Person> findByUsername(String username);
	
	
}
