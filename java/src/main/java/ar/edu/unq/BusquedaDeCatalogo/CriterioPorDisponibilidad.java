package ar.edu.unq.BusquedaDeCatalogo;
import ar.edu.unq.ItemCatalogo;
import java.util.Date;

public class CriterioPorDisponibilidad implements Criterio{
    private Date fecha;
    //private Date fechaFin;
    private int cantPersonas;

    public CriterioPorDisponibilidad(Date fecha, int cantPersonas){
        this.fecha = fecha;
        //this.fechaFin = fechaFin;
        this.cantPersonas = cantPersonas;
    }

    @Override
    public boolean cumpleCriterio(ItemCatalogo item){
        return item.estaDisponible(fecha, cantPersonas);
    }
}
