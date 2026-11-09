package ar.edu.unq.cicloDeLaReserva;

import ar.edu.unq.ItemCatalogo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservaTest {

    private Reserva reserva;
    private ItemCatalogo servicio;

    @BeforeEach
    void setUp() {
        LocalDate fechaInicio = LocalDate.of(2026, 12, 10);
        LocalDate fechaFin = LocalDate.of(2026, 12, 15);

        reserva = new Reserva(2, fechaInicio, fechaFin);

        servicio = mock(ItemCatalogo.class);

        when(servicio.estaDisponible(
                any(LocalDate.class),
                any(LocalDate.class),
                anyInt()
        )).thenReturn(true);
    }

    @Test
    void unaReservaSeCreaConLaListaDeServiciosVacia() {
        assertTrue(reserva.getServicios().isEmpty());
    }

    @Test
    void unaReservaPermiteAgregarUnServicioDisponible() {
        reserva.agregarItem(servicio);
        assertTrue(reserva.getServicios().contains(servicio));
    }

    @Test
    void unaReservaPermiteQuitarUnServicioAgregado() {
        reserva.agregarItem(servicio);
        reserva.quitarItem(servicio);

        assertFalse(reserva.getServicios().contains(servicio));
    }

    @Test
    void unaReservaNoPermiteAgregarUnServicioSinDisponibilidad() {

        when(servicio.estaDisponible(
                eq(LocalDate.of(2026, 12, 10)),
                eq(LocalDate.of(2026, 12, 15)),
                eq(2)
        )).thenReturn(false);

        assertThrows(
                SinDisponibilidad.class,
                () -> reserva.agregarItem(servicio)
        );

        assertTrue(reserva.getServicios().isEmpty());
    }

    @Test
    void unaReservaCalculaElPrecioTotalDeSusServicios() {
        ItemCatalogo otroServicio = mock(ItemCatalogo.class);

        when(otroServicio.estaDisponible(
                any(LocalDate.class),
                any(LocalDate.class),
                anyInt()
        )).thenReturn(true);

        when(servicio.precioFinal()).thenReturn(100.0);
        when(otroServicio.precioFinal()).thenReturn(200.0);

        reserva.agregarItem(servicio);
        reserva.agregarItem(otroServicio);

        assertEquals(300.0, reserva.calcularPrecioTotal(), 0.001);
    }
}
