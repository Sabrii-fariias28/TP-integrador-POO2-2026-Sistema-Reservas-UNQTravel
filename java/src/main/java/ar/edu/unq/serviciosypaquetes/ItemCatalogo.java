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

    abstract public double precioFinal(double descuento);

    public List<Disponibilidad> getCupos(){
        return this.cupos;
    }

    public boolean hayCupos(int cant){
        return this.cupos.stream().anyMatch(cupo -> cupo.hayLugar(cant));
    }
    public boolean estaDisponible(LocalDate fecha, int personas){
        return buscarPorFecha(fecha)
                .map(disp -> disp.hayLugar(personas))
                .orElse(false); // Si la fecha no existe en la lista, no está disponible
    }

    public Optional<Disponibilidad> buscarPorFecha(LocalDate fecha) {
        return this.cupos.stream()
                .filter(d -> d.getFecha().equals(fecha))
                .findFirst();
    }

    public void reservarRango(LocalDate fechaInicio, LocalDate fechaFin, int personas) {
        List<Disponibilidad> diasAApartar = new ArrayList<>();

        // Recorrer el rango de fechas día por día
        for (LocalDate fecha = fechaInicio; !fecha.isAfter(fechaFin); fecha = fecha.plusDays(1)) {
            LocalDate fechaActual = fecha;
            Disponibilidad disp = buscarPorFecha(fechaActual)
                    .orElseThrow(() -> new IllegalArgumentException("No se puede reservar en la fecha: " + fechaActual));
            // Verificar si hay cupo para ese día específico
            if (!disp.hayLugar(personas)) {
                throw new IllegalArgumentException("Cupo insuficiente para el día: " + fechaActual);
            }

            diasAApartar.add(disp);
        }

        //  Si todos los días son válidos, confirmamos la reserva para el grupo de dias
        for (Disponibilidad disp : diasAApartar) {
            disp.agendarReserva(personas);
        }
    }

    public void cancelar(LocalDate fechaInicio, LocalDate fechaFin, int personas){
        List<Disponibilidad> diasACancelar = new ArrayList<>();


        // Recorrer el rango de fechas día por día
        for (LocalDate fecha = fechaInicio; !fecha.isAfter(fechaFin); fecha = fecha.plusDays(1)) {
            LocalDate fechaActual = fecha;
            Disponibilidad disp = buscarPorFecha(fechaActual)
                    .orElseThrow(() -> new IllegalArgumentException("No se puede cancelar si no hay reserva en la fecha: " + fechaActual));
            /* Verificar si puede cancelar ese dia*/
            if (!disp.puedeCancelar(personas)) {
                throw new IllegalArgumentException("No hay cupos para el día: " + fechaActual);
            }

            diasACancelar.add(disp);
        }

        //  Si todos los días existen como cupos, procedemos a cancelarlos
        for (Disponibilidad disp : diasACancelar) {
            disp.cancelarReservas(personas);
        }
    }

    public void agregarFecha(LocalDate fecha, int capacidad) {
        cupos.add(new Disponibilidad(fecha, capacidad));
    }
}
