package ar.edu.unq.cicloDeLaReserva;

public class OperacionInvalida extends RuntimeException {

    public OperacionInvalida(String mensaje) {
        super(mensaje);
    }
}

