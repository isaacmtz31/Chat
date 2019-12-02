package chatmulticasthilos.compartido;


/**
 *
 * @author (at)fferegrino
 */
public class Interaccion {

    public static final int NUEVO_CLIENTE = 1;
    public static final int SALUDO_CLIENTE = 2;
    public static final int MENSAJE_GRUPAL = 3;
    public static final int MENSAJE_PRIVADO = 4;
    public static final int DESPEDIDA_USUARIO = 5;
    public static final int FILE = 7;
    public static final int PING = 5;
    
    public static final int MAX_BUFFER_SIZE = 1500;
}
