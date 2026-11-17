package ar.edu.unq.reportes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VisitorsFormatoTest {

    private ReporteExposicion repExposicionMock;
    private ReporteOcupacion repOcupacionMock;
    private ReporteMasReservado repMasReservadoMock;

    @BeforeEach
    void setUp() {
        repExposicionMock = mock(ReporteExposicion.class);
        when(repExposicionMock.getDetalles()).thenReturn(List.of(
                new DetalleExposicion("Despegar", 2, 50.0)
        ));

        repOcupacionMock = mock(ReporteOcupacion.class);
        when(repOcupacionMock.getPeriodoInicio()).thenReturn(LocalDate.of(2026, 11, 1));
        when(repOcupacionMock.getPeriodoFin()).thenReturn(LocalDate.of(2026, 11, 30));
        when(repOcupacionMock.getDetalles()).thenReturn(List.of(
                new DetalleOcupacion("Alojamiento", 100, 40, 60)
        ));

        repMasReservadoMock = mock(ReporteMasReservado.class);
        when(repMasReservadoMock.getPeriodoInicio()).thenReturn(LocalDate.of(2026, 11, 1));
        when(repMasReservadoMock.getPeriodoFin()).thenReturn(LocalDate.of(2026, 11, 30));
        when(repMasReservadoMock.getDetalles()).thenReturn(List.of(
                new DetalleServicioVendido("Hotel Hilton", 10, 15000.0)
        ));
    }

    @Test
    void testTextoPlanoFormateaTodosLosReportes() {
        TextoPlano visitor = new TextoPlano();
        assertEquals("", visitor.getSalida());

        visitor.visitar(repExposicionMock);
        assertTrue(visitor.getSalida().contains("Despegar"));
        assertTrue(visitor.getSalida().contains("Items=2"));

        visitor.visitar(repOcupacionMock);
        assertTrue(visitor.getSalida().contains("Alojamiento"));
        assertTrue(visitor.getSalida().contains("Totales=100, Reservados=40, Libres=60"));

        visitor.visitar(repMasReservadoMock);
        assertTrue(visitor.getSalida().contains("Hotel Hilton"));
        assertTrue(visitor.getSalida().contains("Vendidos=10"));
    }

    @Test
    void testCSVFormateaTodosLosReportes() {
        CSV visitor = new CSV();

        visitor.visitar(repExposicionMock);
        assertEquals("Proveedor,CantidadItems,PorcentajeExposicion\nDespegar,2,50.0\n", visitor.getSalida());

        visitor.visitar(repOcupacionMock);
        assertEquals("Categoria,CuposTotales,CuposReservados,CuposLibres\nAlojamiento,100,40,60\n", visitor.getSalida());

        visitor.visitar(repMasReservadoMock);
        assertEquals("NombreItem,CantidadVendida,PrecioPromedioEfectivo\nHotel Hilton,10,15000.0\n", visitor.getSalida());
    }

    @Test
    void testHTMLFormateaTodosLosReportes() {
        HTML visitor = new HTML();

        visitor.visitar(repExposicionMock);
        assertTrue(visitor.getSalida().startsWith("<table>"));
        assertTrue(visitor.getSalida().contains("<td>Despegar</td><td>2</td><td>50.0%</td>"));
        assertTrue(visitor.getSalida().endsWith("</table>"));

        visitor.visitar(repOcupacionMock);
        assertTrue(visitor.getSalida().contains("<td>Alojamiento</td><td>100</td><td>40</td><td>60</td>"));

        visitor.visitar(repMasReservadoMock);
        assertTrue(visitor.getSalida().contains("<td>Hotel Hilton</td><td>10</td><td>15000.0</td>"));
    }
}