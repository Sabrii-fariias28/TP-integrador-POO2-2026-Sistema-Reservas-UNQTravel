package ar.edu.unq.cicloDeLaReserva;

import ar.edu.unq.ItemCatalogo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Reserva extends EstadoReserva{
    private List<ItemCatalogo> servicios;
    private EstadoReserva estado;
    private int cantPersonas; //revisar...
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    //private NotificadorManager notificador; Cuando Dami haga push a su rama lo descomento...
    //private MetodoDePago metodoDePago;

    //Acordarme de poner en el contructor el met de pago y el notificador cuando lo hagamos
    public Reserva(int cantPersonas) {
        this.servicios = new ArrayList<>();
        this.estado = new Pendiente();
        this.cantPersonas = cantPersonas;
    }

    public List<ItemCatalogo> getItemsCatalogo(){
        return this.servicios;
    }

    public void agregarItem(ItemCatalogo item){
        if(item.estaDisponible(fechaInicio,fechaFin,cantPersonas)){
            this.servicios.add(item);
            // item.decrementarCupo(...) Cuando Gonza termine su rama lo descomento
        } else{
            throw new SinDisponibilidad("El servicio no se puede añadir, no tiene disponibilidad.");
        }
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
        //teniendo la lista de los items se tienen que descontar todos los cupos a la vez
    }

    //estos lo tengo que agregar al UML
    public void reembolsarTotalCostos(){};
    public void reembolsarCostos(){};
    public void realizarPago(){
       //metodoDePago.realizarPago(calcularPrecioTotal());
    };

    public double calcularPrecioTotal(){
        return servicios.stream()
                .mapToDouble(ItemCatalogo::precioFinal)
                .sum();
    }
}


//Por aca pensé algo, si una familia quiere reservar, entonces ponele que entonces tiene que resevar para todos los integrandes
//Entonces, no deberia descrementar los cupos segun la cantidad de personas¿, por ejemplo 4, entonces el precio se multiplica
//Por justamente 4, lo mismo para cuando quieren rebolsar, se tienen que liberar 4 cupos de esos servicios que eligieron...