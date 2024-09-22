package com.test.TestProjet.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.TestProjet.dtos.LoginUserDto;
import com.test.TestProjet.entity.Person;

@RequestMapping("/random")
@RestController()
public class RandomRepostiroy {

	@Autowired
	private PersonRepository pr;
	
	@PostMapping("/")
	public ResponseEntity<Person> getIt(@RequestBody LoginUserDto dto){
		
	Optional<Person> p=pr.findByUsername(dto.getUsername());
	if (p.isPresent()) {
		
        return ResponseEntity.ok(p.get());
    } else {
        return ResponseEntity.notFound().build();
    }	
	
	
	}
}
