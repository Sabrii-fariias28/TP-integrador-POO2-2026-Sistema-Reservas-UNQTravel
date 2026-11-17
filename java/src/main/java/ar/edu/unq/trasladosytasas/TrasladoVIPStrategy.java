package ar.edu.unq.trasladosytasas;

import ar.edu.unq.cicloDeLaReserva.Reserva;

public class TrasladoVIPStrategy implements MetodoTraslado{
    private final TrasladoVIP servicioVip;

    public TrasladoVIPStrategy(TrasladoVIP servicioVip) {
        this.servicioVip = servicioVip;
    }

    @Override
    public double calcularCosto(Reserva reserva) {
        return this.servicioVip.calcularCosto((float) reserva.calcularPrecioServicios());
    }

    @Override
    public boolean tieneDisponibilidad(Reserva reserva) {
        return true;
    }
}

