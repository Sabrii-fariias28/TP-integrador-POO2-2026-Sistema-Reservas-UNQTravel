package ar.edu.unq.notificadores;

public class GeneradorVoucher implements Notificador{

    public void actualizarEstado(Estado estadoAnterior, Estado estadoNuevo){
        if (estadoNuevo.requiereVoucher()){
            this.generarVoucher();
        }
    }

    private String generarVoucher(){ //LO CAMBIE PARA TESTEAR PERO ES UN VOID
        return("Voucher creado");
    }
}
