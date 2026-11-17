package ar.edu.unq.trasladosytasas;

public class Direccion {
    private final String calle;
    private final int numero;
    private final String ciudad;

    public Direccion(String calle, int numero, String ciudad) {
        this.calle = calle;
        this.numero = numero;
        this.ciudad = ciudad;
    }

    public String getCalle() {
        return calle;
    }

    public int getNumero() {
        return numero;
    }

    public String getCiudad() {
        return ciudad;
    }
}