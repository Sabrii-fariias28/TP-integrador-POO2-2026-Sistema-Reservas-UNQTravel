package ar.edu.unq.notificadores;
import ar.edu.unq.cicloDeLaReserva.EstadoReserva;

public class Fidelizacion implements Notificador{

    public void actualizarEstado(EstadoReserva estadoAnterior, EstadoReserva estadoNuevo){
        if (estadoNuevo.requiereFidelizacion()){
            this.enviarMensajeCupon();
        }
    }

    private void enviarMensajeCupon(){
    }
}
