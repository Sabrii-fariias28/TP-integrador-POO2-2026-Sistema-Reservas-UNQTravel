package ar.edu.unq.notificadores;

import ar.edu.unq.cicloDeLaReserva.EstadoReserva;

public class NotificadorEmail implements Notificador {

    public void actualizarEstado(EstadoReserva estadoAnterior, EstadoReserva estadoNuevo){
        if (estadoNuevo.requiereNotificacionEmail()){
            MailSender.enviarMail(
                    "correoCliente@gmail.com",
                    "Actualizacion de reserva",
                    "Su reserva se actualizo al estado" + estadoNuevo,
                    null);
        }
    }
}
