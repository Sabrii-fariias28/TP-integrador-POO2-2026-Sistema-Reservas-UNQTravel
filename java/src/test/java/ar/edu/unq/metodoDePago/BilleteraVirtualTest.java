package ar.edu.unq.metodoDePago;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BilleteraVirtualTest {
    private ApiBilleteraVirtual apiMock;
    private BilleteraVirtual billeteraDefault;

    @BeforeEach
    void setUp() {
        apiMock = mock(ApiBilleteraVirtual.class);
        billeteraDefault = new BilleteraVirtual(5000.0, apiMock);
    }

    @Test
    void testValidarDatos_FallaSiNoHaySaldoSuficiente() {
        // la API devuelva false con saldo insuficiente
        when(apiMock.verificarSaldoSuficiente()).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {billeteraDefault.validarDatos();});

        assertEquals("Error: No hay suficiente saldo en la cuenta.", ex.getMessage());
        verify(apiMock).verificarSaldoSuficiente();
    }

    @Test
    void testValidarDatos_Exitoso() {
        //la API devuelva true con saldo suficiente
        when(apiMock.verificarSaldoSuficiente()).thenReturn(true);

        assertDoesNotThrow(() -> {billeteraDefault.validarDatos();});

        verify(apiMock).verificarSaldoSuficiente();
    }

    @Test
    void testReservarFondos_LlamaALaApi() {
        billeteraDefault.reservarFondos();

        verify(apiMock).bloquearSaldoHastaConfirmar();
    }

    @Test
    void testEjecutarTransaccion_LlamaALaApi() {
        double monto = 1250.50;

        billeteraDefault.ejecutarTransaccion(monto);

        verify(apiMock).acreditacionEnTiempoRealAlVendedor(monto);
    }

    @Test
    void testRealizarReembolso_EjecutaSinProblemas() {
        //solo para 100 de covertura
        assertDoesNotThrow(() -> {billeteraDefault.realizarReembolso(500.0);});
    }
}
