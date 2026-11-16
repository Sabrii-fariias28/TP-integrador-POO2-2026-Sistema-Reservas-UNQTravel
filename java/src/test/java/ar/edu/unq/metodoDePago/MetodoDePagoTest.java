package ar.edu.unq.metodoDePago;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class MetodoDePagoTest {
    private MetodoDePago metodoPagoSpy;

    @BeforeEach
    void setUp(){
        metodoPagoSpy = spy(MetodoDePago.class); // Se usa spy sobre una clase abstracta para recorrer...
    }

    @Test
    void testRealizarPago_CuandoTodosLosPasosSonExitosos_SeEjecutanEnOrden() {
        double monto = 45000.0;

        metodoPagoSpy.realizarPago(monto);

        verify(metodoPagoSpy).validarDatos();
        verify(metodoPagoSpy).reservarFondos();
        verify(metodoPagoSpy).ejecutarTransaccion(monto);
        verify(metodoPagoSpy).notificarResultado();
    }

    @Test
    void testRealizarPago_CuandoFallaUnPaso_SeCortaElFlujoYNoSigue() {
        double monto = 12000.0;
        // Aca Forzamos a que falle uno de los primeros pasos por ej: reservarFondos)
        doThrow(new RuntimeException("Error: No hay suficiente saldo en la cuenta.")).when(metodoPagoSpy).reservarFondos();

        // Verificamos que lanza la excepción al intentar pagar
        assertThrows(RuntimeException.class, () -> {metodoPagoSpy.realizarPago(monto);});

        // Se ejecutó la validación y la reserva (que falló),
        verify(metodoPagoSpy).validarDatos();
        verify(metodoPagoSpy).reservarFondos();

        // pero NUNCA debió llegar a ejecutar la transacción ni notificar.
        verify(metodoPagoSpy, never()).ejecutarTransaccion(monto);
        verify(metodoPagoSpy, never()).notificarResultado();
    }

    @Test
    void testNotificarResultadoPorDefecto_EjecutaSinProblemas() {
        // el comportamiento por defecto de la clase abstracta
        assertDoesNotThrow(() -> {metodoPagoSpy.notificarResultado();});
    }

    @Test
    void testRealizarReembolso_LlamaAlMetodoAbstracto() {
        double monto = 50.0;
        //Se verifica que el metodo responde bien a la llamada
        metodoPagoSpy.realizarReembolso(monto);

        verify(metodoPagoSpy).realizarReembolso(monto);
    }
}
