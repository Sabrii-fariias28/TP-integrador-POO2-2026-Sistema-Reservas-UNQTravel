package ar.edu.unq.serviciosypaquetes;

import java.util.List;

public class PaqueteTuristico extends ItemCatalogo {
    List<ItemCatalogo> contenido;
    Double descuentoPaquete;

    public PaqueteTuristico(String id, String nombre, List<Disponibilidad> cupos, String descripcion, String proveedor, List<ItemCatalogo> contenido, Double descuentoPaquete) {
        super(id, nombre, cupos, descripcion, proveedor);
        this.contenido = contenido;
        this.descuentoPaquete = descuentoPaquete;
    }

    @Override
    public double getPrecioBase(){
        double precioBase = 0;
        for (ItemCatalogo i : contenido){
            precioBase += i.getPrecioBase();
        }
        return precioBase - (precioBase * (this.descuentoPaquete / 100));
    }

    @Override
    public double precioFinal(double descuento) {
        return this.getPrecioBase() - (this.getPrecioBase() * (descuento / 100));
    }
}
