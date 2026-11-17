package ar.edu.unq.reportes;

public class CSV implements VisitorFormato{
    private String salida = "";

    @Override
    public void visitar(ReporteExposicion r) {
        StringBuilder sb = new StringBuilder("Proveedor,CantidadItems,PorcentajeExposicion\n");
        for (DetalleExposicion d : r.getDetalles()) {
            sb.append(d.proveedor()).append(",")
                    .append(d.cantidadItems()).append(",")
                    .append(d.porcentajeExposicion()).append("\n");
        }
        this.salida = sb.toString();
    }

    @Override
    public void visitar(ReporteOcupacion r) {
        StringBuilder sb = new StringBuilder("Categoria,CuposTotales,CuposReservados,CuposLibres\n");
        for (DetalleOcupacion d : r.getDetalles()) {
            sb.append(d.categoria()).append(",")
                    .append(d.cuposTotales()).append(",")
                    .append(d.cuposReservados()).append(",")
                    .append(d.cuposLibres()).append("\n");
        }
        this.salida = sb.toString();
    }

    @Override
    public void visitar(ReporteMasReservado r) {
        StringBuilder sb = new StringBuilder("NombreItem,CantidadVendida,PrecioPromedioEfectivo\n");
        for (DetalleServicioVendido d : r.detalles()) {
            sb.append(d.nombreItem()).append(",")
                    .append(d.cantidadVendida()).append(",")
                    .append(d.precioPromedioEfectivo()).append("\n");
        }
        this.salida = sb.toString();
    }

    public String getSalida() {
        return salida;
    }

}
