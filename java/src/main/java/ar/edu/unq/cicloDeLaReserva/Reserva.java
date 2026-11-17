package ar.edu.unq.cicloDeLaReserva;
import ar.edu.unq.serviciosypaquetes.*;
import ar.edu.unq.metodoDePago.MetodoDePago;
import ar.edu.unq.notificadores.NotificadorManager;
import ar.edu.unq.trasladosytasas.MetodoTraslado;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reserva{
    private List<ItemCatalogo> servicios;
    private EstadoReserva estado;
    private int cantPersonas;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private MetodoDePago metodoDePago;
    private NotificadorManager notificadorManager;
    private MetodoTraslado traslado;

    public Reserva(int cantPersonas,  LocalDate fechaInicio, LocalDate fechaFin, MetodoDePago metodoDePago, NotificadorManager notificador, MetodoTraslado traslado) {
        this.servicios = new ArrayList<>();
        this.estado = new Pendiente();
        this.cantPersonas = cantPersonas;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.metodoDePago = metodoDePago;
        this.notificadorManager = notificador;
        this.traslado = traslado;
    }

    public void agregarItem(ItemCatalogo item){
        if(item.estaDisponibleEnRango(fechaInicio,fechaFin,cantPersonas)){
            this.servicios.add(item);
        } else{
            throw new SinDisponibilidad("El servicio no se puede añadir, no tiene disponibilidad.");
        }
    }

    public void quitarItem(ItemCatalogo item){
        this.servicios.remove(item);
    }

    public void confirmar(){
        estado.confirmarReserva(this);
    }

    public void cancelar(){
        estado.cancelarReserva(this);
    }

    public void cambiarEstado(EstadoReserva nuevoEstado){
        EstadoReserva estadoAnterior = this.getEstado();
        this.estado = nuevoEstado;
        notificadorManager.actualizarEstado(estadoAnterior, nuevoEstado);
    }

    public void incrementarCuposAItems(){
        servicios.forEach(item -> item.cancelar(fechaInicio, fechaFin, cantPersonas));
    }

    public void decrementarCuposAItems(){
        servicios.forEach(item -> item.reservarRango(fechaInicio, fechaFin, cantPersonas));
    }

    public void reembolsarTotalCostos(){
        metodoDePago.realizarReembolso(calcularPrecioTotal() + tasasAdministracion());
    }

    public void reembolsarServicios(){
        metodoDePago.realizarReembolso(calcularPrecioServicios());
    }

    public void realizarPago(){
       metodoDePago.realizarPago(calcularPrecioTotal());
    }

    public double calcularPrecioTotal(){
        return calcularPrecioServicios() + calcularCostoTraslado();
    }

    public double calcularPrecioServicios() {
        return servicios.stream()
                .mapToDouble(ItemCatalogo::precioFinal) // Ajustar según tu firma de precioFinal
                .sum();
    }

    public double calcularCostoTraslado(){
        if (this.traslado != null) {
            return this.traslado.calcularCosto(this);
        } else{
            return 0.0;
        }
    }

    public boolean estaFinalizada() {
        return estado.estaFinalizada();
    }

    //REVISAR #PREGUNTAR #LOdijoDemi
    private double tasasAdministracion(){
        return 5000;
    }

    public List<ItemCatalogo> getServicios() {
        return servicios;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public int getCantPersonas() {
        return cantPersonas;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setMetodoDePago(MetodoDePago metodoPagoNuevo) {
        this.metodoDePago = metodoPagoNuevo;
    }

    public void setTraslado(MetodoTraslado traslado){
        this.traslado = traslado;
    }

}