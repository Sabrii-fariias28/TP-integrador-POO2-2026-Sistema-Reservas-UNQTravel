package ar.edu.unq.notificadores;

public class Fidelizacion implements Notificador{

    public void actualizarEstado(Estado estadoAnterior, Estado estadoNuevo){
        if (estadoNuevo.requiereFidelizacion()){
            this.enviarMensajeCupon();
        }
    }

    private String enviarMensajeCupon(){
        return("Cupon enviado");
    }
}
