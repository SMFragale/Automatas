import java.util.*;

/**
 * @author smolanof
 * Representa un autómata finito no determinístico
 * Para construir el autómata se necesita agregar los estados y transiciones correspondientes utilizando los métodos de
 * la clase. Está modelada para ser fácil saber cuales son estos estados y transiciones de un AFD gráfico.
 */
public class AFND {

    Set<Estado> estados;
    Map<Transicion, List<Estado>> delta;

    Estado estadoInicial;

    /**
     * Crea el AFD con un estado (el estado inicial)
     * @param estadoInicial donde inicia el autómata (no es necesario ingresar el estado después de crearlo)
     */
    public AFND(Estado estadoInicial) {
        this.estadoInicial = estadoInicial;
        estados = new HashSet<>();
        agregarEstado(estadoInicial);
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
     * Agrega una transición a la máquina
     * @param q estado inicial de la transicion
     * @param simbolo símbolo que se agrega
     * @param estados lista de estados que se agregan en esta transición
     */
    public void agregarTransicion(Estado q, char simbolo, List<Estado> estados) {
        delta.put(new Transicion(q, simbolo), estados);
    }

    /**
     * Procesa el string pasado como parámetro y retorna un boolean que indica si el string fue aceptado por la máquina
     * o no.
     * @param input string a procesar
     * @return boolean indicando si el string es aceptado o no
     */
    public boolean procesarString(String input) {

        return procesarRama(input, estadoInicial);
    }

    /**
     * Método privado que procesa una rama específica de un string.
     * @param input string a procesar
     * @param estadoActual estado donde inicia a procesar el strign
     * @return true, si alguna de las ramas del string fue aceptada, false de lo contrario
     */
    private boolean procesarRama(String input, Estado estadoActual) {
        Estado estado = estadoActual;

        for(int i = 0; i <= input.length()-1; i++) {

            List<Estado> estadosPosibles = delta.get(new Transicion(estado, input.charAt(i)));

            //Si no tienes estados posibles está atascado
            if(estadosPosibles == null)
                return false;

            else {
                for (Estado e : estadosPosibles) {
                    estado = e;
                    //Lee desde el siguiente caracter, si alguna de las ramas llega a un estado final después de leer toda la cadena, la cadena se lee correctamente
                    if (procesarRama(input.substring(i + 1), estado))
                        return true;
                }
            }
        }
        //Retorna el estado donde termina de leer el autómata
        return estado.esFinal();
    }
}
