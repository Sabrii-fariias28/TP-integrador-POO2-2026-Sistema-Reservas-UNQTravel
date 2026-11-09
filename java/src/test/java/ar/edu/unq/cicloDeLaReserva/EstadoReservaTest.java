package ar.edu.unq.cicloDeLaReserva;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ar.edu.unq.ItemCatalogo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EstadoReservaTest {

    private EstadoReserva estadoBase;
    private Reserva reservaMock;
    private ItemCatalogo itemMock;

    @BeforeEach
    void setUp() {
        reservaMock = mock(Reserva.class);
        itemMock = mock(ItemCatalogo.class);
        estadoBase = new EstadoReserva();
    }

    @Test
    void testAgregarItem_EnEstadoBase_LanzaExcepcion() {
        Exception excepcion = assertThrows(OperacionInvalida.class, () -> estadoBase.agregarItem(reservaMock, itemMock));
        assertEquals("No se pueden agregar servicios", excepcion.getMessage());
    }

    @Test
    void testConfirmarReserva_EnEstadoBase_LanzaExcepcion() {
        Exception excepcion = assertThrows(OperacionInvalida.class, () -> estadoBase.confirmarReserva(reservaMock));
        assertEquals("No se puede confirmar la reserva", excepcion.getMessage());
    }

    @Test
    void testRealizarCheckIn_EnEstadoBase_LanzaExcepcion() {
        Exception excepcion = assertThrows(OperacionInvalida.class, () -> estadoBase.realizarCheckIn(reservaMock));
        assertEquals("No se puede realizar el check-in", excepcion.getMessage());
    }
}