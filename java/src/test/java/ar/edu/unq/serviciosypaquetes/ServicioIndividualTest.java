package ar.edu.unq.serviciosypaquetes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ServicioIndividualTest {

    private ServicioIndividual servicio;

    @BeforeEach
    void setUp() {
        servicio = new ServicioIndividual("1", "Vuelo a Bariloche", 10, "Vuelo Nacional", "Aerolineas Argentinas", "Vuelo", 4000.0);
    }

    @Test
    void testEstaDisponibleDevuelveTrueCuandoHayCupos() {
        // El servicio se inicializó con 10 cupos, debería estar disponible
        assertTrue(servicio.estaDisponible(), "El servicio debería estar disponible si tiene cupos mayores a 0");
    }

    @Test
    void testEstaDisponibleDevuelveFalseCuandoNoHayCupos() {
        // Disminuimos los cupos a 0
        servicio.decrementarCupo(10);

        assertFalse(servicio.estaDisponible(), "El servicio no debería estar disponible si los cupos son 0");
    }

    @Test
    void testAumentarCupoCambiaLaDisponibilidad() {
        // Forzamos a 0 cupos
        servicio.decrementarCupo(10);
        assertFalse(servicio.estaDisponible());

        // Aumentamos el cupo y verificamos que vuelva a estar disponible
        servicio.aumentarCupo(5);
        assertTrue(servicio.estaDisponible(), "Al aumentar los cupos, el servicio debe volver a estar disponible");
    }
}
