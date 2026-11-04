package ar.edu.unq.notificadores;

import ar.edu.unq.Estado;//BORRAR ES PARA TEST AL NO TENER LOS STATES

public class Fidelizacion implements Notificador{

    public void actualizarEstado(Estado estadoAnterior, Estado estadoNuevo){
        Estado Cancelada = null;//BORRAR ES PARA TEST AL NO TENER LOS STATES
        if (estadoNuevo == Cancelada){
            this.enviarMensajeCupon();
        }
    }

    public String enviarMensajeCupon(){
        return("Cupon enviado");
    }
}
