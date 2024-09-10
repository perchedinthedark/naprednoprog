package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Klasa koja predstavlja entitet Izvođač u okviru koncerta. 
 * Izvođač može biti muzičar, a svaki izvođač ima napomenu, redni broj izvođača na koncertu, i povezan je sa koncertom.
 * Nasleđuje osnovne metode iz {@code ApstraktniDomenskiObjekat}.
 * 
 * @author Ranko
 */
public class Izvodjac extends ApstraktniDomenskiObjekat {

    /**
     * Koncert na kojem izvođač nastupa.
     */
    private Koncert koncert;
    
    /**
     * Redni broj izvođača na koncertu.
     */
    private int rbIzvodjaca;
    
    /**
     * Napomena koja se odnosi na izvođača (moguće specifičnosti izvođenja).
     */
    private String napomena;
    
    /**
     * Muzičar koji je izvođač na koncertu.
     */
    private Muzicar muzicar;

    /**
     * Prazan konstruktor za kreiranje nove instance klase Izvodjac.
     */
    public Izvodjac() {
    }

    /**
     * Konstruktor koji postavlja sve atribute klase Izvodjac.
     * 
     * @param koncert Koncert na kojem izvođač nastupa.
     * @param rbIzvodjaca Redni broj izvođača na koncertu.
     * @param napomena Napomena o izvođaču.
     * @param muzicar Muzičar koji nastupa kao izvođač.
     */
    public Izvodjac(Koncert koncert, int rbIzvodjaca, String napomena, Muzicar muzicar) {
        this.koncert = koncert;
        this.rbIzvodjaca = rbIzvodjaca;
        this.napomena = napomena;
        this.muzicar = muzicar;
    }

    /**
     * Vraća muzičara povezanog sa izvođačem.
     * 
     * @return Muzičar koji nastupa kao izvođač.
     */
    public Muzicar getMuzicar() {
        return muzicar;
    }

    /**
     * Postavlja muzičara za izvođača.
     * 
     * @param muzicar Novi muzičar izvođača.
     */
    public void setMuzicar(Muzicar muzicar) {
        this.muzicar = muzicar;
    }

    /**
     * Vraća koncert na kojem izvođač nastupa.
     * 
     * @return Koncert na kojem izvođač nastupa.
     */
    public Koncert getKoncert() {
        return koncert;
    }

    /**
     * Postavlja koncert za izvođača.
     * 
     * @param koncert Novi koncert izvođača.
     */
    public void setKoncert(Koncert koncert) {
        this.koncert = koncert;
    }

    /**
     * Vraća redni broj izvođača na koncertu.
     * 
     * @return Redni broj izvođača.
     */
    public int getRbIzvodjaca() {
        return rbIzvodjaca;
    }

    /**
     * Postavlja redni broj izvođača na koncertu.
     * 
     * @param rbIzvodjaca Novi redni broj izvođača.
     */
    public void setRbIzvodjaca(int rbIzvodjaca) {
        this.rbIzvodjaca = rbIzvodjaca;
    }

    /**
     * Vraća napomenu izvođača.
     * 
     * @return Napomena izvođača.
     */
    public String getNapomena() {
        return napomena;
    }

    /**
     * Postavlja napomenu za izvođača.
     * 
     * @param napomena Nova napomena izvođača.
     */
    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    /**
     * Vraća naziv tabele povezane sa izvođačem.
     * 
     * @return Naziv tabele u bazi podataka.
     */
    @Override
    public String nazivTabele() {
        return " Izvodjac ";
    }

    /**
     * Vraća alijas za tabelu izvođača.
     * 
     * @return Alijas za SQL upite.
     */
    @Override
    public String alijas() {
        return " i ";
    }

    /**
     * Definiše SQL JOIN za povezivanje sa tabelama Muzičar, Koncert, Sponzor, Bina, Lokacija, Oprema i Administrator.
     * 
     * @return SQL JOIN klauzula za povezivanje sa tabelama koje su vezane za izvođača.
     */
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

    /**
     * Pretvara {@code ResultSet} iz baze podataka u listu Izvođač objekata.
     * 
     * @param rs {@code ResultSet} sa podacima iz baze
     * @return Lista Izvođač objekata
     * @throws SQLException Ako dođe do greške tokom obrade {@code ResultSet}-a
     */
    @Override
    public ArrayList<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            Administrator a = new Administrator(rs.getLong("AdministratorID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("Username"), rs.getString("Password"));
            
            Oprema o = new Oprema(rs.getLong("OpremaID"), rs.getString("Naziv"),
                    rs.getString("Opis"), rs.getDouble("UkupnaCena"));
            
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

    /**
     * Vraća nazive kolona za umetanje u bazu podataka.
     * 
     * @return SQL kolone za umetanje.
     */
    @Override
    public String koloneZaInsert() {
        return " (KoncertID, RbIzvodjaca, Napomena, MuzicarID) ";
    }

    /**
     * Vraća SQL WHERE uslov za pretragu izvođača po identifikatoru koncerta.
     * 
     * @return SQL WHERE klauzula za pretragu po KoncertID.
     */
    @Override
    public String uslov() {
        return " KoncertID = " + koncert.getKoncertID();
    }

    /**
     * Vraća SQL vrednosti za umetanje u bazu podataka.
     * 
     * @return SQL vrednosti za umetanje.
     */
    @Override
    public String vrednostiZaInsert() {
        return " " + koncert.getKoncertID() + ", " + rbIzvodjaca + ", "
                + "'" + napomena + "', " + muzicar.getMuzicarID() + " ";
    }

    /**
     * Vraća SQL vrednosti za ažuriranje izvođača u bazi podataka (trenutno nije implementirano).
     * 
     * @return Prazan string, pošto ažuriranje nije definisano.
     */
    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    /**
     * Vraća SQL uslov za selektovanje podataka iz baze podataka za izvođača određenog koncerta.
     * 
     * @return SQL WHERE klauzula za selektovanje izvođača po koncertu.
     */
    @Override
    public String uslovZaSelect() {
         return " WHERE K.KONCERTID = " + koncert.getKoncertID();
    }
}

