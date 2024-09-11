package logika;

import domen.Administrator;
import domen.Bina;
import domen.Izvodjac;
import domen.Koncert;
import domen.Lokacija;
import domen.Muzicar;
import domen.Oprema;
import domen.Sponzor;
import java.util.ArrayList;
import so.bina.SOVratiBine;
import so.izvodjaci.SOVratiIzvodjace;
import so.koncerti.SODodajKoncert;
import so.koncerti.SOIzmeniKoncert;
import so.koncerti.SOObrisiKoncert;
import so.koncerti.SOVratiKoncerte;
import so.login.SOLogin;
import so.lokacija.SOVratiLokacije;
import so.muzicar.SODodajMuzicara;
import so.muzicar.SOIzmeniMuzicara;
import so.muzicar.SOObrisiMuzicara;
import so.muzicar.SOVratiMuzicare;
import so.oprema.SOVratiOpreme;
import so.sponzor.SOVratiSponzore;
import org.json.JSONObject;
import java.io.FileWriter;

/**
 * Klasa Kontroler implementira Singleton obrazac i predstavlja centralni deo poslovne logike sistema.
 * Kroz ovu klasu se izvršavaju različite poslovne operacije, kao što su dodavanje, izmena i brisanje muzičara i koncerata,
 * kao i preuzimanje različitih entiteta iz baze podataka (muzičari, koncerti, lokacije, sponzori, itd.).
 * 
 * @author Ranko
 */
public class Kontroler {

    /** Jedinstvena instanca klase Kontroler (Singleton) */
    private static Kontroler instance;

    /** 
     * Privatni konstruktor za klasu Kontroler. 
     * Ovaj konstruktor je prazan jer je klasa Kontroler implementirana kao Singleton.
     */
    private Kontroler() {
    }

    /**
     * Vraća jedinstvenu instancu klase Kontroler.
     * Ako instanca ne postoji, kreira se nova.
     * 
     * @return Jedinstvena instanca klase Kontroler
     */
    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    /**
     * Metoda za prijavljivanje administratora u sistem.
     * 
     * @param administrator Objekat klase Administrator koji sadrži podatke za prijavu
     * @return Prijavljeni administrator
     * @throws Exception Ako dođe do greške pri prijavljivanju
     */
    public Administrator login(Administrator administrator) throws Exception {
        SOLogin so = new SOLogin();
        so.templateExecute(administrator);
        return so.getUlogovani();
    }

    /**
     * Dodaje novog muzičara u sistem.
     * 
     * @param muzicar Objekat klase Muzicar koji se dodaje u sistem
     * @throws Exception Ako dođe do greške pri dodavanju
     */
    public void dodajMuzicara(Muzicar muzicar) throws Exception {
        (new SODodajMuzicara()).templateExecute(muzicar);
    }

    /**
     * Dodaje novi koncert u sistem.
     * 
     * @param koncert Objekat klase Koncert koji se dodaje u sistem
     * @throws Exception Ako dođe do greške pri dodavanju
     */
    public void dodajKoncert(Koncert koncert) throws Exception {
        (new SODodajKoncert()).templateExecute(koncert);
    }

    /**
     * Briše muzičara iz sistema.
     * 
     * @param muzicar Objekat klase Muzicar koji se briše iz sistema
     * @throws Exception Ako dođe do greške pri brisanju
     */
    public void obrisiMuzicara(Muzicar muzicar) throws Exception {
        (new SOObrisiMuzicara()).templateExecute(muzicar);
    }

    /**
     * Briše koncert iz sistema.
     * 
     * @param koncert Objekat klase Koncert koji se briše iz sistema
     * @throws Exception Ako dođe do greške pri brisanju
     */
    public void obrisiKoncert(Koncert koncert) throws Exception {
        (new SOObrisiKoncert()).templateExecute(koncert);
    }

    /**
     * Ažurira podatke o muzičaru.
     * 
     * @param muzicar Objekat klase Muzicar koji se ažurira
     * @throws Exception Ako dođe do greške pri ažuriranju
     */
    public void izmeniMuzicara(Muzicar muzicar) throws Exception {
        (new SOIzmeniMuzicara()).templateExecute(muzicar);
    }

