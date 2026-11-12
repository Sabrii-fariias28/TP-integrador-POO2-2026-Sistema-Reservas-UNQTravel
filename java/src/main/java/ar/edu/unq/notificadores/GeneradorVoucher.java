package ar.edu.unq.notificadores;

import ar.edu.unq.cicloDeLaReserva.EstadoReserva;

public class GeneradorVoucher implements Notificador{

    public void actualizarEstado(EstadoReserva estadoAnterior, EstadoReserva estadoNuevo){
        if (estadoNuevo.requiereVoucher()){
            this.generarVoucher();
        }
    }

    private String generarVoucher(){ //LO CAMBIE PARA TESTEAR PERO ES UN VOID
        return("Voucher creado");
    }
}
