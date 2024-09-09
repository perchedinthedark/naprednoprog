package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Korisnik
 */
public class Izvodjac extends ApstraktniDomenskiObjekat{
    
    private Koncert koncert;
    private int rbIzvodjaca;
    private String napomena;
    private Muzicar muzicar;

    public Izvodjac() {
    }

    public Izvodjac(Koncert koncert, int rbIzvodjaca, String napomena, Muzicar muzicar) {
        this.koncert = koncert;
        this.rbIzvodjaca = rbIzvodjaca;
        this.napomena = napomena;
        this.muzicar = muzicar;
    }

    public Muzicar getMuzicar() {
        return muzicar;
    }

    public void setMuzicar(Muzicar muzicar) {
        this.muzicar = muzicar;
    }

    public Koncert getKoncert() {
        return koncert;
    }

    public void setKoncert(Koncert koncert) {
        this.koncert = koncert;
    }

    public int getRbIzvodjaca() {
        return rbIzvodjaca;
    }

    public void setRbIzvodjaca(int rbIzvodjaca) {
        this.rbIzvodjaca = rbIzvodjaca;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    @Override
    public String nazivTabele() {
        return " Izvodjac ";
    }

    @Override
    public String alijas() {
        return " i ";
    }

    @Override
    public String join() {
        return "JOIN MUZICAR M ON (M.MUZICARID = I.MUZICARID) "
                + "JOIN KONCERT K ON (K.KONCERTID = I.KONCERTID) "
                + "JOIN SPONZOR S ON (S.SPONZORID = K.SPONZORID) "
                + "JOIN BINA B ON (B.BINAID = K.BINAID) "
                + "JOIN LOKACIJA L ON (B.LOKACIJAID = L.LOKACIJAID) "
                + "JOIN OPREMA O ON (B.OPREMAID = O.OPREMAID) "
                + "JOIN ADMINISTRATOR A ON (A.ADMINISTRATORID = K.ADMINISTRATORID)";
    }

    @Override
    public ArrayList<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        
        ArrayList<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            Administrator a = new Administrator(rs.getLong("AdministratorID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("Username"), rs.getString("Password"));
            
            Oprema o = new Oprema(rs.getLong("OpremaID"), rs.getString("Naziv"),
                    rs.getString("Opis"),
                    rs.getDouble("UkupnaCena"));
            
            Lokacija l = new Lokacija(rs.getLong("LokacijaID"),
                    rs.getString("Naziv"), rs.getString("Adresa"),
                    rs.getString("Tip"), rs.getString("Kontakt"),
            rs.getString("Vlasnik"));

            Bina b = new Bina(rs.getLong("BinaID"),
                    rs.getString("Naziv"),
            rs.getInt("Kapacitet"), l, o);
            
            Sponzor s = new Sponzor(rs.getLong("SponzorID"),
                    rs.getString("Naziv"), rs.getDouble("IznosKontribucije"),
                    rs.getString("TipSponzorstva"));
            
             System.out.println(s.getNaziv() + b.getNaziv() + o.getNaziv() + l.getNaziv());
            
            Koncert k = new Koncert(rs.getLong("KoncertID"), rs.getDate("DatumPocetka"),
                    rs.getDate("DatumZavrsetka"), rs.getInt("KapacitetKoncerta"),
                    s, b, a, null);
            
            Muzicar m = new Muzicar(rs.getLong("MuzicarID"),
                    rs.getString("Ime"), rs.getString("Instrument"),
                    rs.getString("Zanr"), rs.getString("Kontakt"));
            
            Izvodjac i = new Izvodjac(k, rs.getInt("RbIzvodjaca"), rs.getString("Napomena"), m);


            lista.add(i);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (KoncertID, RbIzvodjaca, Napomena, MuzicarID) ";
    }

    @Override
    public String uslov() {
        return " KoncertID = " + koncert.getKoncertID();
    }

    @Override
    public String vrednostiZaInsert() {
        return " " + koncert.getKoncertID()+ ", " + rbIzvodjaca + ", "
                + "'" + napomena + "', " + muzicar.getMuzicarID() + " ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslovZaSelect() {
         return " WHERE K.KONCERTID = " + koncert.getKoncertID();
    }
    
    
    
}
