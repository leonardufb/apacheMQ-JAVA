package br.unipe.pos.web.activemq.texto;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class SendMail {
	
	public boolean enviarGmail(String destinatario, String msg) {
        boolean retorno = false;
        //Get the session object  
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session s = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                    	// Digite o email e senha para autenticar o envio
                        return new PasswordAuthentication("...@gmail.com", "*****");//email e senha usuÃ¡rio 
                    }
                });

        //compose message  
        try {
            MimeMessage message = new MimeMessage(s);
            message.setFrom(new InternetAddress("...@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));

            message.setSubject("Integração de Sistemas, Middleware e Computação na Nuvem");
            message.setContent(msg, "text/html; charset=utf-8");

            //send message  
            Transport.send(message);

            retorno = true;

        } catch (MessagingException e) {
            retorno = false;
            e.printStackTrace();
        }
        return retorno;
    }

	public static void main(String[] args) {
		SendMail sendMail = new SendMail();
		sendMail.enviarGmail("thyagoo18@gmail.com", "TESTE1");

	}

}
