package ar.edu.unq.notificadores;

import ar.edu.unq.Estado;//BORRAR ES PARA TEST AL NO TENER LOS STATES

public interface Notificador {
    public void actualizarEstado(Estado estadoAnterior, Estado estadoNuevo);
}
