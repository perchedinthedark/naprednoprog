package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 * Klasa Koncert predstavlja entitet koncerta sa svim relevantnim informacijama 
 * o koncertu, uključujući datum početka, datum završetka, kapacitet, sponzora, 
 * binu, administratora i listu izvođača.
 * Nasleđuje klasu ApstraktniDomenskiObjekat koja pruža osnovne metode za rad 
 * sa bazom podataka.
 * 
 * @author Ranko
 */
public class Koncert extends ApstraktniDomenskiObjekat {

    /** Jedinstveni identifikator koncerta */
    private Long koncertID;

    /** Datum i vreme početka koncerta */
    private Date datumPocetka;

    /** Datum i vreme završetka koncerta */
    private Date datumZavrsetka;

    /** Kapacitet koncerta (maksimalan broj posetilaca) */
    private int kapacitetKoncerta;

    /** Sponzor koji finansira koncert */
    private Sponzor sponzor;

    /** Bina na kojoj će se koncert održati */
    private Bina bina;

    /** Administrator koji upravlja koncertom */
    private Administrator administrator;

    /** Lista izvođača koji će nastupati na koncertu */
    private ArrayList<Izvodjac> izvodjaci;

    /** 
     * Podrazumevani konstruktor koji inicijalizuje prazan objekat klase Koncert.
     */
    public Koncert() {
    }

    /**
     * Parametrizovani konstruktor koji postavlja sve atribute objekta Koncert.
     * 
     * @param koncertID Jedinstveni identifikator koncerta
     * @param datumPocetka Datum i vreme početka koncerta
     * @param datumZavrsetka Datum i vreme završetka koncerta
     * @param kapacitetKoncerta Kapacitet koncerta
     * @param sponzor Sponzor koncerta
     * @param bina Bina na kojoj će se koncert održati
     * @param administrator Administrator koncerta
     * @param izvodjaci Lista izvođača na koncertu
     */
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

    /** 
     * Vraća identifikator koncerta.
     * 
     * @return Jedinstveni identifikator koncerta
     */
    public Long getKoncertID() {
        return koncertID;
    }

    /** 
     * Postavlja identifikator koncerta.
     * 
     * @param koncertID Jedinstveni identifikator koncerta
     */
    public void setKoncertID(Long koncertID) {
        this.koncertID = koncertID;
    }

    /** 
     * Vraća datum početka koncerta.
     * 
     * @return Datum i vreme početka koncerta
     */
    public Date getDatumPocetka() {
        return datumPocetka;
    }

    /** 
     * Postavlja datum početka koncerta.
     * 
     * @param datumPocetka Datum i vreme početka koncerta
     */
    public void setDatumPocetka(Date datumPocetka) {
        this.datumPocetka = datumPocetka;
    }

    /** 
     * Vraća datum završetka koncerta.
     * 
     * @return Datum i vreme završetka koncerta
     */
    public Date getDatumZavrsetka() {
        return datumZavrsetka;
    }

    /** 
     * Postavlja datum završetka koncerta.
     * 
     * @param datumZavrsetka Datum i vreme završetka koncerta
     */
    public void setDatumZavrsetka(Date datumZavrsetka) {
        this.datumZavrsetka = datumZavrsetka;
    }

    /** 
     * Vraća kapacitet koncerta.
     * 
     * @return Maksimalan broj posetilaca na koncertu
     */
    public int getKapacitetKoncerta() {
        return kapacitetKoncerta;
    }

    /** 
     * Postavlja kapacitet koncerta.
     * 
     * @param kapacitetKoncerta Maksimalan broj posetilaca na koncertu
     */
    public void setKapacitetKoncerta(int kapacitetKoncerta) {
        this.kapacitetKoncerta = kapacitetKoncerta;
    }

    /** 
     * Vraća sponzora koncerta.
     * 
     * @return Sponzor koncerta
     */
    public Sponzor getSponzor() {
        return sponzor;
    }

    /** 
     * Postavlja sponzora koncerta.
     * 
     * @param sponzor Sponzor koncerta
     */
    public void setSponzor(Sponzor sponzor) {
        this.sponzor = sponzor;
    }

    /** 
     * Vraća binu na kojoj će se održati koncert.
     * 
     * @return Bina na kojoj će se koncert održati
     */
    public Bina getBina() {
        return bina;
    }

    /** 
     * Postavlja binu na kojoj će se održati koncert.
     * 
     * @param bina Bina na kojoj će se koncert održati
     */
    public void setBina(Bina bina) {
        this.bina = bina;
    }

