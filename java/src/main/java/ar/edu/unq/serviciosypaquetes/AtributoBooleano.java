package ar.edu.unq.serviciosypaquetes;

public class AtributoBooleano extends Atributo{
    private Boolean valor;

    public AtributoBooleano(String nombre, boolean obligatorio, Boolean valor){
        super(nombre, obligatorio);
        this.valor = valor;
    }

    @Override
    public boolean esValido(){
        return !this.indicadorObligatoriedad || this.valor != null;
    }
}
