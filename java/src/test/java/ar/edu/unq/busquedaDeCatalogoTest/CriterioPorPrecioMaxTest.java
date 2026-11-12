package ar.edu.unq.busquedaDeCatalogoTest;
import ar.edu.unq.BusquedaDeCatalogo.Buscador;
import ar.edu.unq.BusquedaDeCatalogo.CriterioPorPrecioMax;
import ar.edu.unq.Catalogo;
import ar.edu.unq.serviciosypaquetes.ItemCatalogo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CriterioPorPrecioMaxTest {
    private Buscador buscador;

    @BeforeEach
    public void setUp(){
        ArrayList<ItemCatalogo> itemsDePrueba = (ArrayList<ItemCatalogo>) TestData.crearCatalogoDePrueba();
        Catalogo catalogo = new Catalogo(itemsDePrueba);
        buscador = new Buscador(catalogo);
    }

    @Test
    public void testFiltrarPorPrecioMaximoBajoDevuelveSoloItemsEconomicos() {
        // En nuestro TestData, el Transfer ($15.000) y el Hostel ($25.000) deberían entrar.
        CriterioPorPrecioMax criterio = new CriterioPorPrecioMax(30000.0);
        ArrayList<ItemCatalogo> resultados = buscador.buscar(criterio);
        assertEquals(2,resultados.size());
    }

    @Test
    public void testFiltrarPorPrecioMaxDe1PesoDevuelveListaVacia() {
        // Ponemos un precio imposible de que existe ($1.0)
        CriterioPorPrecioMax criterio = new CriterioPorPrecioMax(1.0);
        ArrayList<ItemCatalogo> resultados = buscador.buscar(criterio);
        assertTrue(resultados.isEmpty(), "No debería encontrar ningún servicio por debajo de ese precio");
    }
}

