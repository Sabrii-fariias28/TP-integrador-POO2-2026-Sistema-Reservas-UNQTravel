package ar.edu.unq.reportes;

public interface VisitorFormato {

    void visitar(ReporteExposicion r);

    void visitar(ReporteOcupacion r);

    void visitar(ReporteMasReservado r);
}
