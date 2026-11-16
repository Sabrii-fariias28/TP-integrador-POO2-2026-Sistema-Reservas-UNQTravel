package ar.edu.unq.metodoDePago;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class TarjetaDeCreditoInternacional extends MetodoDePago{
    private String numTarjeta;
    private String cvv;
    private YearMonth fechaVen; //Solo tiene  mes y año y no hace falta especificar un dia...
    private ApiTarjetaDeCredito apiTarjeta;

    public TarjetaDeCreditoInternacional(ApiTarjetaDeCredito apiTarjeta, String numTarjeta, String cvv, YearMonth fechaVen) {
        this.apiTarjeta = apiTarjeta;
        this.numTarjeta = numTarjeta;
        this.cvv = cvv;
        this.fechaVen = fechaVen;
    }

    @Override
    public void validarDatos() {
        this.validarNumeroTarjeta();
        this.validarCVV();
        this.validarVencimiento();
        this.apruebaLaApiLaTarjeta();
    }

    @Override
    public void reservarFondos() {
        this.apiTarjeta.preAutorizarBancoEmisor();
    }

    @Override
    public void ejecutarTransaccion(double cantidad) {
        this.apiTarjeta.transferenciaInmediata(cantidad);
    }


    @Override
    public void realizarReembolso(double monto) {
        this.apiTarjeta.reembolsarMonto(monto);
    }

    private void validarVencimiento(){
        if (this.fechaVen == null) {
            throw new RuntimeException("Error: La fecha de vencimiento esta vacia.");
        }
    }

    private void validarCVV(){
        if (!esValidoCvv()) {
            throw new RuntimeException("Error: El código CVV es inválido.");
        }
    }

    private void validarNumeroTarjeta(){
        if (esNumValidoDeTarjeta()) {
            throw new RuntimeException("Error: El número de tarjeta es inválido.");
        }
    }

    private boolean esValidoCvv(){
        return this.cvv != null && cvv.matches("\\d{3}"); //Verifica que solo sea de 3 digitos y solo sean numeros, no letras.
    }

    private boolean esUnaTarjetaValida(){
        return this.apiTarjeta.verificarDatos(this.numTarjeta, this.cvv, this.fechaVen);
    }

    private boolean esNumValidoDeTarjeta(){
        return this.numTarjeta == null || this.numTarjeta.isEmpty();
    }

    private void apruebaLaApiLaTarjeta(){
        if (!esUnaTarjetaValida()) {
            throw new RuntimeException("Error: La API de la tajeta de credito internacional rechazó la tarjeta.");
        }
    }
}
