package ar.edu.unq.metodoDePago;

public interface ApiTransferenciaBancaria {
    public boolean validarTransferencia(String cbu, String alias);
    public void transferenciaDirecta();
    public void transferenciaInmediata(double cantidad);
    public void reembolsarMonto(double cantidad);
}
