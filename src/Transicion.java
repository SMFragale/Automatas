import java.util.Objects;

/**
 * Representa una transición simple de un estado. El estado objetivo es definido por la máquina
 */
class Transicion {
    Estado estadoActual;
    char caracter;

    public Transicion(Estado estado, char caracter) {
        this.estadoActual = estado;
        this.caracter = caracter;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transicion transicion = (Transicion) o;
        return caracter == transicion.caracter &&
                Objects.equals(estadoActual, transicion.estadoActual);
    }

    @Override
    public int hashCode() {
        return Objects.hash(estadoActual, caracter);
    }
}
