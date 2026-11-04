package ar.edu.unq.notificadores;

import ar.edu.unq.Estado;

public class NotificadorEmail implements Notificador {

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
        boolean Confirmada = false;//BORRAR ES PARA TEST AL NO TENER LOS STATES
        boolean Finalizada = false;//BORRAR ES PARA TEST AL NO TENER LOS STATES
        boolean EnCurso = true;//BORRAR ES PARA TEST AL NO TENER LOS STATES
        //return(estado = Confirmada || estado = Finalizada || estado = EnCurso);
        return(true);
    }
}
