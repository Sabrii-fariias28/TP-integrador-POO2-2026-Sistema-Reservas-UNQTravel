package ar.edu.unq.serviciosypaquetes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class DisponibilidadTest {
    private Disponibilidad disp;

    @BeforeEach
    void setUp() {
        disp = new Disponibilidad(LocalDate.of(2026, 12, 1), 10);
    }

    @Test
    void testAgendarReservaExitosamente() {
        assertTrue(disp.hayLugar(5));
        disp.agendarReserva(5);
        assertEquals(5, disp.cuposDisponibles());
    }

    @Test
    void testAgendarReservaFallaPorFaltaDeCupo() {
        Exception exception = assertThrows(RuntimeException.class, () -> disp.agendarReserva(11));
        assertEquals("No hay cupos disponibles en la fecha solicitada", exception.getMessage());
    }

    @Test
    void testCancelarReservasExitosamente() {
        disp.agendarReserva(5); // Hay 5 confirmadas
        assertTrue(disp.puedeCancelar(3));
        disp.cancelarReservas(3);
        assertEquals(8, disp.cuposDisponibles()); // 10 - 2 restantes
    }

    @Test
    void testCancelarReservasFallaPorFaltaDeReservasPrevias() {
        disp.agendarReserva(2);
        Exception exception = assertThrows(RuntimeException.class, () -> disp.cancelarReservas(5));
        assertEquals("No hay esa cantidad de cupos confirmados para cancelar", exception.getMessage());
    }
}