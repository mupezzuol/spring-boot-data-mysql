package com.springbootdata.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.springbootdata.entity.User;
import com.springbootdata.repository.UserRepository;

//Anotação mãe do Spring, exitem diversas filhas
//ApplicationListener é uma interface onde passamos o 'ContextRefreshedEvent'...
//Na interface 'ApplicationListener' toda vez q subirmos nossa aplicação essa classe no método 'onApplicationEvent' será executado
//Fica escutando nossa aplicação, e usamos o evento para sempre q subir a aplicação ele roda esse método
//Nesse caso estamos usando apenas para realizar alguns testes na aplicação
@Component
public class DataInitilizr implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	UserRepository userRepository; //injeção q o spring faz (data repository)

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		criarRegistros();//Crio registros pela primeira vez
		
		
		//Resgato o User de ID 1 do banco
		System.out.println(userRepository.getOne(5L).getNome());//Raul Pezzuol
		userRepository.deleteById(5L);//Deleto após printar.. (no banco é para ficar sem o ID 5)
		
		
		//Faço find por e-mail
		User user1 = userRepository.findByEmail("murillopezzuol@hotmail.com");
		System.out.println(user1.getNome());//Murillo
		
		
		//Uso query, utilizando like para melhorar meu find
		User user2 = userRepository.findByNomeQualquerCoisa("jo");
		System.out.println(user2.getNome());//João
		
		
		User user3 = userRepository.findByEmailAndNome("gabriella@gmail.com", "Gabriella");
		System.out.println(user3.getNome());//Gabriella
		
		
		//Valido dessa forma pois até o momento eu tenho um User com idade 50
		User user4 = userRepository.findByIdadeGreaterThanAndIdadeLessThan(21,50);
		System.out.println(user4.getNome());//Muriçoca
		
		
		//Alterando registros com método SAVE
		atualizarRegistro();
		System.out.println(userRepository.getOne(7L).getNome());//Nome Alterado
	}
	
	
	//Atualizando o registro. Seto os valores e chamo o método save normalmente, ele faz toda a transação/merge
	private void atualizarRegistro(){
		User userUpdate = userRepository.getOne(7L);
		userUpdate.setNome("Nome Alterado");
		userUpdate.setEmail("email.alterado@hotmail.com");
		userUpdate.setIdade(25);
		userRepository.save(userUpdate);
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
			createUser("NOME", "E-MAIL",50);
		}
	}
	
	private void createUser(String nome, String email, int idade) {
		User user = new User(nome, email, idade);
		userRepository.save(user);//Save do spring data repository
	}

}
