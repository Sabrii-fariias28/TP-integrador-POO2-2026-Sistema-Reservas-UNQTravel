package ar.edu.unq.BusquedaDeCatalogo;
import ar.edu.unq.serviciosypaquetes.ItemCatalogo;

public class CriterioPorPrecioMax implements Criterio{
    private Double precioMax;

    public CriterioPorPrecioMax(Double precioMax){
        this.precioMax = precioMax;
    }

    @Override
    public boolean cumpleCriterio(ItemCatalogo item){
        return item.precioFinal(0) <= precioMax;
    }
}
