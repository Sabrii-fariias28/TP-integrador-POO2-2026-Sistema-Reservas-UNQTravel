package ar.edu.unq.reportes;

import ar.edu.unq.Catalogo;
import ar.edu.unq.serviciosypaquetes.Disponibilidad;
import ar.edu.unq.serviciosypaquetes.PaqueteTuristico;
import ar.edu.unq.serviciosypaquetes.ServicioIndividual;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReporteOcupacionTest {

    private Catalogo catalogo;
    private LocalDate inicioPeriodo;
    private LocalDate finPeriodo;
    private ReporteOcupacion reporte;

    @BeforeEach
    void setUp() {
        catalogo = new Catalogo(new ArrayList<>());
        inicioPeriodo = LocalDate.of(2026, 11, 1);
        finPeriodo = LocalDate.of(2026, 11, 30);

        Disponibilidad disp1 = new Disponibilidad(LocalDate.of(2026, 11, 10), 10);
        disp1.agendarReservas(4);

        Disponibilidad dispFuera = new Disponibilidad(LocalDate.of(2026, 12, 5), 20);

        List<Disponibilidad> cuposHotel = List.of(disp1, dispFuera);

        ServicioIndividual hotel = new ServicioIndividual(
                "01", "Hotel Llao Llao", cuposHotel, "Hotel de lujo", "ProveedorA", "Hotel", 100.0, 0.0
        );

        catalogo.agregarItem(hotel);

        reporte = new ReporteOcupacion(catalogo, null, inicioPeriodo, finPeriodo);
    }

    @Test
    void testGetDetallesCalculaCorrectamenteOcupacionEnPeriodo() {
        List<DetalleOcupacion> detalles = reporte.getDetalles();

        assertEquals(1, detalles.size());

        DetalleOcupacion detalleHotel = detalles.getFirst();

        assertEquals("Hotel", detalleHotel.categoria());
        assertEquals(10, detalleHotel.cuposTotales());
        assertEquals(4, detalleHotel.cuposReservados());
        assertEquals(6, detalleHotel.cuposLibres());
    }

    @Test
    void testReporteSinServiciosEnPeriodoDevuelveDetallesVacios() {
        LocalDate inicioLejano = LocalDate.of(2025, 1, 1);
        LocalDate finLejano = LocalDate.of(2025, 1, 31);

        ReporteOcupacion reporteFueraDeRango = new ReporteOcupacion(catalogo, null, inicioLejano, finLejano);

        List<DetalleOcupacion> detalles = reporteFueraDeRango.getDetalles();

        assertTrue(detalles.stream().allMatch(d -> d.cuposTotales() == 0));
    }

    @Test
    void testAceptarVisitaLlamaAlVisitorCorrectamente() {
        VisitorFormato visitorMock = mock(VisitorFormato.class);

        reporte.aceptarVisita(visitorMock);

        verify(visitorMock, times(1)).visitar(reporte);
    }

    @Test
    void testGettersDePeriodoDevuelvenLasFechasCorrectas() {
        assertEquals(LocalDate.of(2026, 11, 1), reporte.getPeriodoInicio());
        assertEquals(LocalDate.of(2026, 11, 30), reporte.getPeriodoFin());
    }

    @Test
    void testDisponibilidadFueraDePeriodoEsIgnoradaPorElMetodoAuxiliar() {
        Catalogo catalogoPrueba = new Catalogo(new ArrayList<>());
        Disponibilidad dispAntes = new Disponibilidad(LocalDate.of(2026, 10, 15), 10);
        dispAntes.agendarReservas(2);
        Disponibilidad dispDespues = new Disponibilidad(LocalDate.of(2026, 12, 10), 10);
        dispDespues.agendarReservas(3);

        ServicioIndividual servicioPrueba = new ServicioIndividual(
                "02", "Tour Test", List.of(dispAntes, dispDespues), "Tour", "Prov", "Tour", 50.0, 0.0
        );
        catalogoPrueba.agregarItem(servicioPrueba);

        ReporteOcupacion reportePrueba = new ReporteOcupacion(catalogoPrueba, null, inicioPeriodo, finPeriodo);
        List<DetalleOcupacion> detalles = reportePrueba.getDetalles();

        assertEquals(0, detalles.getFirst().cuposTotales());
    }
    @Test
    void testGetDetallesConMultiplesCategoriasYPaquetesSeparaPorCategoria() {
        Disponibilidad dispExcursion = new Disponibilidad(LocalDate.of(2026, 11, 15), 20);
        dispExcursion.agendarReservas(5);

        ServicioIndividual excursion = new ServicioIndividual(
                "02", "Excursión Isla Victoria", List.of(dispExcursion), "Paseo", "ProveedorB", "Excursión", 50.0, 0.0
        );

        PaqueteTuristico paquete = new PaqueteTuristico(
                "P1", "Pack Bariloche", new ArrayList<>(), "Combo", "UNQTravel", List.of(excursion), 10.0
        );

        catalogo.agregarItem(paquete);

        List<DetalleOcupacion> detalles = reporte.getDetalles();

        assertEquals(2, detalles.size());

        DetalleOcupacion detalleHotel = detalles.getFirst();
        assertEquals("Hotel", detalleHotel.categoria());
        assertEquals(10, detalleHotel.cuposTotales());
        assertEquals(4, detalleHotel.cuposReservados());
        assertEquals(6, detalleHotel.cuposLibres());

        DetalleOcupacion detalleExcursion = detalles.get(1);
        assertEquals("Excursión", detalleExcursion.categoria());
        assertEquals(20, detalleExcursion.cuposTotales());
        assertEquals(5, detalleExcursion.cuposReservados());
        assertEquals(15, detalleExcursion.cuposLibres());
    }
}