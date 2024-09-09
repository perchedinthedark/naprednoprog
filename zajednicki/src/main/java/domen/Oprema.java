package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Korisnik
 */
public class Oprema extends ApstraktniDomenskiObjekat{
    
    private Long opremaID;
    private String naziv;
    private String opis;
    private double ukupnaCena;

    public Oprema() {
    }

    public Oprema(Long opremaID, String naziv, String opis, double cena) {
        this.opremaID = opremaID;
        this.naziv = naziv;
        this.opis = opis;
        this.ukupnaCena = cena;
    }


    public Long getOpremaID() {
        return opremaID;
    }

    public void setOpremaID(Long opremaID) {
        this.opremaID = opremaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(int cena) {
        this.ukupnaCena = cena;
    }

    @Override
    public String nazivTabele() {
        return " Oprema ";
    }

    @Override
    public String alijas() {
        
        return " o ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {ArrayList<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
          

            Oprema o = new Oprema(rs.getLong("opremaID"), rs.getString("naziv"),
                    rs.getString("opis"),
                    rs.getDouble("ukupnaCena"));

            lista.add(o);
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
        return " opremaID = " + opremaID;
    }

    @Override
    public String vrednostiZaInsert() {

        return "";    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }
    
    
    
}