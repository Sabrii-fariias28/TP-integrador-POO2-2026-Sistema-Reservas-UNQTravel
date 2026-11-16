package ar.edu.unq.reportes;

import ar.edu.unq.Catalogo;

import java.time.LocalDate;

public class ReporteOcupacion extends Reporte{
    DetalleOcupacion detalles;
    private LocalDate periodoInicio;
    private LocalDate periodoFin;

    public ReporteOcupacion(Catalogo catalogo, DetalleOcupacion detalles, LocalDate pInicio, LocalDate pFin){
        super(catalogo);
        this.periodoInicio = pInicio;
        this.periodoFin = pFin;
        this.detalles = detalles;
    }

    @Override
    public void aceptarVisita(VisitorFormato v){
        v.visitar(this);
    }

}
