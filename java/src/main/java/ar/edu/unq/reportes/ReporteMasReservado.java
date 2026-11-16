package ar.edu.unq.reportes;

import ar.edu.unq.Catalogo;

import java.time.LocalDate;

public class ReporteMasReservado extends Reporte {
    private List<DetalleServicioMasVendido> detalles;
    private LocalDate periodoInicio;
    private LocalDate periodoFin;

    public ReporteMasReservado(Catalogo catalogo, List<DetalleServicioMasVendido> detalles, LocalDate pInicio, LocalDate pFin) {
        super(catalogo);
        this.periodoInicio = pInicio;
        this.periodoFin = pFin;
        this.detalles = detalles;
    }

    @Override
    public void aceptarVisita(VisitorFormato v) {
        super.aceptarVisita(v);
    }

    public List<DetalleServicioMasVendido> detallesNose(){
        return this.detalles;
    }
}
