package br.com.posweb.serviceagendamento.service;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.posweb.serviceagendamento.repository.ReservaRepository;
import br.com.posweb.serviceagendamento.util.FuncoesArquivo;
import br.com.posweb.serviceagendamento.util.FuncoesEmail;
import br.com.posweb.serviceusuario.entity.Reserva;

@Service
public class ReservaServiceImpl implements ReservaService{

	@Autowired
	private ReservaRepository reservaRepository;
	
	public void confirmarReserva(Integer codigoReserva) throws Exception {
		
		String IP_MQ=FuncoesArquivo.getInstance().getValorProperties("ip_activemq");
		String PORT_MQ=FuncoesArquivo.getInstance().getValorProperties("port_activemq");
		
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://"+IP_MQ+":"+PORT_MQ);
		
		Connection connection = null;
		Session session = null;
		try {
			
			connection = factory.createConnection();
			connection.start();
			
			session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
			
			Destination queue = session.createQueue("Queue");
			
			MessageConsumer consumer = session.createConsumer(queue, "codigoReserva='"+String.valueOf(codigoReserva)+"'");
			
			Message message = consumer.receive(1000);
			
			if(message instanceof ActiveMQObjectMessage){
					ActiveMQObjectMessage objeto = (ActiveMQObjectMessage) message;
					
					Reserva reserva = (Reserva) objeto.getObject();
					
					System.out.println(reserva);
					
					reservaRepository.save(reserva);
					
					FuncoesEmail.enviaEmail("Agendamento realizado com sucesso para o usuario: " + reserva.getUsuario().getNome());
					
			 }
			
		} catch (JMSException e) {
			e.printStackTrace();
			throw new Exception("Problemas ao iniciar JMS");
		}finally{
			try {
				if(session != null){
					session.close();
				}
				if(connection != null){
					connection.close();
				}
			} catch (JMSException e) {
				e.printStackTrace();
				throw new Exception("Problemas ao fechar as conexoes com o JMS");
			}
		}
	}

}
