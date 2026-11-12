package ar.edu.unq.busquedaDeCatalogoTest;

import ar.edu.unq.BusquedaDeCatalogo.*;
import ar.edu.unq.Catalogo;
import ar.edu.unq.serviciosypaquetes.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BuscadorTest {

    private Buscador buscador;
    private Catalogo catalogo;
    private CriterioPorPrecioMax criterioPrecioMax;
    private ItemCatalogo hotel;
    private ItemCatalogo excursion;
    private List<Disponibilidad> cupos;

    @BeforeEach
    public void setUp() throws Exception {
        catalogo = new Catalogo(new ArrayList<>());
        buscador = new Buscador(catalogo);
        cupos = new ArrayList<>();
        criterioPrecioMax = new CriterioPorPrecioMax(6500.0);
        hotel = new ServicioIndividual("9","Hotel Central",cupos,"Hospedacion en el hotel central",
                "Ricota", "Hotel",5000.0);
        excursion = new ServicioIndividual("5","Excursion a la selva",cupos,"Veni a conocer la Selva",
                "Hoyoverse", "Excursion",7500.0);
        catalogo.agregarItem(hotel);
        catalogo.agregarItem(excursion);
    }

    @Test
    public void buscarEnCatalogoTest() {
        assertTrue(buscador.buscar(criterioPrecioMax).contains(hotel));
    }
}
