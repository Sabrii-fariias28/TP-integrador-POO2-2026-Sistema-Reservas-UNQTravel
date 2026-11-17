package ar.edu.unq.serviciosypaquetes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class ItemCatalogo {
    String id;
    String nombre;
    List<Disponibilidad> cupos;
    String descripcion;
    String proveedor;

    public ItemCatalogo (String id, String nombre, List<Disponibilidad> cupos, String descripcion, String proveedor){
        this.id = id;
        this.nombre = nombre;
        this.cupos = cupos;
        this.descripcion = descripcion;
        this.proveedor = proveedor;
    }

    public String getId(){
        return this.id;
    }

    public String getNombre(){
        return this.nombre;
    }

    public String getDescripcion(){
        return this.descripcion;
    }

    abstract public double getPrecioBase();

    abstract public double precioFinal();

    public List<Disponibilidad> getCupos(){
        return this.cupos;
    }

    public String getProveedor(){ return this.proveedor;}

    public boolean hayCupos(int cant){
        return this.cupos.stream().anyMatch(cupo -> cupo.hayLugar(cant));
    }
    public boolean estaDisponible(LocalDate fecha, int personas){
        return buscarPorFecha(fecha)
                .map(disp -> disp.hayLugar(personas))
                .orElse(false);
    }

    public Optional<Disponibilidad> buscarPorFecha(LocalDate fecha) {
        return this.cupos.stream()
                .filter(d -> d.getFecha().equals(fecha))
                .findFirst();
    }

    public void reservarRango(LocalDate fechaInicio, LocalDate fechaFin, int personas) {
        List<Disponibilidad> diasAApartar = new ArrayList<>();

        for (LocalDate fecha = fechaInicio; !fecha.isAfter(fechaFin); fecha = fecha.plusDays(1)) {
            LocalDate fechaActual = fecha;
            Disponibilidad disp = buscarPorFecha(fechaActual)
                    .orElseThrow(() -> new IllegalArgumentException("No se puede reservar en la fecha: " + fechaActual));
            if (!disp.hayLugar(personas)) {
                throw new IllegalArgumentException("Cupo insuficiente para el día: " + fechaActual);
            }

            diasAApartar.add(disp);
        }

        for (Disponibilidad disp : diasAApartar) {
            disp.agendarReservas(personas);
        }
    }

    public void cancelar(LocalDate fechaInicio, LocalDate fechaFin, int personas){
        List<Disponibilidad> diasACancelar = new ArrayList<>();

        for (LocalDate fecha = fechaInicio; !fecha.isAfter(fechaFin); fecha = fecha.plusDays(1)) {
            LocalDate fechaActual = fecha;
            Disponibilidad disp = buscarPorFecha(fechaActual)
                    .orElseThrow(() -> new IllegalArgumentException("No se puede cancelar si no hay reserva en la fecha: " + fechaActual));

            if (!disp.puedeCancelar(personas)) {
                throw new IllegalArgumentException("No hay cupos para el día: " + fechaActual);
            }

            diasACancelar.add(disp);
        }

        for (Disponibilidad disp : diasACancelar) {
            disp.cancelarReservas(personas);
        }
    }

    public void agregarFecha(LocalDate fecha, int capacidad) {
        cupos.add(new Disponibilidad(fecha, capacidad));
    }

    public boolean estaDisponibleEnRango(LocalDate fechaInicio, LocalDate fechaFin, int cantPersonas) {
        return fechaInicio.datesUntil(fechaFin.plusDays(1))
                .allMatch(fecha -> this.estaDisponible(fecha, cantPersonas));
    }

    abstract public boolean contieneNombre(String nombre);

    abstract public boolean contieneCategoria(String categoria);

    public abstract List<ServicioIndividual> obtenerServiciosIndividuales();
}
