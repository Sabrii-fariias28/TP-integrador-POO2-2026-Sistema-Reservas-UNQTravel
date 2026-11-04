package ar.edu.unq.notificadores;

import ar.edu.unq.Estado;//BORRAR ES PARA TEST AL NO TENER LOS STATES

public class GeneradorVoucher implements Notificador{
    public String generarVoucher(){ //LO CAMBIE PARA TESTEAR PERO ES UN VOID
        return("Voucher creado");
    }

    public void actualizarEstado(Estado estadoAnterior, Estado estadoNuevo){
        Estado Finalizada = null; //BORRAR ES PARA TEST AL NO TENER LOS STATES
        if (estadoNuevo == Finalizada){
            this.generarVoucher();
        }
    }
}
