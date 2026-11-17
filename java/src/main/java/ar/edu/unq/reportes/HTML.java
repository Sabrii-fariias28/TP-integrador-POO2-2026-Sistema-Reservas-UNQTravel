package ar.edu.unq.reportes;

public class HTML implements VisitorFormato{
    private String salida = "";

    @Override
    public void visitar(ReporteExposicion r) {
        StringBuilder sb = new StringBuilder("<table><tr><th>Proveedor</th><th>Cantidad Items</th><th>Porcentaje Exposición</th></tr>");
        for (DetalleExposicion d : r.getDetalles()) {
            sb.append("<tr><td>").append(d.proveedor()).append("</td>")
                    .append("<td>").append(d.cantidadItems()).append("</td>")
                    .append("<td>").append(d.porcentajeExposicion()).append("%</td></tr>");
        }
        sb.append("</table>");
        this.salida = sb.toString();
    }

    @Override
    public void visitar(ReporteOcupacion r) {
        StringBuilder sb = new StringBuilder("<table><tr><th>Categoría</th><th>Totales</th><th>Reservados</th><th>Libres</th></tr>");
        for (DetalleOcupacion d : r.getDetalles()) {
            sb.append("<tr><td>").append(d.categoria()).append("</td>")
                    .append("<td>").append(d.cuposTotales()).append("</td>")
                    .append("<td>").append(d.cuposReservados()).append("</td>")
                    .append("<td>").append(d.cuposLibres()).append("</td></tr>");
        }
        sb.append("</table>");
        this.salida = sb.toString();
    }

    @Override
    public void visitar(ReporteMasReservado r) {
        StringBuilder sb = new StringBuilder("<table><tr><th>Ítem</th><th>Cantidad Vendida</th><th>Precio Promedio Efectivo</th></tr>");
        for (DetalleServicioVendido d : r.detalles()) {
            sb.append("<tr><td>").append(d.getNombreItem()).append("</td>")
                    .append("<td>").append(d.getCantidadVendida()).append("</td>")
                    .append("<td>").append(d.getPrecioPromedioEfectivo()).append("</td></tr>");
        }
        sb.append("</table>");
        this.salida = sb.toString();
    }

    public String getSalida() {
        return salida;
    }
}
