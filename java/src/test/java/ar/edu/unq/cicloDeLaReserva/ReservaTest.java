package ar.edu.unq.cicloDeLaReserva;
import ar.edu.unq.ItemCatalogo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservaTest {

    @Test
    void unaReservaPendientePermiteAgregarUnServicio() {
        // Arrange: preparo los objetos que necesito
        Reserva reserva = new Reserva(2);
        ItemCatalogo item = mock(ItemCatalogo.class);

        // Act: ejecuto el comportamiento que quiero probar
        reserva.agregarItem(item);

        // Assert: verifico el resultado
        assertTrue(reserva.getItemsCatalogo().contains(item));
    }
}
