package ar.edu.unq.metodoDePago;

public abstract class MetodoDePago {

    public void realizarPago(double cantidad){
        //Aca vamos a tener el flujo de pago completo con los 4 pasos.
        //Si algun paso no se puede hacer, se deberia de cortar el flujo
        this.validarDatos();
        this.reservarFondos();
        this.ejecutarTransaccion(cantidad);
        this.notificarResultado();
    }

    public abstract void validarDatos();
    public abstract void reservarFondos();
    public abstract void ejecutarTransaccion(double cantidad);

    //El enunciado dice que este paso, podemos no hacerlo. Es opcional...
    public void notificarResultado(){
        System.out.println("Registro por defecto del codigo de transacción.");
    }

    public abstract void realizarReembolso(double cantidad);

}
