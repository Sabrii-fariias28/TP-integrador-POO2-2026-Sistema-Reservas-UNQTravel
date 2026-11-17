package ar.edu.unq.reportes;
import ar.edu.unq.Catalogo;
import ar.edu.unq.serviciosypaquetes.ServicioIndividual;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReporteExposicionTest {

    private ReporteExposicion reporte;

    @BeforeEach
    void setUp() {
        Catalogo catalogo = new Catalogo(new ArrayList<>());
        ServicioIndividual item1 = new ServicioIndividual(
                "01", "Hotel Llao Llao", new ArrayList<>(), "Hotel de lujo", "ProveedorA", "Hotel", 100.0, 10.0
        );
        ServicioIndividual item2 = new ServicioIndividual(
                "02", "Excursion A", new ArrayList<>(), "Tour guiado", "ProveedorA", "Tour", 50.0, 0.0
        );
        ServicioIndividual item3 = new ServicioIndividual(
                "03", "Vuelo BsAs", new ArrayList<>(), "Vuelo de cabotaje", "ProveedorB", "Vuelo", 200.0, 5.0
        );

        catalogo.agregarItem(item1);
        catalogo.agregarItem(item2);
        catalogo.agregarItem(item3);

        reporte = new ReporteExposicion(catalogo, new ArrayList<>());
    }

    @Test
    void testGetDetallesCalculaCorrectamenteCantidadYPorcentajes() {
        List<DetalleExposicion> detalles = reporte.getDetalles();

        assertEquals(2, detalles.size());

        DetalleExposicion detalleA = detalles.stream()
                .filter(d -> d.proveedor().equals("ProveedorA"))
                .findFirst()
                .orElse(null);

        DetalleExposicion detalleB = detalles.stream()
                .filter(d -> d.proveedor().equals("ProveedorB"))
                .findFirst()
                .orElse(null);

        assertNotNull(detalleA);
        assertNotNull(detalleB);

        assertEquals(2, detalleA.cantidadItems());
        assertEquals(66.66, detalleA.porcentajeExposicion(), 0.01);

        assertEquals(1, detalleB.cantidadItems());
        assertEquals(33.33, detalleB.porcentajeExposicion(), 0.01);
    }

    @Test
    void testReporteConCatalogoVacioDevuelveDetallesVacios() {
        Catalogo catalogoVacio = new Catalogo(new ArrayList<>());
        ReporteExposicion reporteVacio = new ReporteExposicion(catalogoVacio, new ArrayList<>());

        List<DetalleExposicion> detalles = reporteVacio.getDetalles();

        assertTrue(detalles.isEmpty());
    }

    @Test
    void testAceptarVisitaLlamaAlVisitorCorrectamente() {
        VisitorFormato visitorMock = mock(VisitorFormato.class);
        reporte.aceptarVisita(visitorMock);
        verify(visitorMock, times(1)).visitar(reporte);
    }
}