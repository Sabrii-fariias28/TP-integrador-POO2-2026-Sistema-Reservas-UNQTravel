package ar.edu.unq.serviciosypaquetes;

import java.util.List;

public class PaqueteTuristico extends ItemCatalogo {
    List<ItemCatalogo> contenido;

    public PaqueteTuristico(String id, String nombre, int cupos, String descripcion, String proveedor, List<ItemCatalogo> contenido) {
        super(id, nombre, cupos, descripcion, proveedor);
        this.contenido = contenido;
    }

    @Override
    public double precioFinal(double descuento) {
        return 0;
    }
}
