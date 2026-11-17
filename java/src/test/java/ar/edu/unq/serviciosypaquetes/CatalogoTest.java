package ar.edu.unq.serviciosypaquetes;
import ar.edu.unq.Catalogo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CatalogoTest {

    private Catalogo catalogo;
    private ArrayList<ItemCatalogo> listaInicial;
    private ServicioIndividual item1;
    private ServicioIndividual item2;

    @BeforeEach
    void setUp() {
        listaInicial = new ArrayList<>();
        catalogo = new Catalogo(listaInicial);

        // Creamos ítems reales de prueba utilizando tu constructor de ServicioIndividual
        item1 = new ServicioIndividual(
                "01", "Hotel Llao Llao", new ArrayList<>(), "Hotel de lujo", "ProvA", "Hotel", 100.0, 0.0
        );
        item2 = new ServicioIndividual(
                "02", "Excursion A", new ArrayList<>(), "Tour guiado", "ProvB", "Tour", 50.0, 0.0
        );
    }

    @Test
    void testConstructorYGetItems() {
        assertNotNull(catalogo.getItems());
        assertTrue(catalogo.getItems().isEmpty());
        assertEquals(listaInicial, catalogo.getItems());
    }

    @Test
    void testAgregarItem() {
        catalogo.agregarItem(item1);

        assertEquals(1, catalogo.getItems().size());
        assertTrue(catalogo.getItems().contains(item1));
    }

    @Test
    void testQuitarItem() {
        catalogo.agregarItem(item1);
        catalogo.agregarItem(item2);
        assertEquals(2, catalogo.getItems().size());

        catalogo.quitarItem(item1);

        assertEquals(1, catalogo.getItems().size());
        assertFalse(catalogo.getItems().contains(item1));
        assertTrue(catalogo.getItems().contains(item2));
    }
}