package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Apstraktna klasa koja predstavlja osnovu za sve domenske objekte koji
 * komuniciraju sa bazom podataka. Svi domenski objekti treba da proširuju
 * ovu klasu i implementiraju njene metode, omogućavajući osnovne operacije
 * kao što su insert, update, select i join.
 * 
 * Klasa implementira interfejs {@code Serializable}, što omogućava serijalizaciju
 * objekata koji nasleđuju ovu klasu.
 * 
 * @author Ranko
 */
public abstract class ApstraktniDomenskiObjekat implements Serializable {

    /**
     * Vraća naziv tabele u bazi podataka koja je povezana sa ovim domenskim objektom.
     * 
     * @return Naziv tabele kao string.
     */
    public abstract String nazivTabele();

    /**
     * Vraća alijas za tabelu u SQL upitima.
     * 
     * @return Alijas za tabelu kao string.
     */
    public abstract String alijas();

    /**
     * Vraća JOIN klauzulu koja treba da bude korišćena prilikom upita sa ovom tabelom.
     * 
     * @return String koji predstavlja JOIN klauzulu ili prazan string ako nema povezivanja.
     */
    public abstract String join();

    /**
     * Pretvara {@code ResultSet} u listu domenskih objekata.
     * Ova metoda omogućava obradu podataka iz baze i konverziju u listu
     * specifičnih objekata koji proširuju ovu klasu.
     * 
     * @param rs {@code ResultSet} iz baze podataka.
     * @return Lista domenskih objekata.
     * @throws SQLException Ako dođe do greške tokom obrade {@code ResultSet}-a.
     */
    public abstract ArrayList<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException;

    /**
     * Vraća imena kolona koje će biti korišćene prilikom unosa podataka u bazu.
     * 
     * @return String sa imenima kolona.
     */
    public abstract String koloneZaInsert();

    /**
     * Vraća uslov koji će biti korišćen u WHERE klauzuli SQL upita.
     * 
     * @return String sa uslovom za WHERE klauzulu.
     */
    public abstract String uslov();

    /**
     * Vraća vrednosti koje će biti unete u bazu prilikom INSERT operacije.
     * 
     * @return String sa vrednostima za INSERT.
     */
    public abstract String vrednostiZaInsert();

    /**
     * Vraća vrednosti koje će biti ažurirane u bazi podataka prilikom UPDATE operacije.
     * 
     * @return String sa vrednostima za UPDATE.
     */
    public abstract String vrednostiZaUpdate();

    /**
     * Vraća uslov koji će biti korišćen prilikom SELECT upita.
     * 
     * @return String sa uslovom za SELECT ili prazan string ako nema specifičnog uslova.
     */
    public abstract String uslovZaSelect();
}