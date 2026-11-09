package ar.edu.unq.serviciosypaquetes;

import java.time.LocalDate;

public class Disponibilidad {
    private LocalDate fecha;
    private int capacidadMaxima;
    private int reservasConfirmadas;

    public Disponibilidad(LocalDate fecha, int capacidadMaxima){
        this.fecha = fecha;
        this.capacidadMaxima = capacidadMaxima;
        this.reservasConfirmadas = 0;
    }

    public LocalDate getFecha(){
        return this.fecha;
    }

    public boolean hayLugar(int cant){
        return (this.capacidadMaxima - this.reservasConfirmadas) >= cant;
    }

    public void agendarReserva(int cant){
        if (!hayLugar(cant)){
            throw new RuntimeException("No hay cupos disponibles en la fecha solicitada");

        }

        this.reservasConfirmadas += cant;
    }

    public boolean puedeCancelar(int cant) {
        return this.reservasConfirmadas >= cant;
    }

    public void cancelarReservas(int cant){
        if (this.puedeCancelar(cant)){
            throw new RuntimeException("No hay esa cantidad de cupos confirmados para cancelar");
        }

        reservasConfirmadas -= cant;
    }

    public int cuposDisponibles(){
        return this.capacidadMaxima - this.reservasConfirmadas;
    }
}
