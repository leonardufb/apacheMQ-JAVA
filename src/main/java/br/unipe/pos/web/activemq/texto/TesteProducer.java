package br.unipe.pos.web.activemq.texto;


public class TesteProducer {
	
	public Email objTeste() {
		Email mail = new Email();
		mail.setRemetente("thyagoo18@gmail.com");
		mail.setDestinatario("simmonevasconcelos@gmail.com");
		mail.setMsg("LOVE");
		return mail;
	}
	
	public static void main(String[] args) {
		
		
		System.out.println("-->>Criando Mensagens\n");
		JavaProducer javaProducer = new JavaProducer();
		javaProducer.run();
		
		System.out.println("-->>Recebendo Mensagens");
		while (true) {
			Consumer consumer = new Consumer();
			consumer.run();
			try {
				Thread.sleep(30000);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
	}
}