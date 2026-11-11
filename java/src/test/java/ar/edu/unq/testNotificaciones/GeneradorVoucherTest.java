package ar.edu.unq.testNotificaciones;
import ar.edu.unq.cicloDeLaReserva.*;
import ar.edu.unq.notificadores.GeneradorVoucher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GeneradorVoucherTest {

    private GeneradorVoucher generadorVoucher;
    private EstadoReserva estadoFinalizada;
    private EstadoReserva estadoConfirmada;

    @BeforeEach
    public void setUp() {
        generadorVoucher = new GeneradorVoucher();
        estadoFinalizada = new Finalizada();
        estadoConfirmada = new Confirmada();
    }

    @Test
    public void testActualizarEstadoCuandoRequiereVoucherGenera() {
        generadorVoucher.actualizarEstado(estadoConfirmada, estadoFinalizada);
        assertTrue(estadoFinalizada.requiereVoucher());
    }

    @Test
    public void testActualizarEstadoCuandoNoRequiereVoucherNoGenera() {
        generadorVoucher.actualizarEstado(estadoFinalizada, estadoConfirmada);
        assertFalse(estadoConfirmada.requiereVoucher());
    }
}