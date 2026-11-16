package ar.edu.unq.metodoDePago;

public class TransferenciaBancaria extends MetodoDePago{
   private String cbu;
   private String alias;
   private ApiTransferenciaBancaria api;

    public TransferenciaBancaria(String cbu, String alias, ApiTransferenciaBancaria api){
      this.cbu = cbu;
      this.alias = alias;
      this.api = api;
    }

    @Override
    public void validarDatos() {
      this.validarCbu();
      this.validarAlias();
      this.validarTransferenciaPorApi();
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

    public void validarCbu(){

    }

    public void validarAlias(){

    }

    public void validarTransferenciaPorApi(){
      if (!esTransferenciaValida()){
         throw new RuntimeException("Error: La API de la transferencia bancaria rechazó la operación.");
     }
    }

    public boolean esTransferenciaValida(){
       return this.api.validarTransferencia(this.cbu,this.alias);
    }
}
