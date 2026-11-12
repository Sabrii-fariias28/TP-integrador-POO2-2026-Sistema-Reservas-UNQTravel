package ar.edu.unq.serviciosypaquetes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ServicioIndividualTest {

    private ServicioIndividual servicio;

    @BeforeEach
    void setUp() {
        servicio = new ServicioIndividual("1", "Vuelo a Bariloche", new ArrayList<>(), "Vuelo Nacional", "Aerolineas Argentinas", "Vuelo", 4000.0);
    }

    @Test
    void testPreciosConYSinDescuento() {
        assertEquals(4000.0, servicio.getPrecioBase());
        // Descuento del 10%
        assertEquals(3600.0, servicio.precioFinal(10.0));
    }

    @Test
    void testEsValidoConAtributosCorrectos() {
        servicio.agregarAtributo(new AtributoTexto("Clase", true, "Turista"));
        servicio.agregarAtributo(new AtributoBooleano("Mascotas", true, false));

        assertTrue(servicio.esValido());
    }

    @Test
    void testEsInvalidoSiAtributoFijoEstaVacioOAtributoDinamicoFalla() {
        // Falla por dinámico
        servicio.agregarAtributo(new AtributoTexto("Clase", true, null));
        assertFalse(servicio.esValido());

        // Falla por fijo (nombre vacío)
        ServicioIndividual servicioMalo = new ServicioIndividual("V1", "", new ArrayList<>(), "Desc", "Aero", "Vuelo", 100.0);
        assertFalse(servicioMalo.esValido());
    }

    @Test
    void testEsValidoFallaPorIdNuloOBlanco() {
        ServicioIndividual servicioIdNulo = new ServicioIndividual(null, "Nombre", new ArrayList<>(), "Desc", "Prov", "Cat", 10.0);
        ServicioIndividual servicioIdBlanco = new ServicioIndividual("   ", "Nombre", new ArrayList<>(), "Desc", "Prov", "Cat", 10.0);

        assertFalse(servicioIdNulo.esValido());
        assertFalse(servicioIdBlanco.esValido());
    }

    @Test
    void testEsValidoFallaPorNombreNuloOBlanco() {
        ServicioIndividual servicioNombreNulo = new ServicioIndividual("ID1", null, new ArrayList<>(), "Desc", "Prov", "Cat", 10.0);
        ServicioIndividual servicioNombreBlanco = new ServicioIndividual("ID1", "   ", new ArrayList<>(), "Desc", "Prov", "Cat", 10.0);

        assertFalse(servicioNombreNulo.esValido());
        assertFalse(servicioNombreBlanco.esValido());
    }
}
