package ar.edu.unq.cicloDeLaReserva;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EnCursoTest {

    private EnCurso enCurso;
    private Reserva reservaMock;

    @BeforeEach
    void setUp() {
        reservaMock = mock(Reserva.class);
        enCurso = new EnCurso();
    }

    @Test
    void testFinalizarViaje_CambiaEstadoAFinalizada() {
        enCurso.finalizarViaje(reservaMock);
        verify(reservaMock).cambiarEstado(any(Finalizada.class));
    }

    @Test
    void testCancelarReserva_ReembolsaCostosYCambiaACancelada() {
        enCurso.cancelarReserva(reservaMock);

        verify(reservaMock).reembolsarServicios();
        verify(reservaMock).cambiarEstado(any(Cancelada.class));
    }

    @Test
    void testConfirmarViaje_EstandoEnCurso_LanzaExcepcion() {
        Exception excepcion = assertThrows(OperacionInvalida.class, () -> enCurso.confirmarViaje(reservaMock));
        assertEquals("No se puede confirmar el viaje", excepcion.getMessage());
    }
}