    /**
     * Ažurira podatke o koncertu.
     * 
     * @param koncert Objekat klase Koncert koji se ažurira
     * @throws Exception Ako dođe do greške pri ažuriranju
     */
    public void izmeniKoncert(Koncert koncert) throws Exception {
        (new SOIzmeniKoncert()).templateExecute(koncert);
    }

    /**
     * Vraća listu svih muzičara iz sistema.
     * 
     * @return Lista objekata klase Muzicar
     * @throws Exception Ako dođe do greške pri preuzimanju muzičara
     */
    public ArrayList<Muzicar> vratiMuzicare() throws Exception {
        SOVratiMuzicare so = new SOVratiMuzicare();
        so.templateExecute(new Muzicar());
        return so.getLista();
    }

    /**
     * Vraća listu svih koncerata iz sistema.
     * 
     * @return Lista objekata klase Koncert
     * @throws Exception Ako dođe do greške pri preuzimanju koncerata
     */
    public ArrayList<Koncert> vratiKoncerte() throws Exception {
        SOVratiKoncerte so = new SOVratiKoncerte();
        so.templateExecute(new Koncert());
        return so.getLista();
    }

    /**
     * Vraća listu svih lokacija iz sistema.
     * 
     * @return Lista objekata klase Lokacija
     * @throws Exception Ako dođe do greške pri preuzimanju lokacija
     */
    public ArrayList<Lokacija> vratiLokacije() throws Exception {
        SOVratiLokacije so = new SOVratiLokacije();
        so.templateExecute(new Lokacija());
        return so.getLista();
    }

    /**
     * Vraća listu svih bina iz sistema.
     * 
     * @return Lista objekata klase Bina
     * @throws Exception Ako dođe do greške pri preuzimanju bina
     */
    public ArrayList<Bina> vratiBine() throws Exception {
        SOVratiBine so = new SOVratiBine();
        so.templateExecute(new Bina());
        return so.getLista();
    }

    /**
     * Vraća listu svih sponzora iz sistema.
     * 
     * @return Lista objekata klase Sponzor
     * @throws Exception Ako dođe do greške pri preuzimanju sponzora
     */
    public ArrayList<Sponzor> vratiSponzore() throws Exception {
        SOVratiSponzore so = new SOVratiSponzore();
        so.templateExecute(new Sponzor());
        return so.getLista();
    }

    /**
     * Vraća listu svih izvođača za zadati koncert.
     * 
     * @param koncert Objekat klase Koncert za koji se preuzimaju izvođači
     * @return Lista objekata klase Izvodjac
     * @throws Exception Ako dođe do greške pri preuzimanju izvođača
     */
    public ArrayList<Izvodjac> vratiIzvodjace(Koncert koncert) throws Exception {
        SOVratiIzvodjace so = new SOVratiIzvodjace();
        
        Izvodjac izvodjac = new Izvodjac();
        izvodjac.setKoncert(koncert);
        
        so.templateExecute(izvodjac);
        return so.getLista();
    }

    /**
     * Vraća listu svih oprema iz sistema.
     * 
     * @return Lista objekata klase Oprema
     * @throws Exception Ako dođe do greške pri preuzimanju opreme
     */
    public ArrayList<Oprema> vratiOpreme() throws Exception {
        SOVratiOpreme so = new SOVratiOpreme();
        so.templateExecute(new Oprema());
        return so.getLista();
    }
    
    /**
     * Čuva podatke o koncertu u JSON fajl.
     *
     * @param koncert Koncert koji treba sačuvati.
     * @throws Exception Ako dođe do greške prilikom pisanja u fajl.
     */
    public void sacuvajKoncertUJSON(Koncert koncert) throws Exception {
        JSONObject jsonKoncert = koncert.toJSON();
        try (FileWriter file = new FileWriter("koncert_" + koncert.getKoncertID() + ".json")) {
            file.write(jsonKoncert.toString(4)); // Formatira JSON sa uvlačenjem od 4 razmaka
        }
    }
}

