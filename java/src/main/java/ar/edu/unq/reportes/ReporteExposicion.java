package ar.edu.unq.reportes;

import ar.edu.unq.Catalogo;

public class ReporteExposicion extends Reporte{
    List<DetalleExposicion> detalles;

    public ReporteExposicion(Catalogo catalogo, List<DetalleExposicion> detalles){
        super(catalogo);
        this.detalles = detalles;
    }

    @Override
    public void aceptarVisita(VisitorFormato v){
        super.aceptarVisita(v);
    }

    public List<DetalleExposicion> getDetalles(){
        return this.detalles;
    }
}
