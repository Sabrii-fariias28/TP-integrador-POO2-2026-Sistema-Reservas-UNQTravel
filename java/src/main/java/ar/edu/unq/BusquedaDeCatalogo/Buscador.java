package ar.edu.unq.BusquedaDeCatalogo;
import ar.edu.unq.Catalogo;
import ar.edu.unq.serviciosypaquetes.ItemCatalogo;
import java.util.ArrayList;
import java.util.List;

public class Buscador {
    private Catalogo catalogo;

    public Buscador(Catalogo c){
        this.catalogo = c;
    }

    public ArrayList<ItemCatalogo> buscar(Criterio filtro){
        List<ItemCatalogo> filtrados = catalogo.getItems().stream()
                .filter(filtro::cumpleCriterio)
                .toList();
        return new ArrayList<>(filtrados);
    }
}
