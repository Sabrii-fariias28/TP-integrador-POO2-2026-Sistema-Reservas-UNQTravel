package ar.edu.unq.notificadores;

public interface Notificador {
    public void actualizarEstado(Estado estadoAnterior, Estado estadoNuevo);
}
