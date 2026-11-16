package ar.edu.unq;
import ar.edu.unq.serviciosypaquetes.ItemCatalogo;
import java.util.ArrayList;

public class Catalogo {
    private ArrayList<ItemCatalogo> items;

    public Catalogo(ArrayList<ItemCatalogo> i){
        this.items = i;
    }

    public void agregarItem(ItemCatalogo item){
        this.items.add(item);
    }

    public void quitarItem(ItemCatalogo item){
        this.items.remove(item);
    }

    public ArrayList<ItemCatalogo> getItems(){
        return this.items;
    }
}
