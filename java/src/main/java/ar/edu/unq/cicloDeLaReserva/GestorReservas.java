package ar.edu.unq.cicloDeLaReserva;
import java.util.ArrayList;
import java.util.List;

public class GestorReservas {
    private List<Reserva> reservas;

    public GestorReservas() {
        this.reservas = new ArrayList<>();
    }

    public void agregarReserva(Reserva r){
        this.reservas.add(r);
    }

    public List<Reserva> getReservas() {
        return new ArrayList<>(this.reservas);
    }

    public List<Reserva> reservasFinalizadas() {
        return reservas.stream()
                .filter(Reserva::estaFinalizada)
                .toList();
    }
}
