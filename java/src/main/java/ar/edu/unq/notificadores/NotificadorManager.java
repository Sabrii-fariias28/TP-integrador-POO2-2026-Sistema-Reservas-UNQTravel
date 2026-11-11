package ar.edu.unq.notificadores;
import ar.edu.unq.cicloDeLaReserva.EstadoReserva;
import java.util.ArrayList;

public class NotificadorManager {
    private ArrayList<Notificador> notificadores;

    public NotificadorManager(ArrayList<Notificador> n){
        this.notificadores = n;
    }

    public void actualizarEstado(EstadoReserva estadoAnterior, EstadoReserva estadoNuevo){
        for (Notificador notif : notificadores){
            notif.actualizarEstado(estadoAnterior, estadoNuevo);
        }
    }

    public void agregarNotificador(Notificador notificador){
        notificadores.add(notificador);
    }

    public void quitarNotificador(Notificador notificador){
        notificadores.remove(notificador);
    }

    public ArrayList<Notificador> getNotificadores() {
        return notificadores;
    }
}
