package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Klasa Sponzor predstavlja entitet sponzora koji finansira određeni događaj.
 * Sadrži informacije o nazivu sponzora, iznosu kontribucije i tipu sponzorstva.
 * Nasleđuje klasu ApstraktniDomenskiObjekat koja omogućava osnovne operacije 
 * nad bazom podataka.
 * 
 * @author Ranko
 */
public class Sponzor extends ApstraktniDomenskiObjekat {

    /** Jedinstveni identifikator sponzora */
    private Long sponzorID;

    /** Naziv sponzora */
    private String naziv;

    /** Iznos kontribucije sponzora */
    private double iznosKontribucije;

    /** Tip sponzorstva (npr. finansijski, medijski) */
    private String tipSponzorstva;

    /** 
     * Podrazumevani konstruktor koji inicijalizuje prazan objekat klase Sponzor.
     */
    public Sponzor() {
    }

    /** 
     * Parametrizovani konstruktor koji postavlja sve atribute objekta Sponzor.
     * 
     * @param sponzorID Jedinstveni identifikator sponzora
     * @param naziv Naziv sponzora
     * @param iznosKontribucije Iznos kontribucije sponzora
     * @param tipSponzorstva Tip sponzorstva
     */
    public Sponzor(Long sponzorID, String naziv, double iznosKontribucije, String tipSponzorstva) {
    	setSponzorID(sponzorID);
        setNaziv(naziv);
        setIznosKontribucije(iznosKontribucije);
        setTipSponzorstva(tipSponzorstva);
    }

    /** 
     * Vraća tip sponzorstva.
     * 
     * @return Tip sponzorstva
     */
    public String getTipSponzorstva() {
        return tipSponzorstva;
    }

    /** 
     * Postavlja tip sponzorstva.
     * 
     * @param tipSponzorstva Tip sponzorstva
     * @throws IllegalArgumentException ako je tip sponzorstva null ili prazan.
     */
    public void setTipSponzorstva(String tipSponzorstva) {
    	if (tipSponzorstva == null || tipSponzorstva.trim().isEmpty()) {
            throw new IllegalArgumentException("Tip sponzorstva ne može biti prazan ili null.");
        }
        this.tipSponzorstva = tipSponzorstva;
    }

    /** 
     * Vraća identifikator sponzora.
     * 
     * @return Jedinstveni identifikator sponzora
     */
    public Long getSponzorID() {
        return sponzorID;
    }

    /** 
     * Postavlja identifikator sponzora.
     * 
     * @param sponzorID Jedinstveni identifikator sponzora
     * @throws IllegalArgumentException ako je sponzorID null ili manji od 1.
     */
    public void setSponzorID(Long sponzorID) {
    	 if (sponzorID == null || sponzorID <= 0) {
             throw new IllegalArgumentException("Sponzor ID mora biti pozitivan broj.");
         }
        this.sponzorID = sponzorID;
    }

    /** 
     * Vraća iznos kontribucije sponzora.
     * 
     * @return Iznos kontribucije sponzora
     */
    public double getIznosKontribucije() {
        return iznosKontribucije;
    }

    /** 
     * Postavlja iznos kontribucije sponzora.
     * 
     * @param iznosKontribucije Iznos kontribucije sponzora
     * @throws IllegalArgumentException ako je iznos kontribucije negativan.
     */
    public void setIznosKontribucije(double iznosKontribucije) {
    	if (iznosKontribucije < 0) {
            throw new IllegalArgumentException("Iznos kontribucije ne može biti negativan.");
        }
        this.iznosKontribucije = iznosKontribucije;
    }

    /** 
     * Vraća naziv sponzora.
     * 
     * @return Naziv sponzora
     */
    public String getNaziv() {
        return naziv;
    }

    /** 
     * Postavlja naziv sponzora.
     * 
     * @param naziv Naziv sponzora
     * @throws IllegalArgumentException ako je naziv null ili prazan.
     */
    public void setNaziv(String naziv) {
    	 if (naziv == null || naziv.trim().isEmpty()) {
             throw new IllegalArgumentException("Naziv ne može biti prazan ili null.");
         }
        this.naziv = naziv;
    }

    /** 
     * Vraća naziv tabele u bazi podataka koja odgovara entitetu Sponzor.
     * 
     * @return Naziv tabele
     */
    @Override
    public String nazivTabele() {
        return " sponzor ";
    }

    /** 
     * Vraća alias koji se koristi za entitet Sponzor u SQL upitima.
     * 
     * @return Alias za sponzora
     */
    @Override
    public String alijas() {
        return " s ";
    }

    /** 
     * Metoda koja vraća SQL JOIN upit. Pošto entitet Sponzor nema eksplicitne 
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
            Sponzor s = new Sponzor(rs.getLong("SponzorID"),
                    rs.getString("Naziv"), rs.getDouble("IznosKontribucije"),
                    rs.getString("TipSponzorstva"));

            lista.add(s);
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
     * Vraća uslov za SQL upit koji se koristi za identifikaciju specifičnog sponzora.
     * 
     * @return SQL uslov
     */
    @Override
    public String uslov() {
        return " SponzorID = " + sponzorID;
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

    /** 
     * Vraća string reprezentaciju objekta Sponzor, koja sadrži naziv sponzora.
     * 
     * @return Naziv sponzora kao string
     */
    @Override
    public String toString() {
        return naziv;
    }
}