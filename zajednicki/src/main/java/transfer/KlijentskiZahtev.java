package transfer;

import java.io.Serializable;

/**
 * Klasa KlijentskiZahtev predstavlja zahtev koji klijent šalje serveru.
 * Sadrži operaciju koju klijent zahteva da server izvrši i parametar 
 * koji je potreban za izvršenje te operacije.
 * 
 * Implementira interfejs Serializable kako bi objekti mogli biti serijalizovani 
 * i poslati putem mreže.
 * 
 * @author Ranko
 */
public class KlijentskiZahtev implements Serializable {

    /** Operacija koju klijent zahteva da server izvrši */
    private int operacija;

    /** Parametar koji je potreban za izvršenje operacije */
    private Object parametar;

    /**
     * Podrazumevani konstruktor koji inicijalizuje prazan objekat klase KlijentskiZahtev.
     */
    public KlijentskiZahtev() {
    }

    /**
     * Parametrizovani konstruktor koji postavlja operaciju i parametar za zahtev.
     * 
     * @param operacija Operacija koju klijent zahteva
     * @param parametar Parametar potreban za operaciju
     */
    public KlijentskiZahtev(int operacija, Object parametar) {
        this.operacija = operacija;
        this.parametar = parametar;
    }

    /**
     * Vraća operaciju koju klijent zahteva.
     * 
     * @return Operacija koju klijent zahteva
     */
    public int getOperacija() {
        return operacija;
    }

    /**
     * Postavlja operaciju koju klijent zahteva.
     * 
     * @param operacija Operacija koju klijent zahteva
     */
    public void setOperacija(int operacija) {
        this.operacija = operacija;
    }

    /**
     * Vraća parametar potreban za izvršenje operacije.
     * 
     * @return Parametar za operaciju
     */
    public Object getParametar() {
        return parametar;
    }

    /**
     * Postavlja parametar potreban za izvršenje operacije.
     * 
     * @param parametar Parametar za operaciju
     */
    public void setParametar(Object parametar) {
        this.parametar = parametar;
    }

}