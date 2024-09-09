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

/**
 *
 * @author Ana
 */
public class Kontroler {

    private static Kontroler instance;

    private Kontroler() {
    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public Administrator login(Administrator administrator) throws Exception {
        SOLogin so = new SOLogin();
        so.templateExecute(administrator);
        return so.getUlogovani();
    }

    public void dodajMuzicara(Muzicar muzicar) throws Exception {
        (new SODodajMuzicara()).templateExecute(muzicar);
    }

    public void dodajKoncert(Koncert koncert) throws Exception {
        (new SODodajKoncert()).templateExecute(koncert);
    }

    public void obrisiMuzicara(Muzicar muzicar) throws Exception {
        (new SOObrisiMuzicara()).templateExecute(muzicar);
    }

    public void obrisiKoncert(Koncert koncert) throws Exception {
        (new SOObrisiKoncert()).templateExecute(koncert);
    }

    public void izmeniMuzicara(Muzicar muzicar) throws Exception {
        (new SOIzmeniMuzicara()).templateExecute(muzicar);
    }

    public void izmeniKoncert(Koncert koncert) throws Exception {
        (new SOIzmeniKoncert()).templateExecute(koncert);
    }

    public ArrayList<Muzicar> vratiMuzicare() throws Exception {
        SOVratiMuzicare so = new SOVratiMuzicare();
        so.templateExecute(new Muzicar());
        return so.getLista();
    }

    public ArrayList<Koncert> vratiKoncerte() throws Exception {
        SOVratiKoncerte so = new SOVratiKoncerte();
        so.templateExecute(new Koncert());
        return so.getLista();
    }

    public ArrayList<Lokacija> vratiLokacije() throws Exception {
        SOVratiLokacije so = new SOVratiLokacije();
        so.templateExecute(new Lokacija());
        return so.getLista();
    }

    public ArrayList<Bina> vratiBine() throws Exception {
        SOVratiBine so = new SOVratiBine();
        so.templateExecute(new Bina());
        return so.getLista();
    }

    public ArrayList<Sponzor> vratiSponzore() throws Exception {
        SOVratiSponzore so = new SOVratiSponzore();
        so.templateExecute(new Sponzor());
        return so.getLista();
    }

    public ArrayList<Izvodjac> vratiIzvodjace(Koncert koncert) throws Exception {
        SOVratiIzvodjace so = new SOVratiIzvodjace();
        
        Izvodjac izvodjac = new Izvodjac();
        izvodjac.setKoncert(koncert);
        
        so.templateExecute(izvodjac);
        return so.getLista();
    }

    public ArrayList<Oprema> vratiOpreme() throws Exception {
        SOVratiOpreme so = new SOVratiOpreme();
        so.templateExecute(new Oprema());
        return so.getLista();
    }

}
