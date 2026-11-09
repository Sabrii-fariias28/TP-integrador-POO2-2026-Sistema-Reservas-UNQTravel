package ar.edu.unq.cicloDeLaReserva;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.ItemCatalogo;

class ConfirmadaTest {

    private Confirmada confirmada;
    private Reserva reservaMock;
    private ItemCatalogo itemMock;

    @BeforeEach
    void setUp() {
        reservaMock = mock(Reserva.class);
        itemMock = mock(ItemCatalogo.class);
        confirmada = new Confirmada();
    }

    @Test
    void testRealizarCheckIn_CambiaEstadoACheckIn() {
        confirmada.realizarCheckIn(reservaMock);
        verify(reservaMock).cambiarEstado(any(CheckIn.class));
    }

    @Test
    void testCancelarReserva_CambiaEstadoEIncrementaCupos() {
        confirmada.cancelarReserva(reservaMock);

        verify(reservaMock).incrementarCuposAItems();
        verify(reservaMock).cambiarEstado(any(Cancelada.class));
    }

    @Test
    void testAgregarItem_CuandoLaReservaEstaConfirmada_LanzaExcepcion() {
        Exception excepcion = assertThrows(OperacionInvalida.class, () ->
                confirmada.agregarItem(reservaMock, itemMock)
        );
        assertEquals("No se pueden agregar servicios porque la reserva ya está confirmada", excepcion.getMessage());
    }

    @Test
    void testQuitarItem_КогдаLaReservaEstaConfirmada_LanzaExcepcion() {
        Exception excepcion = assertThrows(OperacionInvalida.class, () ->
                confirmada.quitarItem(reservaMock, itemMock)
        );
        assertEquals("No se pueden agregar servicios porque la reserva ya está confirmada", excepcion.getMessage());
    }
}