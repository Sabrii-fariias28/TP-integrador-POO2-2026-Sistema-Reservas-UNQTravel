package ar.edu.unq.notificadores;

import ar.edu.unq.cicloDeLaReserva.EstadoReserva;

public interface Notificador {
    public void actualizarEstado(EstadoReserva estadoAnterior, EstadoReserva estadoNuevo);
}
