package ar.edu.unq.metodoDePago;

public interface ApiTransferenciaBancaria {
    public boolean validarTransferencia(String cbu, String alias);
}
