package com.springbootdata.config;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.springbootdata.entity.User;
import com.springbootdata.repository.UserRepository;

//Anotação mãe do Spring, exitem diversas filhas
@Component
public class DataInitilizr implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	UserRepository userRepository; //Spring Injeta para usar spring data

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		
		//Busco no Banco e valido se é vazia ou não a lista, se for cadastro, se não passo reto
		List<User> users = userRepository.findAll();
		if (users.isEmpty()) {
			createUser("Murillo", "murillopezzuol@hotmail.com");
			createUser("João","joao@gmail.com");
			createUser("Gabriella", "gabriella@gmail.com");
			
		}
		
		//Faço find por e-mail
		User user1 = userRepository.findByEmail("murillopezzuol@hotmail.com");
		System.out.println(user1.getNome());//Murillo
		
		//Uso query, utilizando like para melhorar meu find
		User user2 = userRepository.findByNomeQualquerCoisa("jo");
		System.out.println(user2.getNome());//João
		
		User user3 = userRepository.findByEmailAndNome("gabriella@gmail.com", "Gabriella");
		System.out.println(user3.getNome());//Gabriella
		
		
		
	}
	
	public void createUser(String nome, String email) {
		User user = new User(nome, email);
		userRepository.save(user);//Persist com spring data
	}

}
