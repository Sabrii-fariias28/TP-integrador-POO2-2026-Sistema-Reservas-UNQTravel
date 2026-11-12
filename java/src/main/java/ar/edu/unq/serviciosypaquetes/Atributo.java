package ar.edu.unq.serviciosypaquetes;

public abstract class Atributo {
    String nombre;
    boolean indicadorObligatoriedad;

    public Atributo(String nombre, boolean obligatorio){
        this.nombre = nombre;
        this.indicadorObligatoriedad = obligatorio;
    }

    public abstract boolean esValido();

    public String getNombre(){
        return this.nombre;
    }

    //public boolean getIndicadorObligatoriedad() { return this.indicadorObligatoriedad;}
}
