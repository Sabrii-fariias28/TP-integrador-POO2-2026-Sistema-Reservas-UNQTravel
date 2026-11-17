package ar.edu.unq.cicloDeLaReserva;
import ar.edu.unq.metodoDePago.MetodoDePago;
import ar.edu.unq.notificadores.NotificadorManager;
import ar.edu.unq.serviciosypaquetes.ItemCatalogo;
import ar.edu.unq.trasladosytasas.MetodoTraslado;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestorReservas {
    private List<Reserva> reservas;

    public GestorReservas() {
        this.reservas = new ArrayList<>();
    }

    public Reserva crearReserva(int cantPersonas,
                                LocalDate fechaInicio,
                                LocalDate fechaFin,
                                MetodoDePago metodoDePago,
                                NotificadorManager notificador,
                                MetodoTraslado traslado) {

        Reserva reserva = new Reserva(cantPersonas, fechaInicio, fechaFin, metodoDePago, notificador, traslado );

        agregarReserva(reserva);

        return reserva;
    }

    private void agregarReserva(Reserva r){
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