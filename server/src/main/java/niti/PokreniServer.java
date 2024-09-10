package niti;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Klasa PokreniServer predstavlja nit koja pokreće server na zadatom portu i omogućava
 * prihvatanje klijentskih zahteva. Server ostaje aktivan dok se ne zatvori.
 * 
 * @author Ranko
 */
public class PokreniServer extends Thread {

    /** Soket servera koji sluša na određenom portu */
    private ServerSocket serverSocket;

    /**
     * Konstruktor klase PokreniServer koji postavlja server da sluša na portu 9000.
     */
    public PokreniServer() {
        try {
            serverSocket = new ServerSocket(9000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda koja se izvršava kada se pokrene nit servera. Čeka na klijentske zahteve i
     * za svakog klijenta pokreće novu nit za obradu zahteva.
     */
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

    /**
     * Vraća trenutni server soket.
     * 
     * @return ServerSocket koji služi za prihvatanje klijentskih zahteva
     */
    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    /**
     * Postavlja novi server soket.
     * 
     * @param serverSocket Novi server soket
     */
    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
}
