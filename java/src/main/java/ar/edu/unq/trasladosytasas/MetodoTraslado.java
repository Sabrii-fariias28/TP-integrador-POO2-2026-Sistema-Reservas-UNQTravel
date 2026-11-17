package ar.edu.unq.trasladosytasas;

import ar.edu.unq.cicloDeLaReserva.*;

public interface MetodoTraslado {
    double calcularCosto(Reserva reserva);
    boolean tieneDisponibilidad(Reserva reserva);
}

