package ar.edu.unq.cicloDeLaReserva;

public class CheckIn extends EstadoReserva{
    @Override
    public void confirmarViaje(Reserva reserva) {
        reserva.cambiarEstado(new EnCurso());
    }

    @Override
    public void cancelarReserva(Reserva reserva) {
        reserva.incrementarCuposAItems();
        reserva.reembolsarTotalCostos();
        reserva.cambiarEstado(new Cancelada());
    }
}
