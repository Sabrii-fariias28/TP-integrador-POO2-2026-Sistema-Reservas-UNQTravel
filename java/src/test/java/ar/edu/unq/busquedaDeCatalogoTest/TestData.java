package ar.edu.unq.busquedaDeCatalogoTest;
import ar.edu.unq.serviciosypaquetes.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestData {
    // Disponibilidad para los próximos días (Noviembre / Diciembre 2026)
    static Disponibilidad dispManana = new Disponibilidad(LocalDate.of(2026, 11, 15), 10);
    static Disponibilidad dispProximaSemana = new Disponibilidad(LocalDate.of(2026, 11, 20), 5);
    static Disponibilidad dispDiciembre = new Disponibilidad(LocalDate.of(2026, 12, 1), 20);

    // Listas de disponibilidad reutilizables
    static List<Disponibilidad> cuposMontana = new ArrayList<>(List.of(dispManana, dispProximaSemana));
    static List<Disponibilidad> cuposPlaya = new ArrayList<>(List.of(dispProximaSemana, dispDiciembre));
    static List<Disponibilidad> cuposGeneral = new ArrayList<>(List.of(dispManana));


    // --- 1. CREACIÓN DE SERVICIOS INDIVIDUALES (con sus cupos reales) ---
    static ServicioIndividual hotelBariloche = new ServicioIndividual(
            "S01", "Hotel Llao Llao", cuposMontana,
            "Hotel 5 estrellas en la montaña", "BookingUNQ", "Alojamiento", 150000.0
    );

    static ServicioIndividual excursionCatedral = new ServicioIndividual(
            "S02", "Ascenso al Cerro Catedral", cuposMontana,
            "Excursión de ski y trekking", "AndesExpediciones", "Aventura", 40000.0
    );

    static ServicioIndividual vueloBuenosAires = new ServicioIndividual(
            "S03", "Vuelo Ida y Vuelta a BRC", cuposGeneral,
            "Vuelo directo cabotaje", "FlyUNQ", "Transporte", 80000.0
    );

    static ServicioIndividual excursionBuceo = new ServicioIndividual(
            "S04", "Buceo en Mar del Plata", cuposPlaya,
            "Bautismo submarino con equipo", "CostaMar", "Playa", 35000.0
    );

    static ServicioIndividual hostelMdP = new ServicioIndividual(
            "S05", "Hostel La Costa", cuposPlaya,
            "Alojamiento mochilero frente al mar", "HostelWorld", "Alojamiento", 25000.0
    );

    static ServicioIndividual transferAeropuerto = new ServicioIndividual(
            "S06", "Transfer Privado BRC", cuposGeneral,
            "Traslado aeropuerto - hotel", "TransfersUNQ", "Transporte", 15000.0
    );


    // --- 2. CREACIÓN DE PAQUETES TURÍSTICOS ---

    static List<ItemCatalogo> itemsPatagonia = List.of(hotelBariloche, excursionCatedral, transferAeropuerto);
    static PaqueteTuristico paquetePatagonia = new PaqueteTuristico(
            "P01", "Experiencia Bariloche Total", cuposMontana,
            "Paquete completo de montaña con aventura y hospedaje", "AgenciaUNQ",
            itemsPatagonia, 10.0
    );

    static List<ItemCatalogo> itemsCosta = List.of(excursionBuceo, hostelMdP);
    static PaqueteTuristico paqueteCosta = new PaqueteTuristico(
            "P02", "Fin de Semana en la Costa", cuposPlaya,
            "Escapada de playa y buceo", "AgenciaUNQ",
            itemsCosta, 5.0
    );
    public static List<ItemCatalogo> crearCatalogoDePrueba() {
        // Retornamos todo en una lista unificada
        return new ArrayList<>(List.of(
                hotelBariloche, excursionCatedral, vueloBuenosAires,
                excursionBuceo, hostelMdP, transferAeropuerto,
                paquetePatagonia, paqueteCosta
        ));
    }
}