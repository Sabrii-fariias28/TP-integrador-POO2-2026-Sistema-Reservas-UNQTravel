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

        // Aca pongo el mock para que devuelva true por defecto al consultar disponibilidad
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

        Exception excepcion = assertThrows(
                SinDisponibilidad.class,
                () -> reserva.agregarItem(servicio)
        );

        assertEquals("El servicio no se puede añadir, no tiene disponibilidad.", excepcion.getMessage());
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

        when(servicio.precioFinal()).thenReturn(1000.0);
        when(otroServicio.precioFinal()).thenReturn(2000.0);

        reserva.agregarItem(servicio);
        reserva.agregarItem(otroServicio);

        assertEquals(3000.0, reserva.calcularPrecioTotal(), 0.001);
    }
}