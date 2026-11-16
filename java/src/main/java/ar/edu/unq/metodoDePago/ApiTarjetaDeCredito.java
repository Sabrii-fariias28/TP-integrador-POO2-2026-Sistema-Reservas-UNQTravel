package ar.edu.unq.metodoDePago;

import java.time.YearMonth;

public interface ApiTarjetaDeCredito {
    public boolean verificarDatos(int numTarjeta,String cvv, YearMonth mesYañoVencimiento);
}
