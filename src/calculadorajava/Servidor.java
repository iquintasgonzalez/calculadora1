package calculadorajava;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Servidor {

    public static void main(String[] args) throws IOException {
        //declara las variables con las que va a trabajar
        double num1, num2, total,fin = 0.0;
        int operacion;
        char opr = '\n';

        //Empieza un socket 12342
        ServerSocket servidor = new ServerSocket(12342);
        System.out.println("Puerta 12342 abierta!");

        //Esperando que un cliente se conecte
        System.out.print("Aguardando conexion do cliente...");
        Socket cliente = servidor.accept();
        //nuevo cliente conectado
        System.out.println("Nova conexion como cliente " + cliente.getInetAddress().getHostAddress());

        //flujo de envio y lectura
        ObjectOutputStream resultado = new ObjectOutputStream(cliente.getOutputStream());
        ObjectInputStream dados = new ObjectInputStream(cliente.getInputStream());
        //encapsula los datos recividos en variables
        operacion = dados.readInt();
        num1 = dados.readDouble();
        num2 = dados.readDouble();
       
        //if para seleccionar todas las operaciones
        if (operacion == 1) {

            opr = '+';
            total = (num1 + num2);

        } else if (operacion == 2) {

            opr = '-';
            total = (num1 - num2);

        } else if (operacion == 3) {

            opr = 'x';
            total = (num1 * num2);

        } else {

            opr = '/';
            total = (num1 / num2);

        }
        //despues de hacer la operacion reenvia al cliente los datos obtenidos
        resultado.writeDouble(total);
        resultado.writeChar(opr);
        resultado.flush();
        //cierra todos los flujos abiertos
        resultado.close();
        dados.close();         
        servidor.close();
       }
     
    
}