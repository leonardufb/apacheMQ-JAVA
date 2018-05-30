package br.unipe.pos.web.activemq.texto;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.google.gson.Gson;

public class JavaProducer implements Runnable {
	SendMail sendMail = new SendMail();
	TesteProducer testePro = new TesteProducer();


	public void run() {
		Gson gson = new Gson();
		String mailJson = "";
		Email mail = testePro.objTeste();
//		mail.setRemetente("thyagoo18@gmail.com");
//		mail.setDestinatario("simmonevasconcelos@gmail.com");
//		//mail.setDestinatario("thyago_vasconcelos@gmail.com");
//		mail.setMsg("LOVE");


		try {
			// Create a connection factory.

			ActiveMQConnectionFactory factory =

					new ActiveMQConnectionFactory("tcp://localhost:61616");

			// Create connection.

			Connection connection = factory.createConnection();

			// Start the connection

			connection.start();

			// Create a session which is non transactional

			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// Create Destination queue

			Destination queue = session.createQueue("Thyago");

			// Create a producer

			MessageProducer producer = session.createProducer(queue);

			producer.setDeliveryMode(DeliveryMode.PERSISTENT);

			String msg;

			for (int i = 1; i <= 1; i++) {
				msg = mail.getDestinatario();
				
				// insert message
				TextMessage message = session.createTextMessage(msg);
								
				System.out.println("Mensagem de envio e email do destinatário : " + msg);
				//encapsula a mensagem para envio
				producer.send(message);
			}
			session.close();

			connection.close();
			// Envia mensagem de confirmação para o remetente e de pendência de leitura para o destinatário; 
			sendMail.enviarGmail(mail.getRemetente(), " Sua menssagem foi enviada com sucesso!");
			sendMail.enviarGmail(mail.getDestinatario(), " Você tem uma menssagem aguardando leitura!");

		}

		catch (Exception ex) {

			System.out.println("Nenhuma mensagem enfileirada, verifique a conexão...\n Causa: " + ex.getCause());

		}

	}

}