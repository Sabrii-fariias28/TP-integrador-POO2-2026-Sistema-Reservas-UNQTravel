package ar.edu.unq.serviciosypaquetes;

import java.time.LocalDate;

public class AtributoFecha extends Atributo{
    private LocalDate valor;

    public AtributoFecha(String nombre, boolean obligatorio, LocalDate valor){
        super(nombre, obligatorio);
        this.valor = valor;
    }

    @Override
    public boolean esValido(){
        return !this.indicadorObligatoriedad || this.valor != null;
    }

}
