package ar.edu.unq.metodoDePago;

public interface ApiBilleteraVirtual {
    public boolean verificarSaldoSuficiente();
    public void bloquearSaldoHastaConfirmar();
    public void acreditacionEnTiempoRealAlVendedor(double cantidad);
}
