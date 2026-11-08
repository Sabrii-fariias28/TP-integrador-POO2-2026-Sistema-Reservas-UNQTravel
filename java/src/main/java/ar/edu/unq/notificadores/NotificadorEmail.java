package ar.edu.unq.notificadores;

public class NotificadorEmail implements Notificador {

    public void actualizarEstado(Estado estadoAnterior, Estado estadoNuevo){
        if (estadoNuevo.requiereNotificacionEmail()){
            MailSender.enviarMail(
                    "correoCliente@gmail.com",
                    "Actualizacion de reserva",
                    "Su reserva se actualizo al estado" + estadoNuevo,
                    null);
        }
    }
}
