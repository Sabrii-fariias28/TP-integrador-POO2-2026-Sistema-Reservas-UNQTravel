package ar.edu.unq.serviciosypaquetes;

public class Atributo {
    String nombre;
    boolean indicadorObligatoriedad;
    Object tipoDeValor;

    public Atributo(String nombre, boolean obligatorio, Object valor){
        this.nombre = nombre;
        this.indicadorObligatoriedad = obligatorio;
        this.tipoDeValor = valor;
    }
}
