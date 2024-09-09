package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Korisnik
 */
public class Lokacija extends ApstraktniDomenskiObjekat{
    
    private Long lokacjiaID;
    private String naziv;
    private String adresa;
    private String tip;
    private String kontakt;
    private String vlasnik;

    public Lokacija() {
    }

    public Lokacija(Long lokacjiaID, String naziv, String adresa, String tip, String kontakt, String vlasnik) {
        this.lokacjiaID = lokacjiaID;
        this.naziv = naziv;
        this.adresa = adresa;
        this.tip = tip;
        this.kontakt = kontakt;
        this.vlasnik = vlasnik;
    }

    public String getVlasnik() {
        return vlasnik;
    }

    public void setVlasnik(String vlasnik) {
        this.vlasnik = vlasnik;
    }

    public Long getLokacjiaID() {
        return lokacjiaID;
    }

    public void setLokacjiaID(Long lokacjiaID) {
        this.lokacjiaID = lokacjiaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getKontakt() {
        return kontakt;
    }

    public void setKontakt(String kontakt) {
        this.kontakt = kontakt;
    }

    @Override
    public String nazivTabele() {
        return " lokacija ";
    }

    @Override
    public String alijas() {
        return " l ";
    }

    @Override
    public String join() {
        return "";
    }

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

    @Override
    public String koloneZaInsert() {
        return "";
    }

    @Override
    public String uslov() {
        return " LokacijaID = " + lokacjiaID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }
    
    
    
}
