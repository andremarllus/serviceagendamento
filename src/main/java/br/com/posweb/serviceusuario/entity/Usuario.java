package br.com.posweb.serviceusuario.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;


	@Id
	@Column(name = "ID_USUARIO")
	private Integer id;

	@Column(name = "NOME")
	private String nome;

	@Column(name = "CPF")
	private String cpf;

}