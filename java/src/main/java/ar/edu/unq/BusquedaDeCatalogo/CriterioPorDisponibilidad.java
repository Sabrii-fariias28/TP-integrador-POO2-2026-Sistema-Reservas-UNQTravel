package ar.edu.unq.BusquedaDeCatalogo;
import ar.edu.unq.serviciosypaquetes.ItemCatalogo;
import java.time.LocalDate;

public class CriterioPorDisponibilidad implements Criterio{
    private LocalDate fecha;
    //private Date fechaFin;
    private int cantPersonas;

    public CriterioPorDisponibilidad(LocalDate fecha, int cantPersonas){
        this.fecha = fecha;
        //this.fechaFin = fechaFin;
        this.cantPersonas = cantPersonas;
    }

    @Override
    public boolean cumpleCriterio(ItemCatalogo item){
        return item.estaDisponible(fecha, cantPersonas);
    }
}
