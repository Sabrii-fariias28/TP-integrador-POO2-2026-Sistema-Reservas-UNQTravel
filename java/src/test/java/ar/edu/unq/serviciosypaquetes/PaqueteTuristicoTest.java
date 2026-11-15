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
    private PaqueteTuristico paquetePrincipal;
    private ItemCatalogo itemMock3;

    @BeforeEach
    void setUp() {
        itemMock1 = mock(ServicioIndividual.class);
        itemMock2 = mock(ServicioIndividual.class);
        itemMock3 = mock(ServicioIndividual.class);
        List<ItemCatalogo> itemsPredefinidos = List.of(itemMock1, itemMock2);

        when(itemMock1.precioFinal()).thenReturn(5000.0);
        when(itemMock2.precioFinal()).thenReturn(3000.0);
        when(itemMock3.precioFinal()).thenReturn(2000.0);

        paquete = new PaqueteTuristico("P1", "Paquete Patagonia", new ArrayList<>(), "", "Travel Rock", itemsPredefinidos, 10.0);

        //Para probar la recursividad, tiene el paquete anterior y otro item.
        List<ItemCatalogo> itemsConUnPaquete = List.of(itemMock3, paquete);
        paquetePrincipal =  new PaqueteTuristico("P2", "Paquete completo", new ArrayList<>(), "", "Travel Rock", itemsConUnPaquete, 20.0);

    }

    @Test
    void testGetPrecioBaseAPaquete() {
        // Suma: 8000
        assertEquals(8000.0, paquete.getPrecioBase());
    }

    @Test
    void testPrecioFinalAplicaDescuentoSobrePrecioBaseDelPaquete() {
        // Precio base del paquete es 8000 y se le aplica el 10% de descuento 7200.
        assertEquals(7200.0, paquete.precioFinal());
    }

    @Test
    void testPrecioFinalCalculaRecursivamentePaquetesAnidados() {
        assertEquals(9200.0, paquetePrincipal.getPrecioBase(), 0.001);
        assertEquals(7360.0, paquetePrincipal.precioFinal(), 0.001);
    }
}
