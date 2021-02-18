import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author smolanof
 * Representa un autómata finito determinístico
 * Para construir el autómata se necesita agregar los estados y transiciones correspondientes utilizando los métodos de
 * la clase. Está modelada para ser fácil saber cuales son estos estados y transiciones de un AFD gráfico.
 */
public class AFD {

    private Set<Estado> estados;
    private HashMap<Transicion, Estado>  delta;

    private final Estado estadoInicial;

    /**
     * Crea el AFD con un estado (el estado inicial)
     * @param estadoInicial donde inicia el autómata (no es necesario ingresar el estado después de crearlo)
     */
    public AFD(Estado estadoInicial) {
        this.estadoInicial = estadoInicial;
        agregarEstado(estadoInicial);
        estados = new HashSet<>();
        delta = new HashMap<>();
    }

    /**
     * Agreaga un estado nuevo a la máquina
     * @param e estado para agregar
     */
    public void agregarEstado(Estado e) {
        estados.add(e);
    }

    /**
     * Agrega una transición sencilla a la máquina

     */
    public void agregarTransicion(Estado q, char simbolo, Estado estado) {
        delta.put(new Transicion(q, simbolo), estado);
    }

    /**
     * Agrega un rango de transiciones a la máquina ej. 'a'-'z' es decir todos los caracteres de la 'a' a la 'z' aplican
     * para esta transición.
     * @param estadoI Estado inicial (relativo)
     * @param limInferior Limite inferior desde donde empieza el rango (cualquier caracter ej. 'a') relativo al formato
     * @param limSuperior Límite superior donde termina el rango (cualquier caracter ej. '1')
     * @param estadoF Estado final de la transicion
     */
    public void agregarRangoTransicion(Estado estadoI, char limInferior, char limSuperior, Estado estadoF) {
        if(limInferior > limSuperior)
            return;

        for(char i = limInferior; i <= limSuperior; i++) {
            delta.put(new Transicion(estadoI, i), estadoF);
        }
    }

    /**
     * Procesa el string pasado como parámetro y retorna un boolean que indica si el string fue aceptado por la máquina
     * o no.
     * @param input string a procesar
     * @return boolean indicando si el string es aceptado o no
     */
    public boolean procesarString(String input) {
        Estado estadoActual = estadoInicial;

        for(int i = 0; i < input.length(); i++) {
            Estado temp = estadoActual;
            estadoActual = delta.get(new Transicion(temp, input.charAt(i)));
            if(estadoActual == null)
                return false;
        }
        return estadoActual.esFinal();
    }

}


