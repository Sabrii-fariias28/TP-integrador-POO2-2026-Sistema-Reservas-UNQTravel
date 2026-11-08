package ar.edu.unq.busquedaDeCatalogoTest;

import ar.edu.unq.BusquedaDeCatalogo.Buscador;
import ar.edu.unq.BusquedaDeCatalogo.Criterio;
import ar.edu.unq.BusquedaDeCatalogo.CriterioPorPrecioMax;
import ar.edu.unq.Catalogo;
import ar.edu.unq.ItemCatalogo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BuscadorTest {

    private Buscador buscador;
    private Catalogo catalogo;
    private CriterioPorPrecioMax criterioPrecioMax;
    private ItemCatalogo hotel;
    private ItemCatalogo excursion;

    @BeforeEach
    public void setUp() throws Exception {
        catalogo = new Catalogo(new ArrayList<>());
        buscador = new Buscador(catalogo);
        criterioPrecioMax = new CriterioPorPrecioMax(6500.0);
        hotel = new ServicioIndividual("9","Hotel Central",5,"Hospedacion en el hotel central",
                "Ricota", "Hotel",new ArrayList<Atributo>(),5000);
        excursion = new ServicioIndividual("5","Excursion a la selva",7,"Veni a conocer la Selva",
                "Hoyoverse", "Excursion",new ArrayList<Atributo>(),7500);
        catalogo.agregarItem(hotel);
        catalogo.agregarItem(excursion);
    }

    @Test
    public void buscarEnCatalogoTest() {
        assertTrue(buscador.buscar(criterioPrecioMax).contains(hotel));
    }
}
