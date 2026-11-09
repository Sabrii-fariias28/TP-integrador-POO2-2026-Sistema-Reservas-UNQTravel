package ar.edu.unq.cicloDeLaReserva;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ar.edu.unq.ItemCatalogo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class PendienteTest {
    private Pendiente pendiente;
    private Reserva reservaMock;
    private ItemCatalogo itemMock;

    @BeforeEach
    void setUp() {
        reservaMock = mock(Reserva.class);
        itemMock = mock(ItemCatalogo.class);
        pendiente = new Pendiente();
    }

    @Test
    void testAgregarItem_DelegaEnLaReserva() {
        pendiente.agregarItem(reservaMock, itemMock);
        verify(reservaMock).agregarItem(itemMock);
    }

    @Test
    void testConfirmarReserva_EjecutaPagoCambiaEstadoYDecrementaCupos() {
        pendiente.confirmarReserva(reservaMock);

        verify(reservaMock).realizarPago();
        verify(reservaMock).cambiarEstado(any(Confirmada.class));
        verify(reservaMock).decrementarCuposAItems();
    }

    @Test
    void testCancelarReserva_CambiaEstadoACancelada() {
        pendiente.cancelarReserva(reservaMock);
        verify(reservaMock).cambiarEstado(any(Cancelada.class));
    }

    @Test
    void testRealizarCheckIn_EstandoPendiente_LanzaExcepcion() {
        Exception excepcion = assertThrows(OperacionInvalida.class, () ->
                pendiente.realizarCheckIn(reservaMock)
        );
        assertEquals("No se puede realizar el check-in", excepcion.getMessage());
    }
}
