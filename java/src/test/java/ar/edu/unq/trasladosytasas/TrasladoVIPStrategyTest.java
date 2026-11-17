package ar.edu.unq.trasladosytasas;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ar.edu.unq.cicloDeLaReserva.*;

class TrasladoVIPStrategyTest {

    private TrasladoVIP trasladoVipMock;
    private Reserva reservaMock;
    private TrasladoVIPStrategy strategy;

    @BeforeEach
    void setUp() {
        trasladoVipMock = mock(TrasladoVIP.class);
        reservaMock = mock(Reserva.class);
        strategy = new TrasladoVIPStrategy(trasladoVipMock);
    }

    @Test
    void testTieneDisponibilidadSiempreEsVerdadero() {
        assertTrue(strategy.tieneDisponibilidad(reservaMock));
    }

    @Test
    void testCalcularCostoDelegaSubtotalDeServiciosEnTrasladoVIP() {
        when(reservaMock.calcularPrecioServicios()).thenReturn(20000.0);
        when(trasladoVipMock.calcularCosto(20000.0f)).thenReturn(8000.0f);

        double costo = strategy.calcularCosto(reservaMock);

        assertEquals(8000.0, costo, 0.001);
        verify(trasladoVipMock, times(1)).calcularCosto(20000.0f);
    }
}