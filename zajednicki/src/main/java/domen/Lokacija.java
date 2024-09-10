package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Klasa Lokacija predstavlja entitet lokacije na kojoj se može održavati koncert 
 * ili neki drugi događaj. Sadrži atribute koji opisuju lokaciju kao što su naziv, 
 * adresa, tip, kontakt i vlasnik.
 * Nasleđuje klasu ApstraktniDomenskiObjekat koja omogućava osnovne operacije 
 * nad bazom podataka.
 * 
 * @author Ranko
 */
public class Lokacija extends ApstraktniDomenskiObjekat {

    /** Jedinstveni identifikator lokacije */
    private Long lokacjiaID;

    /** Naziv lokacije */
    private String naziv;

    /** Adresa lokacije */
    private String adresa;

    /** Tip lokacije (npr. otvoreni prostor, sala) */
    private String tip;

    /** Kontakt osoba ili broj telefona za lokaciju */
    private String kontakt;

    /** Vlasnik lokacije */
    private String vlasnik;

    /** 
     * Podrazumevani konstruktor koji inicijalizuje prazan objekat klase Lokacija.
     */
    public Lokacija() {
    }

    /** 
     * Parametrizovani konstruktor koji postavlja sve atribute objekta Lokacija.
     * 
     * @param lokacjiaID Jedinstveni identifikator lokacije
     * @param naziv Naziv lokacije
     * @param adresa Adresa lokacije
     * @param tip Tip lokacije
     * @param kontakt Kontakt za lokaciju
     * @param vlasnik Vlasnik lokacije
     */
    public Lokacija(Long lokacjiaID, String naziv, String adresa, String tip, String kontakt, String vlasnik) {
        this.lokacjiaID = lokacjiaID;
        this.naziv = naziv;
        this.adresa = adresa;
        this.tip = tip;
        this.kontakt = kontakt;
        this.vlasnik = vlasnik;
    }

    /** 
     * Vraća vlasnika lokacije.
     * 
     * @return Vlasnik lokacije
     */
    public String getVlasnik() {
        return vlasnik;
    }

    /** 
     * Postavlja vlasnika lokacije.
     * 
     * @param vlasnik Vlasnik lokacije
     */
    public void setVlasnik(String vlasnik) {
        this.vlasnik = vlasnik;
    }

    /** 
     * Vraća identifikator lokacije.
     * 
     * @return Jedinstveni identifikator lokacije
     */
    public Long getLokacjiaID() {
        return lokacjiaID;
    }

    /** 
     * Postavlja identifikator lokacije.
     * 
     * @param lokacjiaID Jedinstveni identifikator lokacije
     */
    public void setLokacjiaID(Long lokacjiaID) {
        this.lokacjiaID = lokacjiaID;
    }

    /** 
     * Vraća naziv lokacije.
     * 
     * @return Naziv lokacije
     */
    public String getNaziv() {
        return naziv;
    }

    /** 
     * Postavlja naziv lokacije.
     * 
     * @param naziv Naziv lokacije
     */
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    /** 
     * Vraća adresu lokacije.
     * 
     * @return Adresa lokacije
     */
    public String getAdresa() {
        return adresa;
    }

    /** 
     * Postavlja adresu lokacije.
     * 
     * @param adresa Adresa lokacije
     */
    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    /** 
     * Vraća tip lokacije.
     * 
     * @return Tip lokacije
     */
    public String getTip() {
        return tip;
    }

    /** 
     * Postavlja tip lokacije.
     * 
     * @param tip Tip lokacije
     */
    public void setTip(String tip) {
        this.tip = tip;
    }

    /** 
     * Vraća kontakt informacije za lokaciju.
     * 
     * @return Kontakt za lokaciju
     */
    public String getKontakt() {
        return kontakt;
    }

    /** 
     * Postavlja kontakt informacije za lokaciju.
     * 
     * @param kontakt Kontakt za lokaciju
     */
    public void setKontakt(String kontakt) {
        this.kontakt = kontakt;
    }

    /** 
     * Vraća naziv tabele u bazi podataka koja odgovara entitetu Lokacija.
     * 
     * @return Naziv tabele
     */
    @Override
    public String nazivTabele() {
        return " lokacija ";
    }

    /** 
     * Vraća alias koji se koristi za entitet Lokacija u SQL upitima.
     * 
     * @return Alias za lokaciju
     */
    @Override
    public String alijas() {
        return " l ";
    }

    /** 
     * Metoda koja vraća SQL JOIN upit. Pošto entitet Lokacija nema eksplicitne 
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
            Lokacija a = new Lokacija(rs.getLong("LokacijaID"),
                    rs.getString("Naziv"), rs.getString("Adresa"),
                    rs.getString("Tip"), rs.getString("Kontakt"),
                    rs.getString("Vlasnik"));

            lista.add(a);
        }

        rs.close();
        return lista;
    }

    /** 
     * Vraća nazive kolona koje se koriste za INSERT upit u bazi podataka.
     * U ovom slučaju, ne vraća ništa jer nisu specificirane kolone.
     * 
     * @return Prazan string za INSERT kolone
     */
    @Override
    public String koloneZaInsert() {
        return "";
    }

    /** 
     * Vraća uslov za SQL upit koji se koristi za identifikaciju specifične lokacije.
     * 
     * @return SQL uslov
     */
    @Override
    public String uslov() {
        return " LokacijaID = " + lokacjiaID;
    }

    /** 
     * Vraća vrednosti koje će biti umetnute u bazu podataka prilikom INSERT upita.
     * U ovom slučaju, ne vraća ništa jer vrednosti nisu specificirane.
     * 
     * @return Prazan string za INSERT vrednosti
     */
    @Override
    public String vrednostiZaInsert() {
        return "";
    }

    /** 
     * Vraća vrednosti koje će biti ažurirane u bazi podataka prilikom UPDATE upita.
     * U ovom slučaju, ne vraća ništa jer vrednosti nisu specificirane.
     * 
     * @return Prazan string za UPDATE vrednosti
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
