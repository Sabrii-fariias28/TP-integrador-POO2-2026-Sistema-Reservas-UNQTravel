package ar.edu.unq.trasladosytasas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class DireccionTest {

    @Test
    void testCreacionYGettersDeDireccion() {
        Direccion direccion = new Direccion("Av. Calchaquí", 5800, "Florencio Varela");

        assertEquals("Av. Calchaquí", direccion.getCalle());
        assertEquals(5800, direccion.getNumero());
        assertEquals("Florencio Varela", direccion.getCiudad());
    }
}