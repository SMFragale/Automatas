import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Clase para pruebas
 */
public class Main {

    public static void main(String[] args) {

        Estado uno = new Estado(false, "1");
        Estado dos = new Estado(true, "2");
        Estado tres = new Estado(false, "3");

        AFND maquina = new AFND(uno);
        maquina.agregarEstado(dos);
        maquina.agregarEstado(tres);

        maquina.agregarTransicion(uno, '1', new ArrayList<>(Arrays.asList(dos, tres)));
        maquina.agregarTransicion(dos, '1', new ArrayList<>(Collections.singletonList(uno)));
        maquina.agregarTransicion(tres, '0', new ArrayList<>(Collections.singletonList(dos)));

        System.out.println(maquina.procesarString("11"));

    }

}
