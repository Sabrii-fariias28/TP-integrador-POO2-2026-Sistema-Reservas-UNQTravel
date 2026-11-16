package ar.edu.unq.reportes;
import ar.edu.unq.Catalogo;
import ar.edu.unq.serviciosypaquetes.ItemCatalogo;
import java.util.ArrayList;
import java.util.List;

public class ReporteExposicion extends Reporte{
    List<DetalleExposicion> detalles;

    public ReporteExposicion(Catalogo catalogo, List<DetalleExposicion> detalles){
        super(catalogo);
        this.detalles = detalles;
    }

    @Override
    public void aceptarVisita(VisitorFormato v){
        v.visitar(this);
    }

    public List<DetalleExposicion> getDetalles(){
        List<ItemCatalogo> items = this.catalogo.getItems();
        int totalItems = items.size();
        List<String> proveedores = items.stream()
                .map(ItemCatalogo::getProveedor)
                .distinct()
                .toList();

        List<DetalleExposicion> detalles = new ArrayList<>();
        for (String p : proveedores){
            int cantidad = (int) items.stream()
                    .filter(item -> item.getProveedor().equals(p))
                    .count();
            double porcentaje = (cantidad * 100.0) / totalItems;
            detalles.add(new DetalleExposicion(p, cantidad, porcentaje));
        }
        return detalles;
    }
}
