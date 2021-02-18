/**
 * @author smolanof
 * Representa un estado de una máquina de estados
 * El estado solo necesita saber si es final o no.
 * El nombre del estado solo se utiliza para fines de depuración
 */
class Estado {
    boolean esFinal;
    String name;

    public Estado(boolean esFinal, String name) {
        this.esFinal = esFinal;
        this.name = name;
    }

    public boolean esFinal() {
        return esFinal;
    }

    @Override
    public String toString() {
        return "Estado{" + name + '}';
    }
}