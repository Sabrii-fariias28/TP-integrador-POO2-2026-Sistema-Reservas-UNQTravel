package ar.edu.unq;

import java.time.LocalDate;

public class ItemCatalogo {
    public boolean estaDisponibleEnRango(LocalDate fechaInicio,LocalDate fechaFin, int cantPersonas){
        return true;
    };

    public double precioFinal(){
        return 1.2;
    };

    public void reservarRango(LocalDate fechaInicio, LocalDate fechaFin, int cantPersonas) {}
    public void cancelar(LocalDate fechaInicio,LocalDate fechaFin,int cantPersonas){ };
}