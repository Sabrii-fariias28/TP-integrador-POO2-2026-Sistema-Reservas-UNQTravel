package ar.edu.unq.trasladosytasas;

import ar.edu.unq.cicloDeLaReserva.*;

public interface MetodoTraslado {
    double calcularCosto(Reserva reserva);
    /**
     * Indica si existen condiciones operativas (ej. cupo de guías) para realizar el traslado.
     */
    boolean tieneDisponibilidad(Reserva reserva);
}

