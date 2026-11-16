package ar.edu.unq.reportes;

public class DetalleServicioVendido {
    private final String nombreItem;
    private final int cantidadVendida;
    private final double precioPromedioEfectivo;

    public DetalleServicioVendido(String nombreItem, int cantidadVendida, double precioPromedioEfectivo) {
        this.nombreItem = nombreItem;
        this.cantidadVendida = cantidadVendida;
        this.precioPromedioEfectivo = precioPromedioEfectivo;
    }

    public String getNombreItem() { return nombreItem; }
    public int getCantidadVendida() { return cantidadVendida; }
    public double getPrecioPromedioEfectivo() { return precioPromedioEfectivo; }
}
