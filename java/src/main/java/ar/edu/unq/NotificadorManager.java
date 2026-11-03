package src.main.java.ar.edu.unq;

import java.util.ArrayList;

public class NotificadorManager {
    private ArrayList<Notificador> notificadores;

    public NotificadorManager(ArrayList<Notificador> n){
        this.notificadores = n;
    }

    public void actualizarEstado(Estado estadoAnterior, Estado estadoNuevo){
        for (Notificador notif : notificadores){
            notif.actualizarEstado(estadoAnterior, estadoNuevo);
        }
    }

    public void agregarNotificador(Notificador notificador){
        notificadores.add(notificador);
    }

    private void quitarNotificador(Notificador notificador){
        notificadores.remove(notificador);
    }
}
