package ar.edu.unq.metodoDePago;

public abstract class metodoDePago {
    public void realizarPago(float cantidad){
        //Aca vamos a tener el flujo de pago completo con los 4 pasos. Acordarme de preguntar el tipo, era float o double¿
        //Si algun paso no se puede hacer, se deberia de cortar el flujo
        this.validarDatos();
        this.reservarFondos();
        this.ejecutarTransaccion(cantidad);
        this.notificarResultado();
    }
    public abstract void validarDatos();
    public abstract void reservarFondos();
    public abstract void ejecutarTransaccion(float cantidad);
    public abstract void notificarResultado();

    public abstract void reembolsar(float cantidad);

}
