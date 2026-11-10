package ar.edu.unq.cicloDeLaReserva;

public class CheckIn extends EstadoReserva{
    @Override
    public void confirmarViaje(Reserva reserva) {
        reserva.cambiarEstado(new EnCurso());
    }

    @Override
    public void cancelarReserva(Reserva reserva) {
        reserva.incrementarCuposAItems();
        reserva.reembolsarTotalCostos();// Me tengo que fijar como hacer el reintegro de servicios y cargos de reserva, me imagino que por el metodo de pago¿¿¿
        reserva.cambiarEstado(new Cancelada());
    }
}
