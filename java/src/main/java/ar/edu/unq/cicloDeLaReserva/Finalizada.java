package ar.edu.unq.cicloDeLaReserva;

public class Finalizada extends EstadoReserva{
    @Override
    public void realizarCheckIn(Reserva reserva) {
        throw new OperacionInvalida("No se puede realizar el check-in: la reserva ya finalizó");
    }

    @Override
    public void confirmarViaje(Reserva reserva) {
        throw new OperacionInvalida("No se puede confirmar el viaje: la reserva ya finalizó");
    }

    @Override
    public void finalizarViaje(Reserva reserva) {
        throw new OperacionInvalida("El viaje ya se encuentra finalizado");
    }
    @Override
    public void cancelarReserva(Reserva reserva) {
        throw new OperacionInvalida("No se puede cancelar el viaje : El viaje ya se encuentra finalizado");
    }
}