    /** 
     * Vraća administratora koncerta.
     * 
     * @return Administrator koncerta
     */
    public Administrator getAdministrator() {
        return administrator;
    }

    /** 
     * Postavlja administratora koncerta.
     * 
     * @param administrator Administrator koncerta
     */
    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    /** 
     * Vraća listu izvođača na koncertu.
     * 
     * @return Lista izvođača
     */
    public ArrayList<Izvodjac> getIzvodjaci() {
        return izvodjaci;
    }

    /** 
     * Postavlja listu izvođača na koncertu.
     * 
     * @param izvodjaci Lista izvođača
     */
    public void setIzvodjaci(ArrayList<Izvodjac> izvodjaci) {
        this.izvodjaci = izvodjaci;
    }

    /** 
     * Vraća naziv tabele u bazi podataka koja odgovara entitetu Koncert.
     * 
     * @return Naziv tabele
     */
    @Override
    public String nazivTabele() {
        return " Koncert ";
    }

    /** 
     * Vraća alias koji se koristi za entitet Koncert u SQL upitima.
     * 
     * @return Alias za koncert
     */
    @Override
    public String alijas() {
        return " k ";
    }

    /** 
     * Vraća SQL JOIN upit za povezivanje sa drugim entitetima u bazi podataka.
     * 
     * @return SQL JOIN upit
     */
    @Override
    public String join() {
        return " JOIN SPONZOR S ON (S.SPONZORID = K.SPONZORID) "
                + "JOIN BINA B ON (B.BINAID = K.BINAID) "
                + "JOIN LOKACIJA L ON (B.LOKACIJAID = L.LOKACIJAID) "
                + "JOIN OPREMA O ON (B.OPREMAID = O.OPREMAID) "
                + "JOIN ADMINISTRATOR A ON (A.ADMINISTRATORID = K.ADMINISTRATORID)";
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

    /** 
     * Vraća nazive kolona koje se koriste za INSERT upit u bazi podataka.
     * 
     * @return Nazivi kolona za INSERT upit
     */
    @Override
    public String koloneZaInsert() {
        return " (DatumPocetka, DatumZavrsetka, KapacitetKoncerta, SponzorID, "
                + "BinaID, AdministratorID) ";
    }

    /** 
     * Vraća uslov za SQL upit koji se koristi za identifikaciju specifičnog koncerta.
     * 
     * @return SQL uslov
     */
    @Override
    public String uslov() {
         return " KoncertID = " + koncertID;
    }

    /** 
     * Vraća vrednosti koje će biti umetnute u bazu podataka prilikom INSERT upita.
     * 
     * @return Vrednosti za INSERT upit
     */
    @Override
    public String vrednostiZaInsert() {
        return "'" + new java.sql.Date(datumPocetka.getTime()) + "', "
                + "'" + new java.sql.Date(datumZavrsetka.getTime()) + "', "
                + " " + kapacitetKoncerta + ", " + sponzor.getSponzorID()
                + ", " + bina.getBinaID() + ", " + administrator.getAdministratorID();
    }

    /** 
     * Vraća vrednosti koje će biti ažurirane u bazi podataka prilikom UPDATE upita.
     * 
     * @return Vrednosti za UPDATE upit
     */
    @Override
    public String vrednostiZaUpdate() {
        return " datumPocetka = '" + new java.sql.Date(datumPocetka.getTime()) + "', "
                + "datumZavrsetka = '" + new java.sql.Date(datumZavrsetka.getTime()) + "', "
                + "sponzorID = " + sponzor.getSponzorID() + ", "
                + "kapacitetKoncerta = " + kapacitetKoncerta;
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
     * Konvertuje ovaj objekat koncerta u JSON reprezentaciju.
     *
     * @return {@link JSONObject} koji predstavlja ovaj koncert, uključujući detalje o koncertu i izvođače.
     */
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("koncertID", koncertID);
        json.put("datumPocetka", datumPocetka.toString());
        json.put("datumZavrsetka", datumZavrsetka.toString());
        json.put("kapacitet", kapacitetKoncerta);
        json.put("sponzor", sponzor.getNaziv());
        json.put("bina", bina.getNaziv());

        JSONArray izvodjaciArray = new JSONArray();
        for (Izvodjac izvodjac : izvodjaci) {
            JSONObject izvodjacJSON = new JSONObject();
            izvodjacJSON.put("ime", izvodjac.getMuzicar().getIme());
            izvodjacJSON.put("instrument", izvodjac.getMuzicar().getInstrument());
            izvodjaciArray.put(izvodjacJSON);
        }
        json.put("izvodjaci", izvodjaciArray);
        return json;
    }
}
