package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Korisnik
 */
public class Bina extends ApstraktniDomenskiObjekat{
    
    private Long binaID;
    private String naziv;
    private int kapacitet;
    private Lokacija lokacija;
    private Oprema oprema;

    public Bina() {
    }

    public Bina(Long binaID, String naziv, int kapacitet, Lokacija lokacija, Oprema oprema) {
        this.binaID = binaID;
        this.naziv = naziv;
        this.kapacitet = kapacitet;
        this.lokacija = lokacija;
        this.oprema = oprema;
    }
    
    

    public Lokacija getLokacija() {
        return lokacija;
    }

    public void setLokacija(Lokacija lokacija) {
        this.lokacija = lokacija;
    }

    public Long getBinaID() {
        return binaID;
    }

    public void setBinaID(Long binaID) {
        this.binaID = binaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getKapacitet() {
        return kapacitet;
    }

    public void setKapacitet(int kapacitet) {
        this.kapacitet = kapacitet;
    }

    @Override
    public String nazivTabele() {
        return " Bina ";
    }

    @Override
    public String alijas() {
        return " b ";
    }

    @Override
    public String join() {
        return " JOIN LOKACIJA L ON (B.LOKACIJAID = L.LOKACIJAID) "
                + "JOIN OPREMA O ON (B.OPREMAID = O.OPREMAID) ";
    }

    @Override
    public ArrayList<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {

            Oprema o = new Oprema(rs.getLong("opremaID"), rs.getString("naziv"),
                    rs.getString("opis"),
                    rs.getDouble("ukupnaCena"));
            
            Lokacija l = new Lokacija(rs.getLong("LokacijaID"),
                    rs.getString("Naziv"), rs.getString("Adresa"),
                    rs.getString("Tip"), rs.getString("Kontakt"),
            rs.getString("Vlasnik"));

            Bina b = new Bina(rs.getLong("BinaID"),
                    rs.getString("naziv"),
            rs.getInt("kapacitet"), l, o);

            lista.add(b);
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
        return " binaID = " + binaID;
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

    public Oprema getOprema() {
        return oprema;
    }

    public void setOprema(Oprema oprema) {
        this.oprema = oprema;
    }
    
    @Override
    public String toString() {
        return naziv;
    }
    
}
