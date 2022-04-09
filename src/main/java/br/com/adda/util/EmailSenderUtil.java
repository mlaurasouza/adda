package br.com.adda.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import br.com.adda.model.Email;

@Component
public class EmailSenderUtil {

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendMail(Email email) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(email.getSender());
			message.setTo(email.getReceiver());
			message.setSubject(email.getSubject());
			message.setText(email.getBody());

			javaMailSender.send(message);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
