package ar.edu.unq.busquedaDeCatalogoTest;
import ar.edu.unq.BusquedaDeCatalogo.Buscador;
import ar.edu.unq.BusquedaDeCatalogo.CriterioPorDisponibilidad;
import ar.edu.unq.Catalogo;
import ar.edu.unq.serviciosypaquetes.ItemCatalogo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CriterioPorDisponibilidadTest {
    private CriterioPorDisponibilidad critDispo;
    private Buscador buscador;

    @BeforeEach
    public void setUp(){
        ArrayList<ItemCatalogo> itemsDePrueba = (ArrayList<ItemCatalogo>) TestData.crearCatalogoDePrueba();
        Catalogo catalogo = new Catalogo(itemsDePrueba);
        buscador = new Buscador(catalogo);
    }

    @Test
    public void criterioPorDisponibilidadEncuentraResultadosTest() {
        LocalDate fechaBuscada = LocalDate.of(2026, 11, 15);
        int personasBuscadas = 4;

        CriterioPorDisponibilidad criterio = new CriterioPorDisponibilidad(fechaBuscada, personasBuscadas);
        ArrayList<ItemCatalogo> resultados = buscador.buscar(criterio);
        assertFalse(resultados.isEmpty());

        // Verificamos que el Hotel Llao Llao esté entre los resultados
        boolean encontroHotel = resultados.stream()
                .anyMatch(item -> item.getNombre().equals("Hotel Llao Llao"));
        assertTrue(encontroHotel, "El Hotel Llao Llao debería estar disponible para esa fecha y cantidad de personas");
    }

    @Test
    public void criterioPorDisponibilidadNoEncuentraResultadosTest() {
        LocalDate fechaBuscada = LocalDate.of(2077, 11, 15); //NO EXISTEN CUPOS PARA EL 2077
        int personasBuscadas = 3;

        CriterioPorDisponibilidad criterio = new CriterioPorDisponibilidad(fechaBuscada, personasBuscadas);
        ArrayList<ItemCatalogo> resultados = buscador.buscar(criterio);
        assertTrue(resultados.isEmpty());
    }
}
