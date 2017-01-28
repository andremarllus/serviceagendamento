package br.com.posweb.serviceusuario.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Reserva implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_RESERVA")
	private Integer id;
	
	@OneToOne
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;

	public Reserva() {
	}
	
	public Reserva(Integer id, Usuario usuario) {
		super();
		this.id = id;
		this.usuario = usuario;
	}
	
}