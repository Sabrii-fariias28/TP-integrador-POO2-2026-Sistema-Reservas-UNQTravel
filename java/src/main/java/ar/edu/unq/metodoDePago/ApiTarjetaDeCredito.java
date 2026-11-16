package ar.edu.unq.metodoDePago;

import java.time.YearMonth;

public interface ApiTarjetaDeCredito {
    public boolean verificarDatos(String numTarjeta,String cvv, YearMonth mesYañoVencimiento);
    public boolean preAutorizarBancoEmisor();
    public void transferenciaInmediata(double monto);
    public void reembolsarMonto(double cantidad);
}
