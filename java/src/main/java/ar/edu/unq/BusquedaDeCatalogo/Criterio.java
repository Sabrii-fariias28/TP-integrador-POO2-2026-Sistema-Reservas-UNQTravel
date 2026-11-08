package ar.edu.unq.BusquedaDeCatalogo;
import ar.edu.unq.ItemCatalogo;

public interface Criterio {

    public boolean cumpleCriterio(ItemCatalogo item);
}
