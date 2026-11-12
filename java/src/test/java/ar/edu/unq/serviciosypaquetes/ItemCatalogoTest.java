package ar.edu.unq.serviciosypaquetes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class ItemCatalogoTest {
    private ServicioIndividual servicio;
    private LocalDate dia1;
    private LocalDate dia2;

    @BeforeEach
    void setUp() {
        dia1 = LocalDate.of(2026, 12, 1);
        dia2 = LocalDate.of(2026, 12, 2);

        servicio = new ServicioIndividual("V1", "Vuelo Bariloche", new ArrayList<>(), "Desc", "Aero", "Vuelo", 4000.0, 10.0);
        servicio.agregarFecha(dia1, 10);
        servicio.agregarFecha(dia2, 5);
    }

    @Test
    void testEstaDisponibleDevuelveTrueCuandoHayCupoEnFecha() {
        assertTrue(servicio.estaDisponible(dia1, 10));
        assertTrue(servicio.estaDisponible(dia2, 5));
    }

    @Test
    void testEstaDisponibleDevuelveFalseCuandoNoHayCupoOFechaNoExiste() {
        assertFalse(servicio.estaDisponible(dia1, 11)); // Supera capacidad
        assertFalse(servicio.estaDisponible(LocalDate.of(2026, 12, 3), 1)); // Fecha no cargada
    }

    @Test
    void testReservarRangoExitoso() {
        servicio.reservarRango(dia1, dia2, 4);

        assertEquals(6, servicio.buscarPorFecha(dia1).orElseThrow().cuposDisponibles());
        assertEquals(1, servicio.buscarPorFecha(dia2).orElseThrow().cuposDisponibles());
    }

    @Test
    void testReservarRangoFallaSiUnDiaNoTieneCupo() {
        assertThrows(IllegalArgumentException.class, () -> servicio.reservarRango(dia1, dia2, 6));
        assertEquals(10, servicio.buscarPorFecha(dia1).orElseThrow().cuposDisponibles());
    }
    @Test
    void testGettersItemCatalogo() {
        assertEquals("V1", servicio.getId());
        assertEquals("Vuelo Bariloche", servicio.getNombre());
        assertEquals("Desc", servicio.getDescripcion());
        assertNotNull(servicio.getCupos());
        assertEquals(4000.0, servicio.getPrecioBase());
        assertEquals(3600.0, servicio.precioFinal());
    }

    @Test
    void testCancelarRangoExitoso() {
        servicio.reservarRango(dia1, dia2, 2);

        servicio.cancelar(dia1, dia2, 2);

        assertEquals(10, servicio.buscarPorFecha(dia1).orElseThrow().cuposDisponibles());
        assertEquals(5, servicio.buscarPorFecha(dia2).orElseThrow().cuposDisponibles());
    }

    @Test
    void testCancelarRangoFallaPorFaltaDeReservaPrevia() {
        servicio.reservarRango(dia1, dia2, 1);

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> servicio.cancelar(dia1, dia2, 3));

        assertTrue(e.getMessage().contains("No hay cupos para el día"));
    }

    @Test
    void testReservarYCancelarLanzanExcepcionSiLaFechaNoExiste() {
        LocalDate fechaInexistente = LocalDate.of(2026, 12, 25);

        Exception eReserva = assertThrows(IllegalArgumentException.class, () -> servicio.reservarRango(fechaInexistente, fechaInexistente, 1));
        assertTrue(eReserva.getMessage().contains("No se puede reservar"));

        Exception eCancela = assertThrows(IllegalArgumentException.class, () -> servicio.cancelar(fechaInexistente, fechaInexistente, 1));

        assertTrue(eCancela.getMessage().contains("No se puede cancelar"));
    }

    @Test
    void testIteracionCeroEnForDeFechas() {
        servicio.reservarRango(dia2, dia1, 1);

        assertEquals(10, servicio.buscarPorFecha(dia1).orElseThrow().cuposDisponibles());
        assertEquals(5, servicio.buscarPorFecha(dia2).orElseThrow().cuposDisponibles());
    }

    @Test
    void testVerificarSiHayCupos(){
        assertTrue(servicio.hayCupos(10));
        assertFalse(servicio.hayCupos(15));
    }

    @Test
    void testEstaDisponibleEnRangoDevuelveTrueCuandoHayCupoEnTodoElRango() {
        // El servicio tiene cupo 10 en dia1 (01/12) y cupo 5 en dia2 (02/12) hay espacio para dos personas...
        boolean resultado = servicio.estaDisponibleEnRango(dia1, dia2, 2);

        assertTrue(resultado);
    }

    @Test
    void testEstaDisponibleEnRangoDevuelveFalseCuandoFallaAlMenosUnDia() {
        // En dia1 hay cupo para 10, pero pedimos 12...
        boolean resultado = servicio.estaDisponibleEnRango(dia1, dia2, 12);

        assertFalse(resultado);
    }
}