package ar.edu.unq.BusquedaDeCatalogo;
import ar.edu.unq.serviciosypaquetes.ItemCatalogo;

public interface Criterio {

    public boolean cumpleCriterio(ItemCatalogo item);
}
