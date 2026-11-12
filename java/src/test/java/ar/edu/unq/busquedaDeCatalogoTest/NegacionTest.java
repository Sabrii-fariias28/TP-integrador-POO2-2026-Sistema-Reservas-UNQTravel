package ar.edu.unq.busquedaDeCatalogoTest;
import ar.edu.unq.BusquedaDeCatalogo.*;
import ar.edu.unq.Catalogo;
import ar.edu.unq.serviciosypaquetes.ItemCatalogo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class NegacionTest {
    private CriterioPorCategoria critCategoria;
    private CriterioPorPrecioMax critPrecioMax;
    private Disyuncion disyuncion;
    private Buscador buscador;
    private Negacion negacion;

    @BeforeEach
    public void setUp(){
        ArrayList<ItemCatalogo> itemsDePrueba = (ArrayList<ItemCatalogo>) TestData.crearCatalogoDePrueba();
        Catalogo catalogo = new Catalogo(itemsDePrueba);
        buscador = new Buscador(catalogo);
    }

    @Test
    public void notCriterioPorPrecioMax75kYPorCategoriaEncuentraResultadosTest() {
        critCategoria = new CriterioPorCategoria("Transporte");
        critPrecioMax = new CriterioPorPrecioMax(5000.0);
        disyuncion = new Disyuncion(critCategoria, critPrecioMax);
        negacion = new Negacion(disyuncion);
        assertEquals(5, buscador.buscar(negacion).size());
    }

    @Test
    public void criterioPorPrecioMax5kYPorCategoriaNoEncuentraResultadosTest() {
        critPrecioMax = new CriterioPorPrecioMax(100000.0);
        negacion = new Negacion(critPrecioMax);
        assertEquals(2,buscador.buscar(negacion).size());
    }
}
