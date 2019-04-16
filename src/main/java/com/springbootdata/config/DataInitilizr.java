package com.springbootdata.config;

import java.util.List;

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
	UserRepository userRepository; //Spring Data Repository (métodos save, dele, findAll etc...)

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		criarRegistros();//Crio registros pela primeira vez
		
		
		//Faço find por e-mail
		User user1 = userRepository.findByEmail("murillopezzuol@hotmail.com");
		System.out.println(user1.getNome());//Murillo
		
		//Uso query, utilizando like para melhorar meu find
		User user2 = userRepository.findByNomeQualquerCoisa("jo");
		System.out.println(user2.getNome());//João
		
		User user3 = userRepository.findByEmailAndNome("gabriella@gmail.com", "Gabriella");
		System.out.println(user3.getNome());//Gabriella
		
		User user4 = userRepository.findByIdadeGreaterThan(21);
		System.out.println(user4.getNome());//Muriçoca
		
	}
	
	
	
	
	//Crio registros para realizar os testes básicos de Spring Data etc...
	private void criarRegistros(){
		List<User> users = userRepository.findAll();//Retorno do banco a lista de users
		if (users.isEmpty()) {
			createUser("Murillo", "murillopezzuol@hotmail.com",21);
			createUser("João","joao@gmail.com", 15);
			createUser("Gabriella", "gabriella@gmail.com",19);
			createUser("Manuela", "manuela@gmail.com",6);
			createUser("Raul Pezzuol", "raulpezzuol@gmail.com",11);
			createUser("Muriçoca", "muripezzuol@gmail.com",30);
		}
	}
	
	private void createUser(String nome, String email, int idade) {
		User user = new User(nome, email, idade);
		userRepository.save(user);//Save do spring data repository
	}

}
