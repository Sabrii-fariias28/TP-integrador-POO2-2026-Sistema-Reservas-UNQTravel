package ar.edu.unq.metodoDePago;

public class BilleteraVirtual extends MetodoDePago{
    private double saldo;
    private ApiBilleteraVirtual api;

    public BilleteraVirtual(double saldo, ApiBilleteraVirtual api){
        this.saldo = saldo;
        this.api = api;
    }

    @Override
    public void validarDatos() {
        this.validarSaldoEnApi();
    }

    @Override
    public void reservarFondos() {
        this.api.bloquearSaldoHastaConfirmar();
    }

    @Override
    public void ejecutarTransaccion(double cantidad) {
        this.api.acreditacionEnTiempoRealAlVendedor(cantidad);
    }

    @Override
    public void realizarReembolso(double cantidad) {
        this.api.reembolsarMonto(cantidad);
    }

    public void validarSaldoEnApi(){
        if (!api.verificarSaldoSuficiente()) {
            throw new RuntimeException("Error: No hay suficiente saldo en la cuenta.");
        }
    }
}
