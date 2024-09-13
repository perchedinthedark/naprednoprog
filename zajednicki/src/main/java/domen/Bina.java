package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Klasa koja predstavlja entitet Bina u sistemu. 
 * Bina ima naziv, kapacitet, lokaciju i opremu.
 * Nasleđuje osnovne metode iz {@code ApstraktniDomenskiObjekat}.
 * 
 * @author Ranko
 */
public class Bina extends ApstraktniDomenskiObjekat {

    /**
     * Jedinstveni identifikator bine.
     */
    private Long binaID;
    
    /**
     * Naziv bine.
     */
    private String naziv;
    
    /**
     * Kapacitet bine.
     */
    private int kapacitet;
    
    /**
     * Lokacija na kojoj se bina nalazi.
     */
    private Lokacija lokacija;
    
    /**
     * Oprema koja je dostupna na bini.
     */
    private Oprema oprema;

    /**
     * Prazan konstruktor za kreiranje nove instance Bine.
     */
    public Bina() {
    }

    /**
     * Konstruktor koji postavlja sve atribute Bine.
     * 
     * @param binaID Jedinstveni identifikator bine
     * @param naziv Naziv bine
     * @param kapacitet Kapacitet bine
     * @param lokacija Lokacija na kojoj se nalazi bina
     * @param oprema Oprema koja je dostupna na bini
     */
    public Bina(Long binaID, String naziv, int kapacitet, Lokacija lokacija, Oprema oprema) {
        this.binaID = binaID;
        this.naziv = naziv;
        this.kapacitet = kapacitet;
        this.lokacija = lokacija;
        this.oprema = oprema;
    }

    /**
     * Vraća lokaciju povezanu sa Binom.
     * 
     * @return Lokacija na kojoj se nalazi bina.
     */
    public Lokacija getLokacija() {
        return lokacija;
    }

    /**
     * Postavlja novu lokaciju za Binom.
     * 
     * @param lokacija Nova lokacija bine.
     */
    public void setLokacija(Lokacija lokacija) {
        this.lokacija = lokacija;
    }

    /**
     * Vraća jedinstveni identifikator Bine.
     * 
     * @return Jedinstveni identifikator bine.
     */
    public Long getBinaID() {
        return binaID;
    }

    /**
     * Postavlja jedinstveni identifikator za Binu.
     * 
     * @param binaID Jedinstveni identifikator bine.
     */
    public void setBinaID(Long binaID) {
        this.binaID = binaID;
    }

    /**
     * Vraća naziv Bine.
     * 
     * @return Naziv bine.
     */
    public String getNaziv() {
        return naziv;
    }

    /**
     * Postavlja naziv za Binu.
     * 
     * @param naziv Novi naziv bine.
     */
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    /**
     * Vraća kapacitet Bine.
     * 
     * @return Kapacitet bine.
     */
    public int getKapacitet() {
        return kapacitet;
    }

    /**
     * Postavlja kapacitet za Binu.
     * 
     * @param kapacitet Novi kapacitet bine.
     */
    public void setKapacitet(int kapacitet) {
        this.kapacitet = kapacitet;
    }

    /**
     * Vraća opremu povezanu sa Binom.
     * 
     * @return Oprema koja je dostupna na bini.
     */
    public Oprema getOprema() {
        return oprema;
    }

    /**
     * Postavlja opremu za Binu.
     * 
     * @param oprema Nova oprema bine.
     */
    public void setOprema(Oprema oprema) {
        this.oprema = oprema;
    }

    /**
     * Vraća naziv tabele povezane sa Binom.
     * 
     * @return Naziv tabele u bazi podataka.
     */
    @Override
    public String nazivTabele() {
        return " Bina ";
    }

    /**
     * Vraća alijas za tabelu Bina.
     * 
     * @return Alijas za SQL upite.
     */
    @Override
    public String alijas() {
        return " b ";
    }

    /**
     * Definiše SQL JOIN za povezivanje sa tabelama Lokacija i Oprema.
     * 
     * @return SQL JOIN klauzula za povezivanje sa lokacijom i opremom.
     */
    @Override
    public String join() {
        return " JOIN LOKACIJA L ON (B.LOKACIJAID = L.LOKACIJAID) "
                + "JOIN OPREMA O ON (B.OPREMAID = O.OPREMAID) ";
    }

    /**
     * Pretvara {@code ResultSet} iz baze podataka u listu Bina objekata.
     * 
     * @param rs {@code ResultSet} sa podacima iz baze
     * @return Lista Bina objekata
     * @throws SQLException Ako dođe do greške tokom obrade {@code ResultSet}-a
     */
    @Override
    public ArrayList<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            Oprema o = new Oprema(rs.getLong("opremaID"), rs.getString("naziv"),
                    rs.getString("opis"), rs.getDouble("ukupnaCena"));
            
            Lokacija l = new Lokacija(rs.getLong("LokacijaID"),
                    rs.getString("Naziv"), rs.getString("Adresa"),
                    rs.getString("Tip"), rs.getString("Kontakt"),
                    rs.getString("Vlasnik"));

            Bina b = new Bina(rs.getLong("BinaID"), rs.getString("BinaNaziv"),
                    rs.getInt("kapacitet"), l, o);

            lista.add(b);
        }

        rs.close();
        return lista;
    }

    /**
     * Vraća nazive kolona za umetanje u bazu podataka (trenutno nije implementirano).
     * 
     * @return Prazan string, pošto umetanje nije definisano.
     */
    @Override
    public String koloneZaInsert() {
        return "";
    }

    /**
     * Vraća SQL WHERE uslov za pretragu po identifikatoru bine.
     * 
     * @return SQL WHERE klauzula za pretragu po BinaID.
     */
    @Override
    public String uslov() {
        return " binaID = " + binaID;
    }

    /**
     * Vraća vrednosti za umetanje u bazu podataka (trenutno nije implementirano).
     * 
     * @return Prazan string, pošto umetanje nije definisano.
     */
    @Override
    public String vrednostiZaInsert() {
        return "";
    }

    /**
     * Vraća SQL vrednosti za ažuriranje u bazi podataka (trenutno nije implementirano).
     * 
     * @return Prazan string, pošto ažuriranje nije definisano.
     */
    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    /**
     * Vraća SQL uslov za SELECT upit (trenutno nije implementirano).
     * 
     * @return Prazan string, pošto uslov za SELECT nije definisan.
     */
    @Override
    public String uslovZaSelect() {
        return "";
    }

    /**
     * Vraća string koji predstavlja naziv Bine.
     * 
     * @return Naziv bine.
     */
    @Override
    public String toString() {
        return naziv;
    }
}
