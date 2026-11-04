package ar.edu.unq.cicloDeLaReserva;

import ar.edu.unq.ItemCatalogo;

import java.util.ArrayList;
import java.util.List;

public class Reserva extends EstadoReserva{
    private List<ItemCatalogo> servicios;
    private EstadoReserva estado;
    //private NotificadorManager notificador; Cuando Dami haga push a su rama lo descomento...
    //private MetodoDePago metodoDePago;
    private int cantPersonas; //revisar...

    //Acordarme de poner en el contructor el met de pago y el notificador cuando lo hagamos
    public Reserva(int cantPersonas) {
        this.servicios = new ArrayList<>();
        this.estado = new Pendiente();
        this.cantPersonas = cantPersonas;
    }

    public void agregarItem(ItemCatalogo item){
        this.servicios.add(item);
       // item.decrementarCupo(1) Cuando Gonza termine su rama lo descomento
    }

    public void quitarItem(ItemCatalogo item){
        this.servicios.remove(item);
       // item.aumentarCupo(1); Cuando Gonza termine su rama lo descomento
    }

    public void confirmar(){
        estado.confirmarReserva(this);
    }
    public void cancelar(){
        estado.cancelarReserva(this);
    }

    public void cambiarEstado(EstadoReserva nuevoEstado){
        this.estado = nuevoEstado;
    }

    public void incrementarCuposAItems(){
        //teniendo la lista de los items se tienen que liberar todos a la vez
    }

    public void decrementarCuposAItems(){
        //teniendo la lista de los items se tienen que liberar todos a la vez
    }

    //estos lo tengo que agregar al UML
    public void reembolsarTotalCostos(){};
    public void reembolsarCostos(){};
    public void realizarPago(){
        //aca tendria que intervenir el metodo de pago y ver si toene saldo suficiente para pagar
    };
}


