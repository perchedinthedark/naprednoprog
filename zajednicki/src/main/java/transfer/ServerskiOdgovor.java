package transfer;

import java.io.Serializable;

import konstante.StatusServerskogOdgovora;

/**
 * Klasa ServerskiOdgovor predstavlja odgovor koji server šalje klijentu 
 * nakon izvršenja zahteva. Odgovor sadrži rezultat operacije, eventualnu grešku 
 * ako je do nje došlo, i status izvršenja operacije.
 * 
 * Implementira interfejs Serializable kako bi objekti mogli biti serijalizovani 
 * i poslati putem mreže.
 * 
 * @author Korisnik
 */
public class ServerskiOdgovor implements Serializable {

    /** Rezultat operacije koju je server izvršio */
    private Object odgovor;

    /** Izuzetak koji je nastao prilikom izvršenja operacije, ako ga je bilo */
    private Exception exc;

    /** Status serverskog odgovora (Success ili Error) */
    private StatusServerskogOdgovora statusOdgovora;

    /**
     * Podrazumevani konstruktor koji inicijalizuje prazan objekat klase ServerskiOdgovor.
     */
    public ServerskiOdgovor() {
    }

    /**
     * Parametrizovani konstruktor koji postavlja odgovor, izuzetak i status odgovora.
     * 
     * @param odgovor Rezultat operacije koju je server izvršio
     * @param exc Izuzetak koji je nastao prilikom izvršenja operacije
     * @param statusOdgovora Status serverskog odgovora
     */
    public ServerskiOdgovor(Object odgovor, Exception exc, StatusServerskogOdgovora statusOdgovora) {
        this.odgovor = odgovor;
        this.exc = exc;
        this.statusOdgovora = statusOdgovora;
    }

    /**
     * Vraća rezultat operacije koju je server izvršio.
     * 
     * @return Rezultat operacije
     */
    public Object getOdgovor() {
        return odgovor;
    }

    /**
     * Postavlja rezultat operacije koju je server izvršio.
     * 
     * @param odgovor Rezultat operacije
     */
    public void setOdgovor(Object odgovor) {
        this.odgovor = odgovor;
    }

    /**
     * Vraća izuzetak koji je nastao prilikom izvršenja operacije, ako ga je bilo.
     * 
     * @return Izuzetak prilikom izvršenja operacije
     */
    public Exception getExc() {
        return exc;
    }

    /**
     * Postavlja izuzetak koji je nastao prilikom izvršenja operacije.
     * 
     * @param exc Izuzetak prilikom izvršenja operacije
     */
    public void setExc(Exception exc) {
        this.exc = exc;
    }

    /**
     * Vraća status serverskog odgovora, koji može biti Success ili Error.
     * 
     * @return Status serverskog odgovora
     */
    public StatusServerskogOdgovora getStatusOdgovora() {
        return statusOdgovora;
    }

    /**
     * Postavlja status serverskog odgovora (Success ili Error).
     * 
     * @param statusOdgovora Status serverskog odgovora
     */
    public void setStatusOdgovora(StatusServerskogOdgovora statusOdgovora) {
        this.statusOdgovora = statusOdgovora;
    }

}
