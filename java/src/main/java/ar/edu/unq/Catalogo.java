package ar.edu.unq;

import java.util.ArrayList;

public class Catalogo {
    private ArrayList<ItemCatalogo> items;

    public Catalogo(ArrayList<ItemCatalogo> i){
        this.items = i;
    }

    public void agregarItem(ItemCatalogo item){
        items.add(item);
    }

    public void quitarItem(ItemCatalogo item){
        items.remove(item);
    }

    public ArrayList<ItemCatalogo> getItems(){
        return items;
    }
}
