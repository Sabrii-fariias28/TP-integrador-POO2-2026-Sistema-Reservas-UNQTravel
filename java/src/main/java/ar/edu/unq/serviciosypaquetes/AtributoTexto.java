package ar.edu.unq.serviciosypaquetes;

public class AtributoTexto extends Atributo{
    private String valor;

    public AtributoTexto(String nombre, boolean obligatorio, String valor){
        super(nombre, obligatorio);
        this.valor = valor;
    }
    @Override
    public boolean esValido(){
        return !this.indicadorObligatoriedad || !(this.valor == null || valor.isBlank());
    }
}
