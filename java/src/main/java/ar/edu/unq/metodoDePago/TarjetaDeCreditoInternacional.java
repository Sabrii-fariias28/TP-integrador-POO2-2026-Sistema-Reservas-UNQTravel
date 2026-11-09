package ar.edu.unq.metodoDePago;

import java.time.LocalDate;
import java.time.YearMonth;

public class TarjetaDeCreditoInternacional extends MetodoDePago{
    private int numTarjeta;
    private String cvv;
    private YearMonth fechaVen; //Solo tiene  mes y año y no hace falta especificar un dia...

    @Override
    public void validarDatos() {

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
}
