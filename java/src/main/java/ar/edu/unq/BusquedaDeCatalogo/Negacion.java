package ar.edu.unq.BusquedaDeCatalogo;

import ar.edu.unq.ItemCatalogo;

public class Negacion implements Criterio{
    private Criterio criterio;

    public Negacion(Criterio c){
        this.criterio = c;
    }

    @Override
    public boolean cumpleCriterio(ItemCatalogo item) {
        return !(criterio.cumpleCriterio(item));
    }
}
