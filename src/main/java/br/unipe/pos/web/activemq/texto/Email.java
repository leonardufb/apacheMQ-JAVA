package br.unipe.pos.web.activemq.texto;

public class Email {
	private String remetente;
    private String destinatario;
    private String msg;
    
//    public List<String> destinatarios = new ArrayList<String>(){
//        {
//            String[] listDest = destinatario.split(",");
//            for(String dest : listDest){
//                add(dest);
//            }
//            add(destinatario);
//        }
//    };
    
    public String toString(){
        return "Remetente: " + remetente + "\n "
                + "Destinatário(s): " + destinatario +"\n"
                + "Menssagem: " + msg + "\n";
    };

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
