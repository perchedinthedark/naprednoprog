package niti;

import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Ana
 */
public class PokreniServer extends Thread {

    private ServerSocket serverSocket;

    public PokreniServer() {
        try {
            serverSocket = new ServerSocket(9000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                System.out.println("Klijent se povezao!");
                ObradaKlijentskihZahteva th = new ObradaKlijentskihZahteva(socket);
                th.start();
            }
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

}
