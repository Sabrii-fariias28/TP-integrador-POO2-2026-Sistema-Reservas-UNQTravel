package ar.edu.unq.cicloDeLaReserva;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ar.edu.unq.serviciosypaquetes.*;

public class CanceladaTest {

    private Cancelada cancelada;
    private Reserva reservaMock;
    private ItemCatalogo itemMock;

    @BeforeEach
    void setUp() {
        reservaMock = mock(Reserva.class);
        itemMock = mock(ItemCatalogo.class);
        cancelada = new Cancelada();
    }

    @Test
    void testRealizarCheckIn_CuandoEstaCancelada_LanzaExcepcion() {
        Exception excepcion = assertThrows(OperacionInvalida.class, () -> cancelada.realizarCheckIn(reservaMock));
        assertEquals("No se puede realizar el check-in: la reserva fue cancelada", excepcion.getMessage());
    }

    @Test
    void testConfirmarReserva_CuandoEstaCancelada_LanzaExcepcion() {
        Exception excepcion = assertThrows(OperacionInvalida.class, () -> cancelada.confirmarViaje(reservaMock));
        assertEquals("No se puede confirmar el viaje: la reserva ya fue cancelada", excepcion.getMessage());
    }

    @Test
    void testAgregarItem_CuandoEstaCancelada_LanzaExcepcion() {
        Exception excepcion = assertThrows(OperacionInvalida.class, () -> cancelada.agregarItem(reservaMock, itemMock));
        assertEquals("No se pueden agregar servicios: la reserva fue cancelada", excepcion.getMessage());
    }

    @Test
    void testCancelarReserva_CuandoEstaCancelada_LanzaExcepcion() {
        Exception excepcion = assertThrows(OperacionInvalida.class, () -> cancelada.cancelarReserva(reservaMock));
        assertEquals("No se puede cancelar la reserva porque ya fue cancelada", excepcion.getMessage());
    }

    @Test
    void testQuitarItem_CuandoEstaCancelada_LanzaExcepcion() {
        Exception excepcion = assertThrows(OperacionInvalida.class, () -> cancelada.quitarItem(reservaMock, itemMock));
        assertEquals("No se pueden quitar servicios la reserva fue cancelada", excepcion.getMessage());
    }

    @Test
    void testRequiereFidelizacion_DevuelveTrue() {
        assertTrue(cancelada.requiereFidelizacion());
    }
}
