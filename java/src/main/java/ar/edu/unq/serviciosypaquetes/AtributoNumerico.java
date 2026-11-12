package ar.edu.unq.serviciosypaquetes;

public class AtributoNumerico extends Atributo{
        private Double valor;

        public AtributoNumerico(String nombre, boolean obligatorio, Double valor) {
            super(nombre, obligatorio);
            this.valor = valor;
        }

        @Override
        public boolean esValido(){
            return !this.indicadorObligatoriedad || this.valor != null;
        }
}
