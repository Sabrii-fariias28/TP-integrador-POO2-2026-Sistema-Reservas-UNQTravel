package ar.edu.unq.cicloDeLaReserva;

import ar.edu.unq.serviciosypaquetes.*;

import ar.edu.unq.metodoDePago.MetodoDePago;
import ar.edu.unq.notificadores.NotificadorManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservaTest {

    private Reserva reserva;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private MetodoDePago metodoPagoMock;
    private NotificadorManager notificadorManager;
    private ItemCatalogo item1;
    private ItemCatalogo item2;
    private ItemCatalogo item3;

    @BeforeEach
    void setUp() {
        fechaInicio = LocalDate.of(2026, 12, 10);
        fechaFin = LocalDate.of(2026, 12, 15);
        notificadorManager = mock(NotificadorManager.class);
        metodoPagoMock = mock(MetodoDePago.class);
        reserva = new Reserva(2, fechaInicio, fechaFin, metodoPagoMock, notificadorManager);
        item1 = mock(ItemCatalogo.class);
        item2 = mock(ItemCatalogo.class);
        item3 = mock(ItemCatalogo.class);

        // Aca pongo el mock para que devuelva true por defecto al consultar disponibilidad
        when(item1.estaDisponibleEnRango(any(), any(), anyInt())).thenReturn(true);
        when(item2.estaDisponibleEnRango(any(), any(), anyInt())).thenReturn(true);
        when(item3.estaDisponibleEnRango(any(), any(), anyInt())).thenReturn(true);
    }

    @Test
    void unaReservaSeCreaConLaListaDeServiciosVacia() {
        assertTrue(reserva.getServicios().isEmpty());
    }

    @Test
    void unaReservaPermiteAgregarUnServicioDisponible() {
        reserva.agregarItem(item1);

        assertTrue(reserva.getServicios().contains(item1));
    }

    @Test
    void unaReservaPermiteQuitarUnServicioAgregado() {
        reserva.agregarItem(item1);
        reserva.quitarItem(item1);

        assertFalse(reserva.getServicios().contains(item1));
    }

    @Test
    void unaReservaNoPermiteAgregarUnServicioSinDisponibilidad() {
        when(item1.estaDisponibleEnRango(any(), any(), anyInt())).thenReturn(false);
        Exception excepcion = assertThrows(SinDisponibilidad.class, () -> reserva.agregarItem(item1));

        assertEquals("El servicio no se puede añadir, no tiene disponibilidad.", excepcion.getMessage());
        assertTrue(reserva.getServicios().isEmpty());
    }

    @Test
    void unaReservaCalculaElPrecioTotalDeSusServicios() {
        when(item1.precioFinal()).thenReturn(1000.0);
        when(item2.precioFinal()).thenReturn(2000.0);

        reserva.agregarItem(item1);
        reserva.agregarItem(item2);

        assertEquals(3000.0, reserva.calcularPrecioTotal(), 0.001);
    }

    @Test
    void unaReservaPendientePuedeConfirmarse() {
        reserva.confirmar();

        assertInstanceOf(Confirmada.class, reserva.getEstado());
    }

    @Test
    void unaReservaPendientePuedeCancelarse() {
        reserva.cancelar();

        assertInstanceOf(Cancelada.class, reserva.getEstado());
    }

    @Test
    void unaReservaGuardaLaCantidadDePersonas() {
        assertEquals(2, reserva.getCantPersonas());
    }

    @Test
    void unaReservaGuardaLaFechaDeInicio() {
        assertEquals(LocalDate.of(2026, 12, 10), reserva.getFechaInicio());
    }

    @Test
    void unaReservaGuardaLaFechaDeFin() {
        assertEquals(LocalDate.of(2026, 12, 15), reserva.getFechaFin());
    }

    @Test
    void testAlConfirmarReserva_ReservaElRangoEnSusServicios() {
        reserva.agregarItem(item1);
        reserva.confirmar();

        verify(item1).reservarRango(eq(fechaInicio), eq(fechaFin), eq(2));
    }

    @Test
    void testAlConfirmarReserva_ReservaElRangoEnTodosSusServicios() {
        reserva.agregarItem(item1);
        reserva.agregarItem(item2);
        reserva.agregarItem(item3);

        reserva.confirmar();

        verify(item1).reservarRango(eq(fechaInicio), eq(fechaFin), eq(2));
        verify(item2).reservarRango(eq(fechaInicio), eq(fechaFin), eq(2));
        verify(item3).reservarRango(eq(fechaInicio), eq(fechaFin), eq(2));
    }

    @Test
    void testNoPermiteAgregarItemSiNoTieneDisponibilidadYFalla() {
        when(item2.estaDisponibleEnRango(any(), any(), anyInt())).thenReturn(false);
        Exception excepcion = assertThrows(SinDisponibilidad.class, () -> reserva.agregarItem(item2));

        reserva.agregarItem(item1);


        assertEquals("El servicio no se puede añadir, no tiene disponibilidad.", excepcion.getMessage());
        assertEquals(1, reserva.getServicios().size());
    }

}