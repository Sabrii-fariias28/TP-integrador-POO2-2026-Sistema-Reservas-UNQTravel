package ar.edu.unq.cicloDeLaReserva;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import ar.edu.unq.serviciosypaquetes.ItemCatalogo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CheckInTest {

    private CheckIn checkIn;
    private Reserva reservaMock;

    @BeforeEach
    void setUp() {
        reservaMock = mock(Reserva.class);
        checkIn = new CheckIn();
    }

    @Test
    void testConfirmarViaje_CambiaEstadoAEnCurso() {
        checkIn.confirmarViaje(reservaMock);
        verify(reservaMock).cambiarEstado(any(EnCurso.class));
    }

    @Test
    void testCancelarReserva_CambiaEstadoACancelada() {
        checkIn.cancelarReserva(reservaMock);
        verify(reservaMock).cambiarEstado(any(Cancelada.class));
        verify(reservaMock).incrementarCuposAItems();
        verify(reservaMock).reembolsarTotalCostos();
    }

    @Test
    void testFinalizarViaje_EstandoEnCheckIn_LanzaExcepcion() {
        Exception excepcion = assertThrows(OperacionInvalida.class, () -> checkIn.finalizarViaje(reservaMock));
        assertEquals("No se puede finalizar el viaje", excepcion.getMessage());
    }

    @Test
    void testRealizarCheckIn_EstandoEnCheckIn_LanzaExcepcion() {
        Exception excepcion = assertThrows(OperacionInvalida.class, () -> checkIn.realizarCheckIn(reservaMock));
        assertEquals("No se puede realizar el check-in", excepcion.getMessage());
    }

    @Test
    void testAgregarYQuitarItem_EstandoEnCheckIn_LanzaExcepcionHeredada() {
        ItemCatalogo itemMock = mock(ItemCatalogo.class);

        OperacionInvalida exAgregar = assertThrows(OperacionInvalida.class, () -> checkIn.agregarItem(reservaMock, itemMock));
        assertEquals("No se pueden agregar servicios", exAgregar.getMessage());

        OperacionInvalida exQuitar = assertThrows(OperacionInvalida.class, () -> checkIn.quitarItem(reservaMock, itemMock));
        assertEquals("No se pueden quitar servicios", exQuitar.getMessage());
    }
}
