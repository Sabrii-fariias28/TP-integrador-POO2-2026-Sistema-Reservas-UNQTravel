package ar.edu.unq.busquedaDeCatalogoTest;
import ar.edu.unq.BusquedaDeCatalogo.Buscador;
import ar.edu.unq.BusquedaDeCatalogo.CriterioPorCategoria;
import ar.edu.unq.Catalogo;
import ar.edu.unq.serviciosypaquetes.ItemCatalogo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CriterioPorCategoriaTest {
    private CriterioPorCategoria critCategoria;
    private Buscador buscador;

    @BeforeEach
    public void setUp(){
        ArrayList<ItemCatalogo> itemsDePrueba = (ArrayList<ItemCatalogo>) TestData.crearCatalogoDePrueba();
        Catalogo catalogo = new Catalogo(itemsDePrueba);
        buscador = new Buscador(catalogo);
    }

    @Test
    public void criterioPorCategoriaEncuentraResultadosTest() {
        critCategoria = new CriterioPorCategoria("Transporte");
        assertEquals(3, buscador.buscar(critCategoria).size());
    }

    @Test
    public void criterioPorCategoriaNoEncuentraNadaTest() {
        critCategoria = new CriterioPorCategoria("Volar");
        assertEquals(0, buscador.buscar(critCategoria).size());
    }
}
