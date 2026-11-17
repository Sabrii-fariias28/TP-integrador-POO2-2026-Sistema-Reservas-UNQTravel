package ar.edu.unq.trasladosytasas;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ar.edu.unq.cicloDeLaReserva.*;

class TrasladoEstandarTest {

    private PlataformaTransporte plataformaMock;
    private Direccion direccion;
    private Reserva reservaMock;

    @BeforeEach
    void setUp() {
        plataformaMock = mock(PlataformaTransporte.class);
        direccion = new Direccion("Av. Corrientes", 1234, "CABA");
        reservaMock = mock(Reserva.class);
    }

    @Test
    void testTieneDisponibilidadVerdaderoConPasajerosYDireccionValida() {
        when(reservaMock.getCantPersonas()).thenReturn(3);
        TrasladoEstandar traslado = new TrasladoEstandar(plataformaMock, direccion);

        assertTrue(traslado.tieneDisponibilidad(reservaMock));
    }

    @Test
    void testTieneDisponibilidadFalsoCuandoCantidadPasajerosEsCeroONegativa() {
        when(reservaMock.getCantPersonas()).thenReturn(0);
        TrasladoEstandar traslado = new TrasladoEstandar(plataformaMock, direccion);

        assertFalse(traslado.tieneDisponibilidad(reservaMock));
    }

    @Test
    void testTieneDisponibilidadFalsoCuandoDireccionEsNull() {
        when(reservaMock.getCantPersonas()).thenReturn(2);
        TrasladoEstandar traslado = new TrasladoEstandar(plataformaMock, null);

        assertFalse(traslado.tieneDisponibilidad(reservaMock));
    }

    @Test
    void testCalcularCostoDelegaEnPlataformaTransporte() {
        when(reservaMock.getCantPersonas()).thenReturn(4);
        when(plataformaMock.estimarTraslado(4, direccion)).thenReturn(6000.0f);

        TrasladoEstandar traslado = new TrasladoEstandar(plataformaMock, direccion);
        double costo = traslado.calcularCosto(reservaMock);

        assertEquals(6000.0, costo, 0.001);
        verify(plataformaMock, times(1)).estimarTraslado(4, direccion);
    }
}