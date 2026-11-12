package ar.edu.unq.BusquedaDeCatalogo;
import ar.edu.unq.serviciosypaquetes.ItemCatalogo;

public class CriterioPorNombre implements Criterio{
    private String nombreABuscar;

    public CriterioPorNombre(String nombre){
        this.nombreABuscar = nombre.toLowerCase();
    }

    @Override
    public boolean cumpleCriterio(ItemCatalogo item) {
        return item.contieneNombre(nombreABuscar);
    }
}