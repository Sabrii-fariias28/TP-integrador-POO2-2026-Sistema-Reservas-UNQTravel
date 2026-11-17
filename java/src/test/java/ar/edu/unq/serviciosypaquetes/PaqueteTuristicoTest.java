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

        List<ItemCatalogo> itemsConUnPaquete = List.of(itemMock3, paquete);
        paquetePrincipal =  new PaqueteTuristico("P2", "Paquete completo", new ArrayList<>(), "", "Travel Rock", itemsConUnPaquete, 20.0);

    }

    @Test
    void testGetPrecioBaseAPaquete() {
        assertEquals(8000.0, paquete.getPrecioBase());
    }

    @Test
    void testPrecioFinalAplicaDescuentoSobrePrecioBaseDelPaquete() {
        assertEquals(7200.0, paquete.precioFinal());
    }

    @Test
    void testPrecioFinalCalculaRecursivamentePaquetesAnidados() {
        assertEquals(9200.0, paquetePrincipal.getPrecioBase(), 0.001);
        assertEquals(7360.0, paquetePrincipal.precioFinal(), 0.001);
    }

    @Test
    void testObtenerServiciosIndividualesConPaquetesAnidadosRecursivamente() {
        ServicioIndividual excursion = new ServicioIndividual(
                "03", "Excursion A", new ArrayList<>(), "Tour guiado", "ProvC", "Tour", 50.0, 0.0
        );

        PaqueteTuristico subPaquete = new PaqueteTuristico(
                "Sub01", "Subpaquete Aventura", new ArrayList<>(), "Desc", "ProvGeneral",
                List.of(excursion), 5.0
        );

        ServicioIndividual hotel = new ServicioIndividual(
                "01", "Hotel Llao Llao", new ArrayList<>(), "Hotel de lujo", "ProvA", "Hotel", 100.0, 0.0
        );

        PaqueteTuristico paquetePrincipal = new PaqueteTuristico(
                "P02", "Paquete Completo", new ArrayList<>(), "Desc", "ProvGeneral",
                List.of(hotel, subPaquete), 15.0
        );

        List<ServicioIndividual> servicios = paquetePrincipal.obtenerServiciosIndividuales();

        assertEquals(2, servicios.size());
        assertTrue(servicios.contains(hotel));
        assertTrue(servicios.contains(excursion));
    }
}
