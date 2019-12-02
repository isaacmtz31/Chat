package chatmulticasthilos.server;

import chatmulticasthilos.compartido.Interaccion;
import chatmulticasthilos.compartido.*;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author (at)fferegrino
 */
public class Servidor {

    private static ArrayList<Usuario> usuarios;

    public static void main(String[] args) {
        usuarios = new ArrayList<Usuario>(); //new HashMap<String, InetAddress>();
        try {

            MulticastSocket socket = new MulticastSocket(Puertos.SERVIDOR_MULTICAST);           
            socket.joinGroup(InetAddress.getByName("230.0.0.1"));
            socket.setReuseAddress(true);
            socket.setTimeToLive(50);
            while (true) 
            {
                System.out.println("OK");
                DatagramPacket paquete = new DatagramPacket(new byte[1024], 1024);
                socket.receive(paquete);
                InetAddress cliente = paquete.getAddress();
                int puerto = paquete.getPort();
                byte[] data = paquete.getData();
                log("Conexion " + paquete.getSocketAddress());
                System.out.println("OK");
                switch (data[0]) {
                    case Interaccion.SALUDO_CLIENTE:
                        Usuario nuevo = aceptaUsuario(data, cliente, puerto);
                        enviaListaUsuarios(nuevo);
                        System.out.println("Envie lista a " + cliente);
                        usuarios.add(nuevo);
                        break;
                    case Interaccion.DESPEDIDA_USUARIO:
                        Usuario u = new Usuario(cliente, puerto, null);
                        usuarios.remove(u);
                        break;
                    default:
                        System.out.println("Mensaje de " + paquete.getSocketAddress() + " no procesado");
                }
            }

        } catch (UnknownHostException uh) {
        } catch (IOException io) {
        }

    }

    public static Usuario aceptaUsuario(byte[] data, InetAddress cliente, int puerto) {
        int nameLen = data[1];//ByteBuffer.allocateDirect(4).wrap(data, 1, 4).getInt();
        String nombreUsuario = new String(data, 2, nameLen);
        Usuario u = new Usuario(cliente, puerto, nombreUsuario);
        return u;
    }

    public static void enviaListaUsuarios(Usuario u) {
        try {
            Socket socket = new Socket(u.getAddress(), Puertos.CLIENTE_FLUJO);
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            os.flush();

            os.writeInt(usuarios.size());
            for (int i = 0; i < usuarios.size(); i++) {
                os.writeObject(usuarios.get(i));
            }

            os.close();
            socket.close();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void log(String msg) {
        System.out.println(msg);
    }
}
