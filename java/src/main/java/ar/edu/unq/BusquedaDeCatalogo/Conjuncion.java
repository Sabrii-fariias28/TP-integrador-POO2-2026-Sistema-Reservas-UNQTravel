package ar.edu.unq.BusquedaDeCatalogo;

import ar.edu.unq.ItemCatalogo;

public class Conjuncion implements Criterio{
    private Criterio criterio1;
    private Criterio criterio2;

    public Conjuncion(Criterio c1, Criterio c2){
        this.criterio1 = c1;
        this.criterio2 = c2;
    }

    @Override
    public boolean cumpleCriterio(ItemCatalogo item) {
        return criterio1.cumpleCriterio(item) && criterio2.cumpleCriterio(item);
    }
}
