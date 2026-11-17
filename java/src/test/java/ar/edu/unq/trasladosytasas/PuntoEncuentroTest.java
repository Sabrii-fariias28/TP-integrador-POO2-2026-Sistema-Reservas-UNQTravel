package ar.edu.unq.trasladosytasas;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ar.edu.unq.cicloDeLaReserva.*;

class PuntoEncuentroTest {

    private Reserva reservaMock;

    @BeforeEach
    void setUp() {
        reservaMock = mock(Reserva.class);
    }

    @Test
    void testTieneDisponibilidadCuandoHayGuias() {
        PuntoEncuentro punto = new PuntoEncuentro(2);
        assertTrue(punto.tieneDisponibilidad(reservaMock));
    }

    @Test
    void testNoTieneDisponibilidadCuandoNoHayGuias() {
        PuntoEncuentro punto = new PuntoEncuentro(0);
        assertFalse(punto.tieneDisponibilidad(reservaMock));
    }

    @Test
    void testAsignarYLiberarGuiaModificaElCupoCorrectamente() {
        PuntoEncuentro punto = new PuntoEncuentro(1);

        punto.asignarGuia();
        assertFalse(punto.tieneDisponibilidad(reservaMock));

        punto.liberarGuia();
        assertTrue(punto.tieneDisponibilidad(reservaMock));
    }

    @Test
    void testAsignarGuiaSinCupoLanzaExcepcion() {
        PuntoEncuentro punto = new PuntoEncuentro(0);

        IllegalStateException ex = assertThrows(IllegalStateException.class, punto::asignarGuia);
        assertEquals("No hay cupo de guías disponible en el punto de encuentro.", ex.getMessage());
    }

    @Test
    void testCalcularCostoSiempreEsCero() {
        PuntoEncuentro punto = new PuntoEncuentro(3);
        assertEquals(0.0, punto.calcularCosto(reservaMock), 0.001);
    }
}