package ar.edu.unq.reportes;
import ar.edu.unq.Catalogo;

public abstract class Reporte {
    protected Catalogo catalogo;

    public Reporte(Catalogo catalogo) {
        this.catalogo = catalogo;
    }

    abstract public void aceptarVisita(VisitorFormato v);
}
