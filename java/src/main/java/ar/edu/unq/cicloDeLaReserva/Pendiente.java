package ar.edu.unq.cicloDeLaReserva;
import ar.edu.unq.serviciosypaquetes.*;

public class Pendiente extends EstadoReserva{
    @Override
    public void agregarItem(Reserva reserva, ItemCatalogo item) {
        reserva.agregarItem(item);
    }

    @Override
    public void quitarItem(Reserva reserva, ItemCatalogo item) {
        reserva.quitarItem(item);
    }

    @Override
    public void confirmarReserva(Reserva reserva) {
        reserva.realizarPago();
        reserva.cambiarEstado(new Confirmada());
        reserva.decrementarCuposAItems();
    }

    @Override
    public void cancelarReserva(Reserva reserva) {
        reserva.cambiarEstado(new Cancelada());
    }
}
