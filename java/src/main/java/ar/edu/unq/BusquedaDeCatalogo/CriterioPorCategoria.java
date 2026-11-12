package ar.edu.unq.BusquedaDeCatalogo;
import ar.edu.unq.serviciosypaquetes.ItemCatalogo;

public class CriterioPorCategoria implements Criterio{
    private String categoriaABuscar; //Va a ser string hasta que se implementen bien lsa categorias

    public CriterioPorCategoria(String categoriaABuscar){
        this.categoriaABuscar = categoriaABuscar.toLowerCase();
    }

    //falta implementar las categorias
    @Override
    public boolean cumpleCriterio(ItemCatalogo item) {
        return item.contieneCategoria(categoriaABuscar);
    }
}
