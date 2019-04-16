package com.springbootdata.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data //Lombok (get,get,toString, hashcode, equals)
@NoArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private String email;
	
	@ManyToMany
	private Set<Role> roles;
	
	
	//Constructor's customizados
	public User(String nome, String email) {
		super();
		this.nome = nome;
		this.email = email;
	}

	
}
