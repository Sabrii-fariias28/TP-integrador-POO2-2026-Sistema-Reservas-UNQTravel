package src.main.java.ar.edu.unq;

public class NotificadorEmail {

    public void actualizarEstado(Estado estadoAnterior, Estado estadoNuevo){
        if (esEstadoValido(estadoNuevo)){
            MailSender.enviarMail(
                    "correoCliente@gmail.com",
                    "Actualizacion de reserva",
                    "Su reserva se actualizo al estado" + estadoNuevo,
                    null);
        }
    }

    private boolean esEstadoValido(Estado estado){
        return(estadoNuevo = Confirmada || estadoNuevo = Finalizada || estadoNuevo = EnCurso);
    }
}
