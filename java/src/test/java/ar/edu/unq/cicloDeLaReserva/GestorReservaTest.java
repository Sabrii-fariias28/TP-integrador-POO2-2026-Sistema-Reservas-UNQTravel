package ar.edu.unq.cicloDeLaReserva;

import ar.edu.unq.metodoDePago.MetodoDePago;
import ar.edu.unq.notificadores.NotificadorManager;
import ar.edu.unq.trasladosytasas.MetodoTraslado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class GestorReservaTest {
    private GestorReservas gestor;
    private MetodoDePago metodoDePago;
    private NotificadorManager notificador;
    private MetodoTraslado traslado;

    @BeforeEach
    void setUp() {
        gestor = new GestorReservas();

        metodoDePago = mock(MetodoDePago.class);
        notificador = mock(NotificadorManager.class);
        traslado = mock(MetodoTraslado.class);
    }

    @Test
    void unGestorSeCreaConUnaListaDeReservasVacia() {
        assertTrue(gestor.getReservas().isEmpty());
    }

    @Test
    void crearReservaCreaUnaReservaEnEstadoPendienteYLaAgregaAlGestor() {
        Reserva reserva = gestor.crearReserva(
                2,
                LocalDate.of(2026, 12, 1),
                LocalDate.of(2026, 12, 5),
                metodoDePago,
                notificador,
                traslado
        );

        assertNotNull(reserva);
        assertEquals(1, gestor.getReservas().size());
        assertSame(reserva, gestor.getReservas().get(0));
        assertFalse(reserva.estaFinalizada());
    }

    @Test
    void obtenerReservasDevuelveUnaCopiaDeLaLista() {
        gestor.crearReserva(
                2,
                LocalDate.of(2026, 12, 1),
                LocalDate.of(2026, 12, 5),
                metodoDePago,
                notificador,
                traslado
        );

        List<Reserva> reservasObtenidas = gestor.getReservas();

        reservasObtenidas.clear();

        assertEquals(1, gestor.getReservas().size());
    }

    @Test
    void reservasFinalizadasDevuelveUnaListaVaciaCuandoNoHayReservasFinalizadas() {
        gestor.crearReserva(
                2,
                LocalDate.of(2026, 12, 1),
                LocalDate.of(2026, 12, 5),
                metodoDePago,
                notificador,
                traslado
        );

        assertTrue(gestor.reservasFinalizadas().isEmpty());
    }

    //aca falta el test del filtro funcione bien ... las resrvas finalizadas
}
