package br.com.posweb.serviceagendamento.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.posweb.serviceusuario.entity.Reserva;

public interface ReservaRepository extends CrudRepository<Reserva, Integer> {


}
