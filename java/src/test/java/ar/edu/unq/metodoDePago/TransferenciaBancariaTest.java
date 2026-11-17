package ar.edu.unq.metodoDePago;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TransferenciaBancariaTest {

    private ApiTransferenciaBancaria apiMock;
    private TransferenciaBancaria transferenciaDefault;
    private String cbuValido;

    @BeforeEach
    void setUp() {
        apiMock = mock(ApiTransferenciaBancaria.class);
        cbuValido = "1234567890123456789012";
        transferenciaDefault = new TransferenciaBancaria(cbuValido, "mi.alias", apiMock);
    }

    @Test
    void testValidarDatos_FallaSiCbuInvalido() {
        TransferenciaBancaria transferenciaInvalida = new TransferenciaBancaria("1A3", "mi.alias", apiMock);

        RuntimeException ex = assertThrows(RuntimeException.class, transferenciaInvalida::validarDatos);
        assertEquals("Error: No es un CBU valido.", ex.getMessage());
    }

    @Test
    void testValidarDatos_FallaSiAliasVacioONulo() {
        TransferenciaBancaria transferenciaSinAlias = new TransferenciaBancaria(cbuValido, "", apiMock);

        RuntimeException ex = assertThrows(RuntimeException.class, transferenciaSinAlias::validarDatos);
        assertEquals("Error: El alias es invalido.", ex.getMessage());
    }

    @Test
    void testValidarDatos_FallaSiApiRechazaTransferencia() {
        TransferenciaBancaria transferencia = new TransferenciaBancaria(cbuValido, "mi.alias", apiMock);

        when(apiMock.validarTransferencia(cbuValido, "mi.alias")).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class, transferencia::validarDatos);
        assertEquals("Error: La API de la transferencia bancaria rechazó la operación.", ex.getMessage());
    }

    @Test
    void testValidarDatos_Exitoso() {
        TransferenciaBancaria transferencia = new TransferenciaBancaria(cbuValido, "mi.alias", apiMock);

        when(apiMock.validarTransferencia(cbuValido, "mi.alias")).thenReturn(true);

        assertDoesNotThrow(transferencia::validarDatos);
    }

    @Test
    void testReservarFondos_LlamaALaApi() {
        transferenciaDefault.reservarFondos();
        verify(apiMock).transferenciaDirecta();
    }

    @Test
    void testEjecutarTransaccion_LlamaALaApi() {
        transferenciaDefault.ejecutarTransaccion(300.0);
        verify(apiMock).transferenciaInmediata(300.0);
    }

    @Test
    void testRealizarReembolso_LlamaALaApi() {
        transferenciaDefault.realizarReembolso(150.0);
        verify(apiMock).reembolsarMonto(150.0);
    }

    @Test
    void testValidarDatos_FallaSiCbuEsNulo() {
        TransferenciaBancaria transferenciaCbuNulo = new TransferenciaBancaria(null, "mi.alias", apiMock);

        RuntimeException ex = assertThrows(RuntimeException.class, transferenciaCbuNulo::validarDatos);
        assertEquals("Error: No es un CBU valido.", ex.getMessage());
    }

    @Test
    void testValidarDatos_FallaSiAliasEsNulo() {
        TransferenciaBancaria transferenciaAliasNulo = new TransferenciaBancaria(cbuValido, null, apiMock);

        RuntimeException ex = assertThrows(RuntimeException.class, transferenciaAliasNulo::validarDatos);
        assertEquals("Error: El alias es invalido.", ex.getMessage());
    }
}
