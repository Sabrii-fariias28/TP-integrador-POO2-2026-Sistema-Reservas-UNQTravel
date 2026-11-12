package ar.edu.unq.BusquedaDeCatalogo;
import ar.edu.unq.Catalogo;
import ar.edu.unq.serviciosypaquetes.ItemCatalogo;
import java.util.ArrayList;

public class Buscador {
    private Catalogo catalogo;

    public Buscador(Catalogo c){
        this.catalogo = c;
    }

    public ArrayList<ItemCatalogo> buscar(Criterio filtro){
        ArrayList<ItemCatalogo> itemsFiltrados = catalogo.getItems();
        itemsFiltrados.stream().filter(item -> filtro.cumpleCriterio(item));
        return itemsFiltrados;
    }
}
