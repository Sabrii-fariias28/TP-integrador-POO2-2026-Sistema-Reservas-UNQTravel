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
       this.api.transferenciaDirecta();
    }

    @Override
    public void ejecutarTransaccion(double cantidad) {
       this.api.transferenciaInmediata(cantidad);
    }

    @Override
    public void realizarReembolso(double cantidad) {
       this.api.reembolsarMonto(cantidad);
    }

    public void validarCbu(){
      if(!esCbuValido()){
        throw new RuntimeException("Error: No es un CBU valido.");
      }
    }

    public void validarAlias(){
      if(this.alias == null || this.alias.isEmpty()){
         throw new RuntimeException("Error: El alias es invalido.");
      }
    }

    public void validarTransferenciaPorApi(){
      if (!esTransferenciaValida()){
         throw new RuntimeException("Error: La API de la transferencia bancaria rechazó la operación.");
     }
    }

    public boolean esTransferenciaValida(){
       return this.api.validarTransferencia(this.cbu,this.alias);
    }

    public boolean esCbuValido(){
       return this.cbu != null && cbu.matches("\\d{22}"); // un cbu valido es de 22 de solo numeros.
    }
}
