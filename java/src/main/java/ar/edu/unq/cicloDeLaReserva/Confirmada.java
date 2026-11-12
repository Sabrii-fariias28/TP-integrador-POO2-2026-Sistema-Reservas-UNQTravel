package ar.edu.unq.cicloDeLaReserva;

import ar.edu.unq.serviciosypaquetes.*;

public class Confirmada extends EstadoReserva{
    @Override
    public void realizarCheckIn(Reserva reserva) {
        reserva.cambiarEstado(new CheckIn());
    }

    @Override
    public void cancelarReserva(Reserva reserva) {
        reserva.incrementarCuposAItems();
        reserva.cambiarEstado(new Cancelada());
    }

    @Override
    public void agregarItem(Reserva reserva, ItemCatalogo item) {
        throw new OperacionInvalida("No se pueden agregar servicios porque la reserva ya está confirmada");
    }

    @Override
    public void quitarItem(Reserva reserva, ItemCatalogo item) {
        throw new OperacionInvalida("No se pueden quitar servicios porque la reserva ya está confirmada");
    }

    @Override
    public boolean requiereNotificacionEmail(){return true;}
}
