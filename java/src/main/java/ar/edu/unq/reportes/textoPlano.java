package ar.edu.unq.reportes;

public class textoPlano implements VisitorFormato{
    private String salida = "";

    @Override
    public void visitar(ReporteExposicion r) {
        StringBuilder sb = new StringBuilder("Reporte de Exposición por Proveedor\n");
        for (DetalleExposicion d : r.getDetalles()) {
            sb.append("- ").append(d.proveedor())
                    .append(": Items=").append(d.cantidadItems())
                    .append(", Exposición=").append(String.format("%.2f", d.porcentajeExposicion())).append("%\n");
        }
        this.salida = sb.toString();
    }

    @Override
    public void visitar(ReporteOcupacion r) {
        StringBuilder sb = new StringBuilder("Reporte de Ocupación (" + r.getPeriodoInicio() + " a " + r.getPeriodoFin() + ")\n");
        for (DetalleOcupacion d : r.getDetalles()) {
            sb.append("- ").append(d.categoria())
                    .append(": Totales=").append(d.cuposTotales())
                    .append(", Reservados=").append(d.cuposReservados())
                    .append(", Libres=").append(d.cuposLibres()).append("\n");
        }
        this.salida = sb.toString();
    }

    @Override
    public void visitar(ReporteMasReservado r) {
        StringBuilder sb = new StringBuilder("Reporte de Servicios Más Reservados (" + r.getPeriodoInicio() + " a " + r.getPeriodoFin() + ")\n");
        for (DetalleServicioVendido d : r.detalles()) {
            sb.append("- ").append(d.nombreItem())
                    .append(": Vendidos=").append(d.cantidadVendida())
                    .append(", Precio Promedio=$").append(String.format("%.2f", d.precioPromedioEfectivo())).append("\n");
        }
        this.salida = sb.toString();
    }

    public String getSalida() {
        return salida;
    }
}
