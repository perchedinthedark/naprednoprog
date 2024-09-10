package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Klasa Oprema predstavlja entitet opreme koja se koristi u organizaciji događaja,
 * poput koncerata. Oprema može uključivati tehničku i scensku opremu.
 * Sadrži atribute kao što su naziv, opis i ukupna cena opreme.
 * Nasleđuje klasu ApstraktniDomenskiObjekat koja omogućava osnovne operacije 
 * nad bazom podataka.
 * 
 * @author Ranko
 */
public class Oprema extends ApstraktniDomenskiObjekat {

    /** Jedinstveni identifikator opreme */
    private Long opremaID;

    /** Naziv opreme */
    private String naziv;

    /** Opis opreme */
    private String opis;

    /** Ukupna cena opreme */
    private double ukupnaCena;

    /** 
     * Podrazumevani konstruktor koji inicijalizuje prazan objekat klase Oprema.
     */
    public Oprema() {
    }

    /** 
     * Parametrizovani konstruktor koji postavlja sve atribute objekta Oprema.
     * 
     * @param opremaID Jedinstveni identifikator opreme
     * @param naziv Naziv opreme
     * @param opis Opis opreme
     * @param cena Ukupna cena opreme
     */
    public Oprema(Long opremaID, String naziv, String opis, double cena) {
        this.opremaID = opremaID;
        this.naziv = naziv;
        this.opis = opis;
        this.ukupnaCena = cena;
    }

    /** 
     * Vraća identifikator opreme.
     * 
     * @return Jedinstveni identifikator opreme
     */
    public Long getOpremaID() {
        return opremaID;
    }

    /** 
     * Postavlja identifikator opreme.
     * 
     * @param opremaID Jedinstveni identifikator opreme
     */
    public void setOpremaID(Long opremaID) {
        this.opremaID = opremaID;
    }

    /** 
     * Vraća naziv opreme.
     * 
     * @return Naziv opreme
     */
    public String getNaziv() {
        return naziv;
    }

    /** 
     * Postavlja naziv opreme.
     * 
     * @param naziv Naziv opreme
     */
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    /** 
     * Vraća opis opreme.
     * 
     * @return Opis opreme
     */
    public String getOpis() {
        return opis;
    }

    /** 
     * Postavlja opis opreme.
     * 
     * @param opis Opis opreme
     */
    public void setOpis(String opis) {
        this.opis = opis;
    }

    /** 
     * Vraća ukupnu cenu opreme.
     * 
     * @return Ukupna cena opreme
     */
    public double getUkupnaCena() {
        return ukupnaCena;
    }

    /** 
     * Postavlja ukupnu cenu opreme.
     * 
     * @param cena Ukupna cena opreme
     */
    public void setUkupnaCena(int cena) {
        this.ukupnaCena = cena;
    }

    /** 
     * Vraća naziv tabele u bazi podataka koja odgovara entitetu Oprema.
     * 
     * @return Naziv tabele
     */
    @Override
    public String nazivTabele() {
        return " Oprema ";
    }

    /** 
     * Vraća alias koji se koristi za entitet Oprema u SQL upitima.
     * 
     * @return Alias za opremu
     */
    @Override
    public String alijas() {
        return " o ";
    }

    /** 
     * Metoda koja vraća SQL JOIN upit. Pošto entitet Oprema nema eksplicitne 
     * veze sa drugim entitetima, ne vraća ništa.
     * 
     * @return Prazan SQL JOIN upit
     */
    @Override
    public String join() {
        return "";
    }

    /** 
     * Kreira listu objekata na osnovu rezultata upita iz baze podataka.
     * 
     * @param rs ResultSet sa rezultatima upita
     * @return Lista ApstraktniDomenskiObjekat koja sadrži rezultate upita
     * @throws SQLException Ako dođe do greške pri radu sa bazom podataka
     */
    @Override
    public ArrayList<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            Oprema o = new Oprema(rs.getLong("opremaID"), rs.getString("naziv"),
                    rs.getString("opis"), rs.getDouble("ukupnaCena"));

            lista.add(o);
        }

        rs.close();
        return lista;
    }

    /** 
     * Vraća nazive kolona koje se koriste za INSERT upit u bazi podataka.
     * 
     * @return Prazan string jer nisu definisane kolone za INSERT
     */
    @Override
    public String koloneZaInsert() {
        return "";
    }

    /** 
     * Vraća uslov za SQL upit koji se koristi za identifikaciju specifične opreme.
     * 
     * @return SQL uslov
     */
    @Override
    public String uslov() {
        return " opremaID = " + opremaID;
    }

    /** 
     * Vraća vrednosti koje će biti umetnute u bazu podataka prilikom INSERT upita.
     * 
     * @return Prazan string jer vrednosti nisu definisane
     */
    @Override
    public String vrednostiZaInsert() {
        return "";
    }

    /** 
     * Vraća vrednosti koje će biti ažurirane u bazi podataka prilikom UPDATE upita.
     * 
     * @return Prazan string jer vrednosti nisu definisane
     */
    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    /** 
     * Vraća uslov za SELECT upit, koji može biti prazan ako nije potrebna specifična selekcija.
     * 
     * @return Uslov za SELECT upit
     */
    @Override
    public String uslovZaSelect() {
        return "";
    }
}