package ar.edu.unq.busquedaDeCatalogoTest;
import ar.edu.unq.BusquedaDeCatalogo.*;
import ar.edu.unq.Catalogo;
import ar.edu.unq.serviciosypaquetes.ItemCatalogo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class ConjuncionTest {
    private CriterioPorCategoria critCategoria;
    private CriterioPorPrecioMax critPrecioMax;
    private Disyuncion disyuncion;
    private Conjuncion conjuncion;
    private Buscador buscador;
    private CriterioPorNombre critNom;

    @BeforeEach
    public void setUp(){
        ArrayList<ItemCatalogo> itemsDePrueba = (ArrayList<ItemCatalogo>) TestData.crearCatalogoDePrueba();
        Catalogo catalogo = new Catalogo(itemsDePrueba);
        buscador = new Buscador(catalogo);
    }

    @Test
    public void criterioPorPrecioMax75kYPorCategoriaEncuentraResultadosTest() {
        critCategoria = new CriterioPorCategoria("Aventura");
        critPrecioMax = new CriterioPorPrecioMax(50000.0);
        critNom = new CriterioPorNombre("Vuelo Ida y Vuelta a BRC");
        disyuncion = new Disyuncion(critCategoria, critNom);
        assertEquals(3, buscador.buscar(disyuncion).size());
        conjuncion = new Conjuncion(disyuncion, critPrecioMax);
        assertEquals(1, buscador.buscar(conjuncion).size());
    }

    @Test
    public void criterioPorPrecioMax5kYPorCategoriaNoEncuentraResultadosTest() {
        critCategoria = new CriterioPorCategoria("Aventura");
        critPrecioMax = new CriterioPorPrecioMax(35000.0);
        conjuncion = new Conjuncion(critCategoria, critPrecioMax);
        assertTrue(buscador.buscar(conjuncion).isEmpty());
    }
}
