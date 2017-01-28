package br.com.posweb.serviceagendamento.util;

import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailSender {
	public void senderMail(final Mail mail) throws MessagingException, IOException {

		Properties props = new Properties();

		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.starttls.enable", "true");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.mime.charset", "ISO-8859-1");
		
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mail.getUserMail(), mail.getPassMail());
			}
		};

		Session session = Session.getInstance(props, auth);
		session.setDebug(false); // log

		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(mail.getFromNameMail()));
		
		for (int i = 0; i < mail.getDestinatarios().size(); i++) {
			String dest = mail.getDestinatarios().get(i);
			
			msg.addRecipient(i == 0 ? Message.RecipientType.TO: Message.RecipientType.CC, new InternetAddress(dest, "email"));
		}

		msg.setSubject(mail.getSubjectMail());

		MimeBodyPart textPart = new MimeBodyPart();

		textPart.setContent(mail.getBodyMail(), Mail.TYPE_TEXT_HTML);

		Multipart mps = new MimeMultipart();

		if(mail.getFileMails() != null){
			
			MimeBodyPart attachFilePart;
			FileDataSource fds;
			
			for (int index = 0; index < mail.getFileMails().size(); index++) {
	
				attachFilePart = new MimeBodyPart();
				fds = new FileDataSource(mail.getFileMails().get(index));
	
				attachFilePart.setDataHandler(new DataHandler(fds));
				attachFilePart.setFileName(fds.getName());
	
				mps.addBodyPart(attachFilePart, index);
			}
		}

		mps.addBodyPart(textPart);
		msg.setContent(mps);
		Transport.send(msg);
	}
}