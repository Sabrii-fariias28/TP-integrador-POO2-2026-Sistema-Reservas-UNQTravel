package ar.edu.unq.cicloDeLaReserva;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

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
    }

    @Test
    void testFinalizarViaje_EstandoEnCheckIn_LanzaExcepcion() {
        Exception excepcion = assertThrows(OperacionInvalida.class, () -> checkIn.finalizarViaje(reservaMock));
        assertEquals("No se puede finalizar el viaje", excepcion.getMessage());
    }
}
