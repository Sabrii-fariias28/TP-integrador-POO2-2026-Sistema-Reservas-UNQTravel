package ar.edu.unq.serviciosypaquetes;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ServicioIndividual extends ItemCatalogo{
    String categoria;
    List<Atributo> listaDeAtributos;
    Double precioBase;

    public ServicioIndividual(String id, String nombre, List<Disponibilidad> cupos, String descripcion, String proveedor, String categoria, Double precioBase) {
        super(id, nombre, cupos, descripcion, proveedor);
        this.categoria = categoria;
        this.listaDeAtributos = new ArrayList<>();
        this.precioBase = precioBase;
    }

    @Override
    public double getPrecioBase(){
        return this.precioBase;
    }

    @Override
    public double precioFinal(double descuento) {
        return this.getPrecioBase() - (this.getPrecioBase() * (descuento / 100));
    }

    public void agregarAtributo(Atributo a){
        listaDeAtributos.add(a);
    }

    public boolean esValido() {
        boolean fijosValidos = (this.getId() != null && !this.getId().isBlank()) &&
                               (this.getNombre() != null && !this.getNombre().isBlank());

        boolean dinamicosValidos = this.listaDeAtributos.stream()
                                    .allMatch(Atributo::esValido);

        return fijosValidos && dinamicosValidos;
    }

    @Override
    public boolean contieneNombre(String nombre){
        return this.getNombre().toLowerCase().equals(nombre);
    }

    @Override
    public boolean contieneCategoria(String categoria){
        return this.categoria.toLowerCase().equals(categoria);
    }
}
