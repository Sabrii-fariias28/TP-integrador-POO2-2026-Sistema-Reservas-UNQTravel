package ar.edu.unq.metodoDePago;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TarjetaDeCreditoInternacionalTest {
    private ApiTarjetaDeCredito apiMock;
    private TarjetaDeCreditoInternacional tarjeta;
    private YearMonth fechaValida;
    private String numValido;
    private String cvvValido;

    @BeforeEach
    void setUp() {
        apiMock = mock(ApiTarjetaDeCredito.class);
        fechaValida = YearMonth.of(2028, 12);
        numValido = "1234567890123456";
        cvvValido = "123";

        tarjeta = new TarjetaDeCreditoInternacional(apiMock, numValido, cvvValido, fechaValida);
    }

    @Test
    void testValidarDatos_FallaSiFechaVencimientoEsNula() {
        TarjetaDeCreditoInternacional tarjetaInvalida = new TarjetaDeCreditoInternacional(apiMock, numValido, cvvValido, null);

        RuntimeException ex = assertThrows(RuntimeException.class, tarjetaInvalida::validarDatos);
        assertEquals("Error: La fecha de vencimiento esta vacia.", ex.getMessage());
    }

    @Test
    void testValidarDatos_FallaSiNumeroTarjetaEsNuloOUnoVacio() {
        TarjetaDeCreditoInternacional tarjetaVacia = new TarjetaDeCreditoInternacional(apiMock, "", cvvValido, fechaValida);

        RuntimeException ex = assertThrows(RuntimeException.class, tarjetaVacia::validarDatos);
        assertEquals("Error: El número de tarjeta es inválido.", ex.getMessage());
    }

    @Test
    void testValidarDatos_FallaSiCvvEsInvalido() {
        // CVV con menos de 3 dígitos o letras
        TarjetaDeCreditoInternacional tarjetaCvvMalo = new TarjetaDeCreditoInternacional(apiMock, numValido, "12", fechaValida);

        RuntimeException ex = assertThrows(RuntimeException.class, tarjetaCvvMalo::validarDatos);
        assertEquals("Error: El código CVV es inválido.", ex.getMessage());
    }

    @Test
    void testValidarDatos_FallaSiApiRechazaLaTarjeta() {
        //la API para que devuelva false al verificar los datos
        when(apiMock.verificarDatos(numValido, cvvValido, fechaValida)).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class, tarjeta::validarDatos);
        assertEquals("Error: La API de la tajeta de credito internacional rechazó la tarjeta.", ex.getMessage());
        verify(apiMock).verificarDatos(numValido, cvvValido, fechaValida);
    }

    @Test
    void testValidarDatos_Exitoso() {
        // la API para que devuelva true
        when(apiMock.verificarDatos(numValido, cvvValido, fechaValida)).thenReturn(true);

        assertDoesNotThrow(tarjeta::validarDatos);
        verify(apiMock).verificarDatos(numValido, cvvValido, fechaValida);
    }

    @Test
    void testReservarFondos_LlamaALaApi() {
        tarjeta.reservarFondos();
        verify(apiMock).preAutorizarBancoEmisor();
    }

    @Test
    void testEjecutarTransaccion_LlamaALaApi() {
        double monto = 2500.0;
        tarjeta.ejecutarTransaccion(monto);
        verify(apiMock).transferenciaInmediata(monto);
    }

    @Test
    void testRealizarReembolso_LlamaALaApi() {
        double monto = 450.0;
        tarjeta.realizarReembolso(monto);
        verify(apiMock).reembolsarMonto(monto);
    }
}
