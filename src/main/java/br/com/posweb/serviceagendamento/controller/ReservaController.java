package br.com.posweb.serviceagendamento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.posweb.serviceagendamento.service.ReservaService;

@RestController
public class ReservaController {
	
	@Autowired
	private ReservaService reservaService;
	
	@RequestMapping(value="/agenda/confirma", method = RequestMethod.POST)
	public ResponseEntity<String> cadastroUsuario(@RequestParam Integer codigoReserva) {
		try {
			reservaService.confirmarReserva(codigoReserva);
			return new ResponseEntity<String>(HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
