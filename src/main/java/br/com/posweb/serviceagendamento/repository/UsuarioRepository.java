package br.com.posweb.serviceagendamento.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.posweb.serviceusuario.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

}
