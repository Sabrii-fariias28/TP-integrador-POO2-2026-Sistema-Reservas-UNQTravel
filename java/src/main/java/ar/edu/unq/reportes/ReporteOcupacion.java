package ar.edu.unq.reportes;

import ar.edu.unq.Catalogo;
import ar.edu.unq.serviciosypaquetes.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReporteOcupacion extends Reporte {
    private LocalDate periodoInicio;
    private LocalDate periodoFin;

    public ReporteOcupacion(Catalogo catalogo, DetalleOcupacion detalles, LocalDate pInicio, LocalDate pFin) {
        super(catalogo);
        this.periodoInicio = pInicio;
        this.periodoFin = pFin;
    }

    @Override
    public void aceptarVisita(VisitorFormato v) {
        v.visitar(this);
    }

    public List<DetalleOcupacion> getDetalles() {
        List<ServicioIndividual> servicios = this.catalogo.getItems().stream()
                .flatMap(item -> item.obtenerServiciosIndividuales().stream())
                .distinct()
                .toList();

        List<String> categorias = servicios.stream()
                .map(ServicioIndividual::getCategoria)
                .distinct()
                .toList();

        List<DetalleOcupacion> detalles = new ArrayList<>();

        for (String cat : categorias) {
            int totales = 0;
            int reservados = 0;
            int libres = 0;

            for (ServicioIndividual servicio : servicios) {
                if (servicio.contieneCategoria(cat)) {
                    for (Disponibilidad disp : servicio.getCupos()) {
                        if (estaEnPeriodo(disp.getFecha())) {
                            totales += disp.getCapacidadMaxima();
                            reservados += disp.getReservasConfirmadas();
                            libres += disp.cuposDisponibles();
                        }
                    }
                }
            }

            detalles.add(new DetalleOcupacion(cat, totales, reservados, libres));
        }

        return detalles;
    }

    private boolean estaEnPeriodo(LocalDate fecha) {
        return !fecha.isBefore(periodoInicio) && !fecha.isAfter(periodoFin);
    }

    public LocalDate getPeriodoInicio() {
        return periodoInicio;
    }

    public LocalDate getPeriodoFin() {
        return periodoFin;
    }

}
