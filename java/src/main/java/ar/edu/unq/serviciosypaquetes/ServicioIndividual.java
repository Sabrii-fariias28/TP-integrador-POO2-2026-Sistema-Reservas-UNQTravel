package ar.edu.unq.serviciosypaquetes;

import java.util.ArrayList;
import java.util.List;

public class ServicioIndividual extends ItemCatalogo{
    String categoria;
    List<Atributo> listaDeAtributos;

    public ServicioIndividual(String id, String nombre, int cupos, String descripcion, String proveedor, String categoria) {
        super(id, nombre, cupos, descripcion, proveedor);
        this.categoria = categoria;
        this.listaDeAtributos = new ArrayList<>();
    }

    @Override
    public double precioFinal(double descuento) {
        return 0;
    }

    public void agregarAtributo(Atributo a){
        listaDeAtributos.add(a);
    }
}
