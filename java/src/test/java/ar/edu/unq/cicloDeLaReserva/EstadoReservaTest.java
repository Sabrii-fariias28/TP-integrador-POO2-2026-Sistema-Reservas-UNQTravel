package ar.edu.unq.cicloDeLaReserva;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ar.edu.unq.ItemCatalogo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EstadoReservaTest {
    private EstadoReserva estadoBase;
    private Reserva reservaMock;
    private ItemCatalogo itemMock;

    @BeforeEach
    void setUp() {
        reservaMock = mock(Reserva.class);
        itemMock = mock(ItemCatalogo.class);
        estadoBase = new EstadoReserva();
    }

    @Test
    void porDefectoAgregarItemLanzaOperacionInvalida() {
        assertThrows(OperacionInvalida.class, () -> estadoBase.agregarItem(reservaMock, itemMock));
    }

    @Test
    void porDefectoConfirmarReservaLanzaOperacionInvalida() {
        assertThrows(OperacionInvalida.class, () -> estadoBase.confirmarReserva(reservaMock));
    }

    @Test
    void porDefectoRealizarCheckInLanzaOperacionInvalida() {
        assertThrows(OperacionInvalida.class, () -> estadoBase.realizarCheckIn(reservaMock));
    }
}
