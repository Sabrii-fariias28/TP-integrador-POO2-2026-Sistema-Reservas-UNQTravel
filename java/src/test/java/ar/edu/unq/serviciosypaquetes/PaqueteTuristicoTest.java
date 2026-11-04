package ar.edu.unq.serviciosypaquetes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

        paquete = new PaqueteTuristico("P1", "Paquete Patagonia", 5, "", "Travel Rock", itemsPredefinidos);


    }

    @Test
    void testPrecioFinal() {

        // Aplicamos un descuento de 10.0 al paquete
        when(itemMock1.precioFinal(10.0)).thenReturn(5000.0);
        when(itemMock2.precioFinal(10.0)).thenReturn(3000.0);


        double precioTotal = paquete.precioFinal(10.0);

        // Verificamos que el paquete haya sumado los valores correctamente (5000 + 3000 = 8000)
        assertEquals(8000.0, precioTotal, "El precio final del paquete debe ser la suma de los precios finales de sus ítems");

        // Verificamos que el paquete efectivamente llamó al metodo
        verify(itemMock1, times(1)).precioFinal(10.0);
        verify(itemMock2, times(1)).precioFinal(10.0);
    }
}
