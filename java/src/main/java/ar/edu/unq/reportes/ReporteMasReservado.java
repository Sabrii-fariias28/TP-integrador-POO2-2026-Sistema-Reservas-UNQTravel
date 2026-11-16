package ar.edu.unq.reportes;

import ar.edu.unq.Catalogo;

import java.time.LocalDate;
import java.util.List;

public class ReporteMasReservado extends Reporte {
    private List<DetalleServicioVendido> detalles;
    private LocalDate periodoInicio;
    private LocalDate periodoFin;

    public ReporteMasReservado(Catalogo catalogo, List<DetalleServicioVendido> detalles, LocalDate pInicio, LocalDate pFin) {
        super(catalogo);
        this.periodoInicio = pInicio;
        this.periodoFin = pFin;
        this.detalles = detalles;
    }

    @Override
    public void aceptarVisita(VisitorFormato v) {
        v.visitar(this);
    }

    public List<DetalleServicioVendido> detalles(){
        return this.detalles;
    }
}
