package sesija;

import domen.Administrator;
import java.io.IOException;
import java.net.Socket;


/**
 * Klasa Sesija implementira Singleton obrazac i služi za održavanje sesije između klijenta i servera.
 * Kroz ovu klasu se održava konekcija sa serverom i čuva se prijavljeni administrator.
 * 
 * @author Ranko
 */
public class Sesija {

    /** Jedinstvena instanca klase Sesija (Singleton) */
    private static Sesija instance;
    /** Soket koji se koristi za komunikaciju sa serverom */
    private Socket socket;
    /** Prijavljeni administrator */
    private Administrator ulogovani;

    /**
     * Privatni konstruktor za klasu Sesija.
     * Uspostavlja konekciju sa serverom na portu 9000.
     */
    private Sesija() {
        try {
            socket = new Socket("localhost", 9000);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Vraća jedinstvenu instancu klase Sesija.
     * Ako instanca ne postoji, kreira se nova.
     * 
     * @return Jedinstvena instanca klase Sesija
     */
    public static Sesija getInstance() {
        if (instance == null) {
            instance = new Sesija();
        }
        return instance;
    }

    /**
     * Vraća soket koji se koristi za komunikaciju sa serverom.
     * 
     * @return Soket za komunikaciju sa serverom
     */
    public Socket getSocket() {
        return socket;
    }

    /**
     * Postavlja prijavljenog administratora.
     * 
     * @param ulogovani Administrator koji je prijavljen
     */
    public void setUlogovani(Administrator ulogovani) {
        this.ulogovani = ulogovani;
    }

    /**
     * Vraća prijavljenog administratora.
     * 
     * @return Administrator koji je prijavljen
     */
    public Administrator getUlogovani() {
        return ulogovani;
    }
}