package br.unipe.pos.web.activemq.texto;

import javax.jms.Connection;

import javax.jms.Destination;

import javax.jms.Message;

import javax.jms.MessageConsumer;

import javax.jms.Session;

import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.google.gson.Gson;

public class Consumer implements Runnable {

	public void run() {
		TesteProducer testePro = new TesteProducer();
		Email mail = testePro.objTeste();
		Gson gson = new Gson();
		String jsonTemp = "";

		try {

			ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");

			// Cria a conexão com ActiveMQ
			
			factory.setTrustAllPackages(true);

			Connection connection = factory.createConnection();

			// Inicia a conexão

			connection.start();

			// Cria a sessão

			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// Crea a fila e informa qual o destinatário.

			Destination queue = session.createQueue("Thyago");

			MessageConsumer consumer = session.createConsumer(queue);

			Message message = consumer.receive();
			
			do{
				
				if (message instanceof TextMessage) {

					TextMessage textMessage = (TextMessage) message;

					String text = textMessage.getText();

					System.out.println("Mensagem recebida: " + text);
					
					if (text.equals(mail.getDestinatario())) {						
						System.out.println("Existe mensagem para este destinatário...");
						
						mail.setMsg(text);
						jsonTemp = gson.toJson(mail);
						System.out.println("Objeto completo\n" +jsonTemp);
						System.out.println("Mensagem recebida pelo destinatário: \n"+text);
						
					} else {
						System.out.println("Nenhuma mensagem para esse destinatário");
					}

				}
				//Recebe a msg enviada pela class JavaProducer
				message = consumer.receive();
				
			}while(message!=null);
			
			if (message instanceof TextMessage) {

				TextMessage textMessage = (TextMessage) message;

				String text = textMessage.getText();

				System.out.println("Consumer Received: " + text);

			}

			session.close();

			connection.close();

		}

		catch (Exception ex) {

			System.out.println("Exception Occured");

		}

	}

}