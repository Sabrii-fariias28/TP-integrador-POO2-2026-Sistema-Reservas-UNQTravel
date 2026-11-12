package ar.edu.unq.BusquedaDeCatalogo;
import ar.edu.unq.serviciosypaquetes.ItemCatalogo;

public interface Criterio {
    boolean cumpleCriterio(ItemCatalogo item);
}
