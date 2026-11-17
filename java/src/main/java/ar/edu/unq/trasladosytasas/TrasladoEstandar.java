package ar.edu.unq.trasladosytasas;

import ar.edu.unq.cicloDeLaReserva.Reserva;

public class TrasladoEstandar implements MetodoTraslado{
    private final PlataformaTransporte plataforma;
    private Direccion direccionDestino;

    public TrasladoEstandar(PlataformaTransporte plataforma, Direccion direccion) {
        this.plataforma = plataforma;
        this.direccionDestino = direccion;
    }

    public boolean tieneDisponibilidad(Reserva reserva) {
        return reserva.getCantPersonas() > 0 && this.direccionDestino != null;
    }

    @Override
    public double calcularCosto(Reserva reserva) {
        return plataforma.estimarTraslado(reserva.getCantPersonas(), this.direccionDestino);
    }
}
