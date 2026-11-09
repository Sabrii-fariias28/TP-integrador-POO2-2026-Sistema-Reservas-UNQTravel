package ar.edu.unq.cicloDeLaReserva;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ar.edu.unq.ItemCatalogo;

import static org.mockito.Mockito.*;

public class PendienteTest {
    private Pendiente pendiente;
    private Reserva reservaMock;
    private ItemCatalogo itemMock;

    @BeforeEach
    void setUp() {
        pendiente = new Pendiente();
        reservaMock = mock(Reserva.class);
        itemMock = mock(ItemCatalogo.class);
    }

    @Test
    void agregarItemDelegaEnLaReserva() {
        pendiente.agregarItem(reservaMock, itemMock);
        verify(reservaMock).agregarItem(itemMock);
    }

    @Test
    void confirmarReservaEjecutaPagoCambiaEstadoYDecrementaCupos() {
        pendiente.confirmarReserva(reservaMock);

        verify(reservaMock).realizarPago();
        verify(reservaMock).cambiarEstado(any(Confirmada.class));
        verify(reservaMock).decrementarCuposAItems();
    }

    @Test
    void cancelarReservaCambiaElEstadoACancelada() {
        pendiente.cancelarReserva(reservaMock);
        verify(reservaMock).cambiarEstado(any(Cancelada.class));
    }

}
