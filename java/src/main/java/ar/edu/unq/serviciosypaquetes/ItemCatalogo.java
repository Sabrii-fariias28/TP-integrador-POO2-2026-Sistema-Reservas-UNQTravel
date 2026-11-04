package ar.edu.unq.serviciosypaquetes;

public abstract class ItemCatalogo {
    String id;
    String nombre;
    int cupos;
    String descripcion;
    String proveedor;

    public ItemCatalogo (String id, String nombre, int cupos, String descripcion, String proveedor){
        this.id = id;
        this.nombre = nombre;
        this.cupos = cupos;
        this.descripcion = descripcion;
        this.proveedor = proveedor;
    }

    public String getId(){
        return this.id;
    }

    public String getNombre(){
        return this.nombre;
    }

    public int getCupos(){
        return this.cupos;
    }

    public String getDescripcion(){
        return this.descripcion;
    }

    public boolean estaDisponible(){
        return this.cupos > 0;
    }

    abstract public double precioFinal(double descuento);


    public void decrementarCupo(int cantidad){
        this.cupos =- cantidad;
    }

    public void aumentarCupo(int cantidad){
        this.cupos =+ cantidad;
    }
}
