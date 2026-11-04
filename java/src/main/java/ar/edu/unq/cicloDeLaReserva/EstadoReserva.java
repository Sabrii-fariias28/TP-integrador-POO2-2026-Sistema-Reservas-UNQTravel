package ar.edu.unq.cicloDeLaReserva;

import ar.edu.unq.ItemCatalogo;

public class EstadoReserva {

    public void agregarItem(Reserva reserva, ItemCatalogo item) {
        throw new OperacionInvalida("No se pueden agregar servicios");
    }

    public void quitarItem(Reserva reserva, ItemCatalogo item) {
        throw new OperacionInvalida("No se pueden quitar servicios");
    }

    public void confirmarReserva(Reserva reserva) {
        throw new OperacionInvalida("No se puede confirmar la reserva");
    }

    public void cancelarReserva(Reserva reserva) {
        throw new OperacionInvalida("No se puede cancelar la reserva");
    }

    public void realizarCheckIn(Reserva reserva) {
        throw new OperacionInvalida("No se puede realizar el check-in");
    }

    public void confirmarViaje(Reserva reserva) {
        throw new OperacionInvalida("No se puede confirmar el viaje");
    }

    public void finalizarViaje(Reserva reserva) {
        throw new OperacionInvalida("No se puede finalizar el viaje");
    }
}