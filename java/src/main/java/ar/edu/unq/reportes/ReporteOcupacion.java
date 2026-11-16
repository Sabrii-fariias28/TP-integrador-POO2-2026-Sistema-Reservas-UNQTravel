package ar.edu.unq.reportes;

import ar.edu.unq.Catalogo;

import java.time.LocalDate;

public class ReporteOcupacion extends Reporte{
    DatosOcupacion detalles;
    private LocalDate periodoInicio;
    private LocalDate periodoFin;

    public ReporteOcupacion(Catalogo catalogo, DatosOcupacion detalles, LocalDate pInicio, LocalDate pFin){
        super(catalogo);
        this.periodoInicio = pInicio;
        this.periodoFin = pFin;
        this.detalles = detalles;
    }

    @Override
    public void aceptarVisita(VisitorFormato v){
        super.aceptarVisita(v);
    }

    public List<detalles> datosAReportar(){
        return this.detalles;
    }
}
