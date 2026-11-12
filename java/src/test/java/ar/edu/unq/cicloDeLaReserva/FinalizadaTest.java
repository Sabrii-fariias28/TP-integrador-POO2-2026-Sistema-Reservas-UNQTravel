package ar.edu.unq.cicloDeLaReserva;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FinalizadaTest {
    private Finalizada finalizada;
    private Reserva reservaMock;

    @BeforeEach
    void setUp() {
        reservaMock = mock(Reserva.class);
        finalizada = new Finalizada();
    }

    @Test
    void testRealizarCheckIn_CuandoYaFinalizo_LanzaExcepcion() {
        Exception excepcion = assertThrows(OperacionInvalida.class, () -> finalizada.realizarCheckIn(reservaMock));
        assertEquals("No se puede realizar el check-in: la reserva ya finalizó", excepcion.getMessage());
    }

    @Test
    void testConfirmarViaje_CuandoYaFinalizo_LanzaExcepcion() {
        Exception excepcion = assertThrows(OperacionInvalida.class, () -> finalizada.confirmarViaje(reservaMock));
        assertEquals("No se puede confirmar el viaje: la reserva ya finalizó", excepcion.getMessage());
    }

    @Test
    void testFinalizarViaje_CuandoYaFinalizo_LanzaExcepcion() {
        Exception excepcion = assertThrows(OperacionInvalida.class, () -> finalizada.finalizarViaje(reservaMock));
        assertEquals("El viaje ya se encuentra finalizado", excepcion.getMessage());
    }

    @Test
    void testCancelarReserva_CuandoYaFinalizo_LanzaExcepcion() {
        Exception excepcion = assertThrows(OperacionInvalida.class, () -> finalizada.cancelarReserva(reservaMock));
        assertEquals("No se puede cancelar el viaje : El viaje ya se encuentra finalizado", excepcion.getMessage());
    }

    @Test
    void testRequiereNotificacionEmail_DevuelveTrue() {
        assertTrue(finalizada.requiereNotificacionEmail());
    }

    @Test
    void testRequiereVoucher_DevuelveTrue() {
        assertTrue(finalizada.requiereVoucher());
    }
}
