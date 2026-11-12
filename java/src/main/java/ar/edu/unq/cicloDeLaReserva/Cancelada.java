package ar.edu.unq.cicloDeLaReserva;

import ar.edu.unq.serviciosypaquetes.*;

public class Cancelada extends EstadoReserva{
    @Override
    public void realizarCheckIn(Reserva reserva) {
        throw new OperacionInvalida("No se puede realizar el check-in: la reserva fue cancelada");
    }

    @Override
    public void confirmarViaje(Reserva reserva) {
        throw new OperacionInvalida("No se puede confirmar el viaje: la reserva ya fue cancelada");
    }

    @Override
    public void agregarItem(Reserva reserva, ItemCatalogo item) {
        throw new OperacionInvalida("No se pueden agregar servicios: la reserva fue cancelada");
    }

    @Override
    public void quitarItem(Reserva reserva, ItemCatalogo item) {
        throw new OperacionInvalida("No se pueden quitar servicios la reserva fue cancelada");
    }

    @Override
    public void cancelarReserva(Reserva reserva) {
        throw new OperacionInvalida("No se puede cancelar la reserva porque ya fue cancelada");
    }

    @Override
    public boolean requiereFidelizacion(){return true;}
}
