package ar.edu.unq.cicloDeLaReserva;

public class EnCurso extends EstadoReserva{
    @Override
    public void finalizarViaje(Reserva reserva) {
        reserva.cambiarEstado(new Finalizada());
    }

    @Override
    public void cancelarReserva(Reserva reserva) {
        reserva.reembolsarCostosTasas();// solo se reembolsa el costo de los servicios no disfrutados pero no de las tasas administrativas.
        reserva.cambiarEstado(new Cancelada());
    }
}
