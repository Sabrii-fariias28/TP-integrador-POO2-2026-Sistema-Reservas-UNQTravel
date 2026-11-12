package ar.edu.unq.serviciosypaquetes;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AtributoTest {

    @Test
    void testAtributoTextoValido() {
        AtributoTexto obligatorioConTexto = new AtributoTexto("Políticas", true, "No reembolsable");
        AtributoTexto opcionalVacio = new AtributoTexto("Notas", false, null);
        AtributoTexto opcionalConTexto = new AtributoTexto("Notas", false, "Llevar abrigo");

        assertTrue(obligatorioConTexto.esValido());
        assertTrue(opcionalVacio.esValido());
        assertTrue(opcionalConTexto.esValido());
    }

    @Test
    void testAtributoTextoInvalido() {
        AtributoTexto obligatorioNulo = new AtributoTexto("Políticas", true, null);
        AtributoTexto obligatorioVacio = new AtributoTexto("Políticas", true, "   ");

        assertFalse(obligatorioNulo.esValido());
        assertFalse(obligatorioVacio.esValido());
    }

    @Test
    void testAtributoBooleano() {
        AtributoBooleano obligatorioTrue = new AtributoBooleano("Mascotas", true, true);
        AtributoBooleano obligatorioFalse = new AtributoBooleano("Mascotas", true, false);
        AtributoBooleano obligatorioNulo = new AtributoBooleano("Mascotas", true, null);
        AtributoBooleano opcionalNulo = new AtributoBooleano("Mascotas", false, null);

        assertTrue(obligatorioTrue.esValido());
        assertTrue(obligatorioFalse.esValido()); // false es un valor válido, significa "No acepta mascotas"
        assertFalse(obligatorioNulo.esValido()); // null es inválido si es obligatorio
        assertTrue(opcionalNulo.esValido());
    }

    @Test
    void testAtributoNumerico() {
        AtributoNumerico obligatorioValido = new AtributoNumerico("Precio", true, 1500.0);
        AtributoNumerico obligatorioInvalido = new AtributoNumerico("Precio", true, null);
        AtributoNumerico opcional = new AtributoNumerico("Descuento", false, null);

        assertTrue(obligatorioValido.esValido());
        assertFalse(obligatorioInvalido.esValido());
        assertTrue(opcional.esValido());
    }

    @Test
    void testAtributoFecha() {
        AtributoFecha obligatorioValido = new AtributoFecha("Fecha Vuelo", true, LocalDate.now());
        AtributoFecha obligatorioInvalido = new AtributoFecha("Fecha Vuelo", true, null);
        AtributoFecha opcional = new AtributoFecha("Fecha Regreso", false, null);

        assertTrue(obligatorioValido.esValido());
        assertFalse(obligatorioInvalido.esValido());
        assertTrue(opcional.esValido());
    }

    @Test
    void testAtributoGetters() {
        AtributoTexto atributo = new AtributoTexto("Politicas", true, "Sin reembolso");
        assertEquals("Politicas", atributo.getNombre()); // O el getter que tengas definido
    }
}