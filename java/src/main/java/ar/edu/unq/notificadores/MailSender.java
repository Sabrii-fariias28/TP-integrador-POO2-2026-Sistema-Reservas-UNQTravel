package ar.edu.unq.notificadores;

public interface MailSender {
    public static void enviarMail(String destino, String titulo, String mensaje, Object adjunto) {}
}
