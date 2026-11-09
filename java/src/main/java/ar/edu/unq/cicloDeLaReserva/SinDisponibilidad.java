package ar.edu.unq.cicloDeLaReserva;

public class SinDisponibilidad extends RuntimeException {
    public SinDisponibilidad(String message) {
        super(message);
    }
}
