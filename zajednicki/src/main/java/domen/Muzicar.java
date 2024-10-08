package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Klasa Muzicar predstavlja entitet muzičara sa relevantnim informacijama kao što su 
 * ime, instrument koji svira, žanr muzike i kontakt podaci. Nasleđuje klasu 
 * ApstraktniDomenskiObjekat koja omogućava osnovne operacije nad bazom podataka.
 * 
 * @author Ranko
 */
public class Muzicar extends ApstraktniDomenskiObjekat {

    /** Jedinstveni identifikator muzičara */
    private Long muzicarID;

    /** Ime muzičara */
    private String ime;

    /** Instrument koji muzičar svira */
    private String instrument;

    /** Muzički žanr koji muzičar svira */
    private String zanr;

    /** Kontakt informacije za muzičara */
    private String kontakt;

    /** 
     * Podrazumevani konstruktor koji inicijalizuje prazan objekat klase Muzicar.
     */
    public Muzicar() {
    }

    /** 
     * Parametrizovani konstruktor koji postavlja sve atribute objekta Muzicar.
     * 
     * @param muzicarID Jedinstveni identifikator muzičara
     * @param ime Ime muzičara
     * @param instrument Instrument koji muzičar svira
     * @param zanr Muzički žanr muzičara
     * @param kontakt Kontakt informacije za muzičara
     */
    public Muzicar(Long muzicarID, String ime, String instrument, String zanr, String kontakt) {
    	setMuzicarID(muzicarID);
        setIme(ime);
        setInstrument(instrument);
        setZanr(zanr);
        setKontakt(kontakt);
    }

    /** 
     * Vraća kontakt informacije muzičara.
     * 
     * @return Kontakt informacije
     */
    public String getKontakt() {
        return kontakt;
    }

    /** 
     * Postavlja kontakt informacije za muzičara.
     * 
     * @param kontakt Kontakt informacije
     * @throws IllegalArgumentException ako su kontakt informacije null ili prazne.
     */
    public void setKontakt(String kontakt) {
    	if (kontakt == null || kontakt.trim().isEmpty()) {
            throw new IllegalArgumentException("Kontakt informacije ne mogu biti prazne ili null.");
        }
        this.kontakt = kontakt;
    }

    /** 
     * Vraća identifikator muzičara.
     * 
     * @return Jedinstveni identifikator muzičara
     */
    public Long getMuzicarID() {
        return muzicarID;
    }

    /** 
     * Postavlja identifikator muzičara.
     * 
     * @param muzicarID Jedinstveni identifikator muzičara
     * @throws IllegalArgumentException ako je muzicarID null ili manji od 1.
     */
    public void setMuzicarID(Long muzicarID) {
    	if (muzicarID == null || muzicarID <= 0) {
            throw new IllegalArgumentException("Muzicar ID mora biti pozitivan broj.");
        }
        this.muzicarID = muzicarID;
    }

    /** 
     * Vraća ime muzičara.
     * 
     * @return Ime muzičara
     */
    public String getIme() {
        return ime;
    }

    /** 
     * Postavlja ime muzičara.
     * 
     * @param ime Ime muzičara
     * @throws IllegalArgumentException ako je ime null ili prazno.
     */
    public void setIme(String ime) {
    	if (ime == null || ime.trim().isEmpty()) {
            throw new IllegalArgumentException("Ime ne može biti prazno ili null.");
        }
        this.ime = ime;
    }

    /** 
     * Vraća instrument koji muzičar svira.
     * 
     * @return Instrument muzičara
     */
    public String getInstrument() {
        return instrument;
    }

    /** 
     * Postavlja instrument koji muzičar svira.
     * 
     * @param instrument Instrument muzičara
     * @throws IllegalArgumentException ako je instrument null ili prazan.
     */
    public void setInstrument(String instrument) {
    	if (instrument == null || instrument.trim().isEmpty()) {
            throw new IllegalArgumentException("Instrument ne može biti prazan ili null.");
        }
        this.instrument = instrument;
    }

    /** 
     * Vraća muzički žanr muzičara.
     * 
     * @return Žanr muzičara
     */
    public String getZanr() {
        return zanr;
    }

    /** 
     * Postavlja muzički žanr muzičara.
     * 
     * @param zanr Žanr muzičara
     * @throws IllegalArgumentException ako je žanr null ili prazan.
     */
    public void setZanr(String zanr) {
    	if (zanr == null || zanr.trim().isEmpty()) {
            throw new IllegalArgumentException("Žanr ne može biti prazan ili null.");
        }
        this.zanr = zanr;
    }

    /** 
     * Vraća naziv tabele u bazi podataka koja odgovara entitetu Muzicar.
     * 
     * @return Naziv tabele
     */
    @Override
    public String nazivTabele() {
        return " muzicar ";
    }

    /** 
     * Vraća alias koji se koristi za entitet Muzicar u SQL upitima.
     * 
     * @return Alias za muzičara
     */
    @Override
    public String alijas() {
        return " m ";
    }

    /** 
     * Metoda koja vraća SQL JOIN upit. Pošto entitet Muzicar nema eksplicitne 
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
            Muzicar m = new Muzicar(rs.getLong("MuzicarID"),
                    rs.getString("Ime"), rs.getString("Instrument"),
                    rs.getString("Zanr"), rs.getString("Kontakt"));

            lista.add(m);
        }

        rs.close();
        return lista;
    }

    /** 
     * Vraća nazive kolona koje se koriste za INSERT upit u bazi podataka.
     * 
     * @return Nazivi kolona za INSERT upit
     */
    @Override
    public String koloneZaInsert() {
        return " (Ime, Instrument, Zanr, Kontakt) ";
    }

    /** 
     * Vraća uslov za SQL upit koji se koristi za identifikaciju specifičnog muzičara.
     * 
     * @return SQL uslov
     */
    @Override
    public String uslov() { 
        return " MuzicarID = " + muzicarID;
    }

    /** 
     * Vraća vrednosti koje će biti umetnute u bazu podataka prilikom INSERT upita.
     * 
     * @return Vrednosti za INSERT upit
     */
    @Override
    public String vrednostiZaInsert() {
         return "'" + ime + "', '" + instrument + "', "
                + "'" + zanr + "', '" + kontakt + "'";
    }

    /** 
     * Vraća vrednosti koje će biti ažurirane u bazi podataka prilikom UPDATE upita.
     * 
     * @return Vrednosti za UPDATE upit
     */
    @Override
    public String vrednostiZaUpdate() {
         return " instrument = '" + instrument + "', zanr = '" + zanr + "', kontakt = '" + kontakt + "' ";
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
     * Vraća string reprezentaciju objekta Muzicar, koja sadrži samo ime muzičara.
     * 
     * @return Ime muzičara kao string
     */
    @Override
    public String toString() {
        return ime;
    }
}

