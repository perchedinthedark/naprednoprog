package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Korisnik
 */
public class Muzicar extends ApstraktniDomenskiObjekat{
    
    private Long muzicarID;
    private String ime;
    private String instrument;
    private String zanr;
    private String kontakt;

    public Muzicar() {
    }

    public Muzicar(Long muzicarID, String ime, String instrument, String zanr, String kontakt) {
        this.muzicarID = muzicarID;
        this.ime = ime;
        this.instrument = instrument;
        this.zanr = zanr;
        this.kontakt = kontakt;
    }

    public String getKontakt() {
        return kontakt;
    }

    public void setKontakt(String kontakt) {
        this.kontakt = kontakt;
    }

    public Long getMuzicarID() {
        return muzicarID;
    }

    public void setMuzicarID(Long muzicarID) {
        this.muzicarID = muzicarID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public String getZanr() {
        return zanr;
    }

    public void setZanr(String zanr) {
        this.zanr = zanr;
    }

    @Override
    public String nazivTabele() {
        return " muzicar ";
    }

    @Override
    public String alijas() {
        return " m ";
    }

    @Override
    public String join() {
        return "";
    }

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

    @Override
    public String koloneZaInsert() {
        return " (Ime, Instrument, Zanr, Kontakt) ";
    }

    @Override
    public String uslov() { 
        return " MuzicarID = " + muzicarID;
    }

    @Override
    public String vrednostiZaInsert() {
         return "'" + ime + "', '" + instrument + "', "
                + "'" + zanr + "', '" + kontakt + "'";
    }

    @Override
    public String vrednostiZaUpdate() {
         return " instrument = '" + instrument + "', zanr = '" + zanr + "', kontakt = '" + kontakt + "' ";
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }
    
    @Override
    public String toString() {
        return ime;
    }
    
}
