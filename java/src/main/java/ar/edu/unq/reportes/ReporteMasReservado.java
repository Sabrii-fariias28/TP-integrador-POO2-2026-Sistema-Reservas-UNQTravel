package ar.edu.unq.reportes;

import ar.edu.unq.Catalogo;
import ar.edu.unq.cicloDeLaReserva.GestorReservas;
import ar.edu.unq.cicloDeLaReserva.Reserva;
import ar.edu.unq.serviciosypaquetes.ItemCatalogo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReporteMasReservado extends Reporte {
    private GestorReservas gestorReservas;
    private LocalDate periodoInicio;
    private LocalDate periodoFin;

    public LocalDate getPeriodoFin() {
        return periodoFin;
    }

    public LocalDate getPeriodoInicio() {
        return periodoInicio;
    }

    public ReporteMasReservado(Catalogo catalogo, GestorReservas gestorReservas, LocalDate pInicio, LocalDate pFin) {
        super(catalogo);
        this.periodoInicio = pInicio;
        this.periodoFin = pFin;
        this.gestorReservas = gestorReservas;
    }

    @Override
    public void aceptarVisita(VisitorFormato v) {
        v.visitar(this);
    }

    public List<DetalleServicioVendido> detalles(){
        List<Reserva> reservasEnPeriodo = gestorReservas.reservasFinalizadas().stream()
                .filter(r -> !r.getFechaInicio().isBefore(periodoInicio) && !r.getFechaInicio().isAfter(periodoFin))
                .toList();

        List<ItemCatalogo> itemsUnicos = reservasEnPeriodo.stream()
                .flatMap(r -> r.getServicios().stream())
                .distinct()
                .toList();

        List<DetalleServicioVendido> detalles = new ArrayList<>();

        for (ItemCatalogo item : itemsUnicos) {
            List<Reserva> reservasDelItem = reservasEnPeriodo.stream()
                    .filter(r -> r.getServicios().contains(item))
                    .toList();

            int cantidadReservas = reservasDelItem.size();

            double recaudacionTotal = reservasDelItem.stream()
                    .mapToDouble(Reserva::calcularPrecioTotal)
                    .sum();

            double precioPromedio = reservasDelItem.isEmpty() ? 0.0 : recaudacionTotal / reservasDelItem.size();

            detalles.add(new DetalleServicioVendido(item.getNombre(), cantidadReservas, precioPromedio));
        }
        detalles.sort((d1, d2) -> Integer.compare(d2.cantidadVendida(), d1.cantidadVendida()));
        return detalles;
    }
}
