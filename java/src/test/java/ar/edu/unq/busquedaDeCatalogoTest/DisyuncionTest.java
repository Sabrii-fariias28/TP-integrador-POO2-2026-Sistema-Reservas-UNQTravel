package ar.edu.unq.busquedaDeCatalogoTest;
import ar.edu.unq.BusquedaDeCatalogo.Buscador;
import ar.edu.unq.BusquedaDeCatalogo.CriterioPorCategoria;
import ar.edu.unq.BusquedaDeCatalogo.CriterioPorPrecioMax;
import ar.edu.unq.BusquedaDeCatalogo.Disyuncion;
import ar.edu.unq.Catalogo;
import ar.edu.unq.serviciosypaquetes.ItemCatalogo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DisyuncionTest {
    private CriterioPorCategoria critCategoria;
    private CriterioPorPrecioMax critPrecioMax;
    private Disyuncion disyuncion;
    private Buscador buscador;

    @BeforeEach
    public void setUp(){
        ArrayList<ItemCatalogo> itemsDePrueba = (ArrayList<ItemCatalogo>) TestData.crearCatalogoDePrueba();
        Catalogo catalogo = new Catalogo(itemsDePrueba);
        buscador = new Buscador(catalogo);
    }

    @Test
    public void criterioPorPrecioMax75kYPorCategoriaEncuentraResultadosTest() {
        critCategoria = new CriterioPorCategoria("Transporte");
        critPrecioMax = new CriterioPorPrecioMax(5000.0);
        disyuncion = new Disyuncion(critCategoria, critPrecioMax);
        assertEquals(3, buscador.buscar(disyuncion).size());
    }

    @Test
    public void criterioPorPrecioMax5kYPorCategoriaNoEncuentraResultadosTest() {
        critCategoria = new CriterioPorCategoria("Remo");
        critPrecioMax = new CriterioPorPrecioMax(5000.0);
        disyuncion = new Disyuncion(critCategoria, critPrecioMax);
        assertTrue(buscador.buscar(disyuncion).isEmpty());
    }
}
