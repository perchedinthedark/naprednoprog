package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Korisnik
 */
public class Sponzor extends ApstraktniDomenskiObjekat{
    
    private Long sponzorID;
    private String naziv;
    private double iznosKontribucije;
    private String tipSponzorstva;

    public Sponzor() {
    }

    public Sponzor(Long sponzorID, String naziv, double iznosKontribucije, String tipSponzorstva) {
        this.sponzorID = sponzorID;
        this.iznosKontribucije = iznosKontribucije;
        this.tipSponzorstva = tipSponzorstva;
        this.naziv = naziv;
    }

    public String getTipSponzorstva() {
        return tipSponzorstva;
    }

    public void setTipSponzorstva(String tipSponzorstva) {
        this.tipSponzorstva = tipSponzorstva;
    }

    public Long getSponzorID() {
        return sponzorID;
    }

    public void setSponzorID(Long sponzorID) {
        this.sponzorID = sponzorID;
    }

    public double getIznosKontribucije() {
        return iznosKontribucije;
    }

    public void setIznosKontribucije(double iznosKontribucije) {
        this.iznosKontribucije = iznosKontribucije;
    }
    
    

    @Override
    public String nazivTabele() {
        return " sponzor ";
    }

    @Override
    public String alijas() {
        return " s ";
    }

    @Override
    public String join() {
        return "";
    }

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

    @Override
    public String koloneZaInsert() {
        return "";    }

    @Override
    public String uslov() {
        return " SponzorID = " + sponzorID;
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

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    
    @Override
    public String toString() {
        return naziv;
    }
    
}
