package ar.edu.unq.busquedaDeCatalogoTest;
import ar.edu.unq.BusquedaDeCatalogo.Buscador;
import ar.edu.unq.BusquedaDeCatalogo.CriterioPorNombre;
import ar.edu.unq.Catalogo;
import ar.edu.unq.serviciosypaquetes.ItemCatalogo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CriterioPorNombreTest {
    private CriterioPorNombre critNombre;
    private Buscador buscador;

    @BeforeEach
    public void setUp(){
        ArrayList<ItemCatalogo> itemsDePrueba = (ArrayList<ItemCatalogo>) TestData.crearCatalogoDePrueba();
        Catalogo catalogo = new Catalogo(itemsDePrueba);
        buscador = new Buscador(catalogo);
    }

    @Test
    public void criterioPorNombreEncuentraResultadosTest() {
        critNombre = new CriterioPorNombre("Hotel Llao Llao");
        assertEquals(2, buscador.buscar(critNombre).size());
    }

    @Test
    public void criterioPorNombreNoEncuentraNadaTest() {
        critNombre = new CriterioPorNombre("Volar");
        assertEquals(0, buscador.buscar(critNombre).size());
    }
}