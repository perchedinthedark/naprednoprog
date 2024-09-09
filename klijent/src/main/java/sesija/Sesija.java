package sesija;

import domen.Administrator;
import java.io.IOException;
import java.net.Socket;


public class Sesija {

    private static Sesija instance;
    private Socket socket;
    private Administrator ulogovani;

    private Sesija() {
        try {
            socket = new Socket("localhost", 9000);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Sesija getInstance() {
        if (instance == null) {
            instance = new Sesija();
        }
        return instance;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setUlogovani(Administrator ulogovani) {
        this.ulogovani = ulogovani;
    }

    public Administrator getUlogovani() {
        return ulogovani;
    }

}
