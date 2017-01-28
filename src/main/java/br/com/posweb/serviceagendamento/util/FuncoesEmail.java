package br.com.posweb.serviceagendamento.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

public class FuncoesEmail {
	
	public static void enviaEmail(String texto) throws IOException{
		Mail mj = new Mail();

		mj.setUserMail(FuncoesArquivo.getInstance().getValorProperties("mail.user"));
		mj.setPassMail(FuncoesArquivo.getInstance().getValorProperties("mail.password"));		         
        mj.setBodyMail(texto);
        mj.setSubjectMail("Mail Agendamento");
        mj.setFromNameMail(FuncoesArquivo.getInstance().getValorProperties("mail.from"));
        
        List<String> destinatario = new ArrayList<String>();
        destinatario.add(FuncoesArquivo.getInstance().getValorProperties("mail.to"));
        
        mj.setDestinatarios(destinatario);
        
        try {
            new MailSender().senderMail(mj);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace(); 
        }
    }
	
}