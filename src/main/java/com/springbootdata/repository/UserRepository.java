package com.springbootdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springbootdata.entity.User;

//Usando Spring Data criamos uma interface herdando de JpaRepository
//Repository é um Designer Patterns
public interface UserRepository extends JpaRepository<User, Long> {
	
	//Posso especificar uma query, para por exemplo encontrar utilizando LIKE
	//?1% -> é a ordem dos parametros do método
	@Query("select u from User u where u.nome like ?1%")
	User findByNomeQualquerCoisa(String nome);
	
	
	//Será retornado um User, de acordo com o e-mail
	//posso user diversos métodos dessa forma 'findByNome'
	User findByEmail(String email);
	
	
	//Qnd tiver mais parametros user somente '?1' com a ordem dos parametros
	@Query("select u from User u where u.email = ?1 and u.nome = ?2")
	User findByEmailAndNome(String email, String nome);
	
	//Retorna o User quando a idade for maior que a idade que for passada por parametro
	User findByIdadeGreaterThan(int idade);
	
}
