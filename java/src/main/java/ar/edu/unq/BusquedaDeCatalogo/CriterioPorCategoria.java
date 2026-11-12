package ar.edu.unq.BusquedaDeCatalogo;
import ar.edu.unq.serviciosypaquetes.ItemCatalogo;

public class CriterioPorCategoria implements Criterio{
    private Categoria categoriaABuscar;

    public CriterioPorCategoria(Categoria categoriaABuscar){
        this.categoriaABuscar = categoriaABuscar;
    }

    @Override
    public boolean cumpleCriterio(ItemCatalogo item) {
        return item.contieneCategoria(categoriaABuscar);
    }
}
