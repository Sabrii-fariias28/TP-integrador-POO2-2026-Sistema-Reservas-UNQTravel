package ar.edu.unq.cicloDeLaReserva;
import java.util.ArrayList;
import java.util.List;

public class GestorReservas {
    private List<Reserva> reservas = List.of();

    public void agregarReserva(Reserva r){
        this.reservas.add(r);
    }

    public List<Reserva> getReservas() {
        return new ArrayList<>(this.reservas);
    }
}
