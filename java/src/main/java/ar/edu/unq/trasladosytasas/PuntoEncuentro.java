package ar.edu.unq.trasladosytasas;

import ar.edu.unq.cicloDeLaReserva.Reserva;

public class PuntoEncuentro implements MetodoTraslado{
    private int cupoGuiasDisponibles;

    public PuntoEncuentro(int cupoGuiasDisponibles) {
        this.cupoGuiasDisponibles = cupoGuiasDisponibles;
    }

    @Override
    public boolean tieneDisponibilidad(Reserva reserva) {
        return cupoGuiasDisponibles > 0;
    }

    public void asignarGuia() {
        if (cupoGuiasDisponibles <= 0) {
            throw new IllegalStateException("No hay cupo de guías disponible en el punto de encuentro.");
        }
        this.cupoGuiasDisponibles--;
    }

    public void liberarGuia() {
        this.cupoGuiasDisponibles++;
    }

    @Override
    public double calcularCosto(Reserva reserva) {
        return 0.0;
    }
}
