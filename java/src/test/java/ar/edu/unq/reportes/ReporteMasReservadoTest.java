package ar.edu.unq.reportes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.metodoDePago.*;
import ar.edu.unq.notificadores.*;
import ar.edu.unq.trasladosytasas.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ar.edu.unq.cicloDeLaReserva.*;
import ar.edu.unq.serviciosypaquetes.*;
import ar.edu.unq.Catalogo;

class ReporteMasReservadoTest {

    private Catalogo catalogo;
    private GestorReservas gestorReservas;
    private ServicioIndividual hotel;
    private ServicioIndividual excursion;
    private PaqueteTuristico paquete;
    private LocalDate inicioPeriodo;
    private LocalDate finPeriodo;
    private ReporteMasReservado reporte;

    @BeforeEach
    void setUp() {
        inicioPeriodo = LocalDate.of(2026, 11, 1);
        finPeriodo = LocalDate.of(2026, 11, 30);

        LocalDate dia1 = LocalDate.of(2026, 11, 5);
        LocalDate dia2 = LocalDate.of(2026, 11, 20);
        LocalDate diaFueraAntes = LocalDate.of(2026, 10, 25);
        LocalDate diaFueraDespues = LocalDate.of(2026, 12, 5);

        hotel = new ServicioIndividual("H1", "Hotel Hilton", new ArrayList<>(), "5 estrellas", "Hilton", "Alojamiento", 10000.0, 0.0);
        hotel.agregarFecha(dia1, 20);
        hotel.agregarFecha(dia2, 20);
        hotel.agregarFecha(diaFueraAntes, 20);
        hotel.agregarFecha(diaFueraDespues, 20);

        excursion = new ServicioIndividual("E1", "City Tour", new ArrayList<>(), "Paseo", "TurismoBA", "Excursión", 5000.0, 0.0);
        excursion.agregarFecha(dia2, 20);

        paquete = new PaqueteTuristico("P1", "Pack BA", new ArrayList<>(), "Combo", "UNQTravel", List.of(hotel, excursion), 10.0);
        paquete.agregarFecha(dia2, 20);

        catalogo = new Catalogo(new ArrayList<>(List.of(hotel, excursion, paquete)));
        gestorReservas = new GestorReservas();
        reporte = new ReporteMasReservado(catalogo, gestorReservas, inicioPeriodo, finPeriodo);
    }

    @Test
    void testGettersYAceptarVisitaConVisitorReal() {
        assertEquals(inicioPeriodo, reporte.getPeriodoInicio());
        assertEquals(finPeriodo, reporte.getPeriodoFin());

        CSV csv = new CSV();
        reporte.aceptarVisita(csv);
        assertEquals("NombreItem,CantidadVendida,PrecioPromedioEfectivo\n", csv.getSalida());
    }

    @Test
    void testGetDetallesFiltraPorEstadoYPeriodoOrdenaYCalculaPromedio() {
        LocalDate dia1 = LocalDate.of(2026, 11, 5);
        LocalDate dia2 = LocalDate.of(2026, 11, 20);
        LocalDate diaFueraAntes = LocalDate.of(2026, 10, 25);
        LocalDate diaFueraDespues = LocalDate.of(2026, 12, 5);

        Reserva r1 = gestorReservas.crearReserva(2, dia1, dia1, mock(MetodoDePago.class), mock(NotificadorManager.class), new PuntoEncuentro(5));
        r1.agregarItem(hotel);
        r1.cambiarEstado(new Finalizada());

        Reserva r2 = gestorReservas.crearReserva(4, dia2, dia2, mock(MetodoDePago.class), mock(NotificadorManager.class), new PuntoEncuentro(5));
        r2.agregarItem(hotel);
        r2.cambiarEstado(new Finalizada());

        Reserva r3 = gestorReservas.crearReserva(3, dia2, dia2, mock(MetodoDePago.class), mock(NotificadorManager.class), new PuntoEncuentro(5));
        r3.agregarItem(paquete);
        r3.cambiarEstado(new Finalizada());

        Reserva rPendiente = gestorReservas.crearReserva(2, dia2, dia2, mock(MetodoDePago.class), mock(NotificadorManager.class), new PuntoEncuentro(5));
        rPendiente.agregarItem(excursion);

        Reserva rAntes = gestorReservas.crearReserva(1, diaFueraAntes, diaFueraAntes, mock(MetodoDePago.class), mock(NotificadorManager.class), new PuntoEncuentro(5));
        rAntes.agregarItem(hotel);
        rAntes.cambiarEstado(new Finalizada());

        Reserva rDespues = gestorReservas.crearReserva(1, diaFueraDespues, diaFueraDespues, mock(MetodoDePago.class), mock(NotificadorManager.class), new PuntoEncuentro(5));
        rDespues.agregarItem(hotel);
        rDespues.cambiarEstado(new Finalizada());

        assertEquals(6, gestorReservas.getReservas().size());

        List<DetalleServicioVendido> detalles = reporte.getDetalles();

        assertEquals(2, detalles.size());

        assertEquals("Hotel Hilton", detalles.getFirst().nombreItem());
        assertEquals(2, detalles.getFirst().cantidadVendida());
        assertEquals(10000.0, detalles.getFirst().precioPromedioEfectivo(), 0.001);

        assertEquals("Pack BA", detalles.get(1).nombreItem());
        assertEquals(1, detalles.get(1).cantidadVendida());
        assertEquals(13500.0, detalles.get(1).precioPromedioEfectivo(), 0.001);
    }

    @Test
    void testGetDetallesCuandoNoHayReservasEnPeriodo() {
        List<DetalleServicioVendido> detalles = reporte.getDetalles();
        assertTrue(detalles.isEmpty());
    }
}
