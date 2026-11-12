package ar.edu.unq.serviciosypaquetes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PaqueteTuristicoTest {

    private PaqueteTuristico paquete;
    private ItemCatalogo itemMock1;
    private ItemCatalogo itemMock2;

    @BeforeEach
    void setUp() {
        itemMock1 = mock(ServicioIndividual.class);
        itemMock2 = mock(ServicioIndividual.class);
        List<ItemCatalogo> itemsPredefinidos = List.of(itemMock1, itemMock2);

        when(itemMock1.getPrecioBase()).thenReturn(5000.0);
        when(itemMock2.getPrecioBase()).thenReturn(3000.0);

        paquete = new PaqueteTuristico("P1", "Paquete Patagonia", new ArrayList<>(), "", "Travel Rock", itemsPredefinidos, 10.0, 20.0);


    }

    @Test
    void testGetPrecioBaseAplicaDescuentoDelPaquete() {
        // Suma: 8000. Descuento paquete 10% -> 7200
        assertEquals(7200.0, paquete.getPrecioBase());
    }

    @Test
    void testPrecioFinalAplicaDescuentoExtraSobrePrecioBaseDelPaquete() {
        // Precio base del paquete ya es 7200. Le aplicamos un 20% de descuento promocional final.
        // 7200 - 20% = 5760
        assertEquals(5760.0, paquete.precioFinal());
    }
}
