package ar.edu.unq.busquedaDeCatalogoTest;
import ar.edu.unq.serviciosypaquetes.*;
import java.util.ArrayList;
import java.util.List;

public class TestData {

    public static List<ItemCatalogo> crearCatalogoDePrueba() {
        // Servicios
        ServicioIndividual hotelBariloche = new ServicioIndividual("S01", "Hotel Llao Llao", new ArrayList<>(), "Hotel 5 estrellas", "BookingUNQ", "Alojamiento", 150000.0);
        ServicioIndividual excursionCatedral = new ServicioIndividual("S02", "Ascenso al Cerro Catedral", new ArrayList<>(), "Ski y trekking", "AndesExpediciones", "Aventura", 40000.0);
        ServicioIndividual vueloBuenosAires = new ServicioIndividual("S03", "Vuelo BRC", new ArrayList<>(), "Vuelo directo", "FlyUNQ", "Transporte", 80000.0);
        ServicioIndividual excursionBuceo = new ServicioIndividual("S04", "Buceo MdP", new ArrayList<>(), "Bautismo submarino", "CostaMar", "Playa", 35000.0);
        ServicioIndividual hostelMdP = new ServicioIndividual("S05", "Hostel La Costa", new ArrayList<>(), "Mochilero", "HostelWorld", "Alojamiento", 25000.0);
        ServicioIndividual transferAeropuerto = new ServicioIndividual("S06", "Transfer BRC", new ArrayList<>(), "Traslado", "TransfersUNQ", "Transporte", 15000.0);

        // Paquete 1
        List<ItemCatalogo> itemsPatagonia = List.of(hotelBariloche, excursionCatedral, transferAeropuerto);
        PaqueteTuristico paquetePatagonia = new PaqueteTuristico("P01", "Experiencia Bariloche Total", new ArrayList<>(), "Completo montaña", "AgenciaUNQ", itemsPatagonia, 10.0);

        // Paquete 2
        List<ItemCatalogo> itemsCosta = List.of(excursionBuceo, hostelMdP);
        PaqueteTuristico paqueteCosta = new PaqueteTuristico("P02", "Fin de Semana en la Costa", new ArrayList<>(), "Playa y buceo", "AgenciaUNQ", itemsCosta, 5.0);

        // Retornamos todo en una lista unificada
        return new ArrayList<>(List.of(
                hotelBariloche, excursionCatedral, vueloBuenosAires,
                excursionBuceo, hostelMdP, transferAeropuerto,
                paquetePatagonia, paqueteCosta
        ));
    }
}