package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Korisnik
 */
public class Koncert extends ApstraktniDomenskiObjekat{
    
    private Long koncertID;
    private Date datumPocetka;
    private Date datumZavrsetka;
    private int kapacitetKoncerta;
    private Sponzor sponzor;
    private Bina bina;
    private Administrator administrator;
    private ArrayList<Izvodjac> izvodjaci;

    public Koncert() {
    }

    public Koncert(Long koncertID, Date datumPocetka, Date datumZavrsetka, int kapacitetKoncerta, Sponzor sponzor, Bina bina, Administrator administrator, ArrayList<Izvodjac> izvodjaci) {
        this.koncertID = koncertID;
        this.datumPocetka = datumPocetka;
        this.datumZavrsetka = datumZavrsetka;
        this.kapacitetKoncerta = kapacitetKoncerta;
        this.sponzor = sponzor;
        this.bina = bina;
        this.administrator = administrator;
        this.izvodjaci = izvodjaci;
    }

    public Long getKoncertID() {
        return koncertID;
    }

    public void setKoncertID(Long koncertID) {
        this.koncertID = koncertID;
    }

    public Date getDatumPocetka() {
        return datumPocetka;
    }

    public void setDatumPocetka(Date datumPocetka) {
        this.datumPocetka = datumPocetka;
    }

    public Date getDatumZavrsetka() {
        return datumZavrsetka;
    }

    public void setDatumZavrsetka(Date datumZavrsetka) {
        this.datumZavrsetka = datumZavrsetka;
    }

    public int getKapacitetKoncerta() {
        return kapacitetKoncerta;
    }

    public void setKapacitetKoncerta(int kapacitetKoncerta) {
        this.kapacitetKoncerta = kapacitetKoncerta;
    }

    public Sponzor getSponzor() {
        return sponzor;
    }

    public void setSponzor(Sponzor sponzor) {
        this.sponzor = sponzor;
    }

    public Bina getBina() {
        return bina;
    }

    public void setBina(Bina bina) {
        this.bina = bina;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    public ArrayList<Izvodjac> getIzvodjaci() {
        return izvodjaci;
    }

    public void setIzvodjaci(ArrayList<Izvodjac> izvodjaci) {
        this.izvodjaci = izvodjaci;
    }

    @Override
    public String nazivTabele() {
        return " Koncert ";
    }

    @Override
    public String alijas() {
        return " k ";
    }

    @Override
    public String join() {
        return " JOIN SPONZOR S ON (S.SPONZORID = K.SPONZORID) "
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
            
            Sponzor s = new Sponzor(rs.getLong("SponzorID"),
                    rs.getString("Naziv"), rs.getDouble("IznosKontribucije"),
                    rs.getString("TipSponzorstva"));
            
            Koncert k = new Koncert(rs.getLong("koncertID"), rs.getDate("datumPocetka"),
                    rs.getDate("datumZavrsetka"), rs.getInt("kapacitetKoncerta"),
                    s, b, a, null);

            lista.add(k);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
         return " (DatumPocetka, DatumZavrsetka, KapacitetKoncerta, SponzorID, "
                + "BinaID, AdministratorID) ";
    }

    @Override
    public String uslov() {
         return " KoncertID = " + koncertID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + new java.sql.Date(datumPocetka.getTime()) + "', "
                + "'" + new java.sql.Date(datumZavrsetka.getTime()) + "', "
                + " " + kapacitetKoncerta + ", " + sponzor.getSponzorID()
                + ", " + bina.getBinaID() + ", " + administrator.getAdministratorID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " datumPocetka = '" + new java.sql.Date(datumPocetka.getTime()) + "', "
                + "datumZavrsetka = '" + new java.sql.Date(datumZavrsetka.getTime()) + "', "
                + "sponzorID = " + sponzor.getSponzorID() + ", "
                + "kapacitetKoncerta = " + kapacitetKoncerta;
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }
    
    
    
    
}
