package ar.edu.unq.metodoDePago;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class TarjetaDeCreditoInternacional extends MetodoDePago{
    private int numTarjeta;
    private String cvv;
    private YearMonth fechaVen; //Solo tiene  mes y año y no hace falta especificar un dia...
    private ApiTarjetaDeCredito apiTarjeta;
    private List<VoucherDigital> vouchers;

    public TarjetaDeCreditoInternacional(ApiTarjetaDeCredito apiTarjeta) {
        this.apiTarjeta = apiTarjeta;
        this.vouchers = new ArrayList<>();
    }

    @Override
    public void validarDatos() {
        this.validarNumeroTarjeta();
        this.validarCVV();
        this.validarVencimiento();

        if (!esUnaTarjetaValida()) {
            throw new RuntimeException("Error: La API de la tajeta de credito internacional rechazó la tarjeta.");
        }
    }

    @Override
    public void reservarFondos() {

    }

    @Override
    public void ejecutarTransaccion(double cantidad) {

    }

    @Override
    public void notificarResultado() {

    }

    @Override
    public void realizarReembolso(double cantidad) {

    }

    private void validarVencimiento(){
        if (this.fechaVen == null) {
            throw new RuntimeException("Error: La fecha de vencimiento esta vacia.");
        }
    }

    private void validarCVV(){
        if (esValidoCvv()) {
            throw new RuntimeException("Error: El código CVV es inválido.");
        }
    }

    private void validarNumeroTarjeta(){
        if (this.numTarjeta <= 0) {
            throw new RuntimeException("Error: El número de tarjeta es inválido.");
        }
    }

    private boolean esValidoCvv(){
        return this.cvv != null && cvv.matches("\\d{3}"); //Verifica que solo sea de 3 digitos y solo sean numeros, no letras.
    }

    private boolean esUnaTarjetaValida(){
        return this.apiTarjeta.verificarDatos(this.numTarjeta, this.cvv, this.fechaVen);
    }
}
