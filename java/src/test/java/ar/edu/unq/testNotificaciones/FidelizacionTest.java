package ar.edu.unq.testNotificaciones;

import ar.edu.unq.EstadoReserva;
import ar.edu.unq.Cancelada;
import ar.edu.unq.Pendiente;
import ar.edu.unq.notificadores.Fidelizacion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FidelizacionTest {

    private Fidelizacion fidelizacion;
    private EstadoReserva estadoCancelada;
    private EstadoReserva estadoPendiente;

    @BeforeEach
    public void setUp() {
        fidelizacion = new Fidelizacion();
        estadoCancelada = new Cancelada();
        estadoPendiente = new Pendiente();
    }

    @Test
    public void testActualizarEstadoCuandoRequiereFidelizacionActua() {
        fidelizacion.actualizarEstado(estadoPendiente, estadoCancelada);
        assertTrue(estadoCancelada.requiereFidelizacion());
    }

    @Test
    public void testActualizarEstadoCuandoNoRequiereFidelizacionNoActua() {
        fidelizacion.actualizarEstado(estadoCancelada, estadoPendiente);
        assertFalse(estadoPendiente.requiereFidelizacion());
    }
}