package kontroler;

import domen.Administrator;
import domen.Bina;
import domen.Izvodjac;
import domen.Koncert;
import domen.Lokacija;
import domen.Muzicar;
import domen.Oprema;
import domen.Sponzor;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import sesija.Sesija;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;
import konstante.StatusServerskogOdgovora;
import konstante.Operacije;

/**
 * Klasa Komunikacija koristi Singleton obrazac za slanje zahteva serveru i primanje odgovora.
 * Ova klasa omogućava komunikaciju između klijenta i servera, slanje zahteva i primanje odgovora.
 * 
 * @author Ranko
 */
public class Komunikacija {

    /** Jedinstvena instanca klase Komunikacija (Singleton) */
    private static Komunikacija instance;

    /**
     * Privatni konstruktor za klasu Komunikacija.
     */
    private Komunikacija() {
    }

    /**
     * Vraća jedinstvenu instancu klase Komunikacija.
     * Ako instanca ne postoji, kreira se nova.
     * 
     * @return Jedinstvena instanca klase Komunikacija
     */
    public static Komunikacija getInstance() {
        if (instance == null) {
            instance = new Komunikacija();
        }
        return instance;
    }

    /**
     * Šalje zahtev za prijavljivanje administratora i vraća prijavljenog administratora.
     * 
     * @param administrator Administrator sa kredencijalima za prijavu
     * @return Prijavljeni administrator
     * @throws Exception Ako prijava nije uspešna
     */
    public Administrator login(Administrator administrator) throws Exception {
        return (Administrator) posaljiZahtev(Operacije.LOGIN, administrator);
    }

    /**
     * Šalje zahtev za dodavanje novog muzičara.
     * 
     * @param muzicar Muzičar koji se dodaje
     * @throws Exception Ako dodavanje nije uspešno
     */
    public void dodajMuzicara(Muzicar muzicar) throws Exception {
        posaljiZahtev(Operacije.DODAJ_MUZICARA, muzicar);
    }

    /**
     * Šalje zahtev za dodavanje novog koncerta.
     * 
     * @param koncert Koncert koji se dodaje
     * @throws Exception Ako dodavanje nije uspešno
     */
    public void dodajKoncert(Koncert koncert) throws Exception {
        posaljiZahtev(Operacije.DODAJ_KONCERT, koncert);
    }

    /**
     * Šalje zahtev za brisanje muzičara.
     * 
     * @param muzicar Muzičar koji se briše
     * @throws Exception Ako brisanje nije uspešno
     */
    public void obrisiMuzicara(Muzicar muzicar) throws Exception {
        posaljiZahtev(Operacije.OBRISI_MUZICARA, muzicar);
    }

    /**
     * Šalje zahtev za brisanje koncerta.
     * 
     * @param koncert Koncert koji se briše
     * @throws Exception Ako brisanje nije uspešno
     */
    public void obrisiKoncert(Koncert koncert) throws Exception {
        posaljiZahtev(Operacije.OBRISI_KONCERT, koncert);
    }

    /**
     * Šalje zahtev za izmenu muzičara.
     * 
     * @param muzicar Muzičar koji se menja
     * @throws Exception Ako izmena nije uspešna
     */
    public void izmeniMuzicara(Muzicar muzicar) throws Exception {
        posaljiZahtev(Operacije.IZMENI_MUZICARA, muzicar);
    }

    /**
     * Šalje zahtev za izmenu koncerta.
     * 
     * @param koncert Koncert koji se menja
     * @throws Exception Ako izmena nije uspešna
     */
    public void izmeniKoncert(Koncert koncert) throws Exception {
        posaljiZahtev(Operacije.IZMENI_KONCERT, koncert);
    }

    /**
     * Vraća listu muzičara iz baze podataka.
     * 
     * @return Lista muzičara
     * @throws Exception Ako preuzimanje nije uspešno
     */
    public ArrayList<Muzicar> vratiMuzicare() throws Exception {
        return (ArrayList<Muzicar>) posaljiZahtev(Operacije.VRATI_MUZICARE, null);
    }

    /**
     * Vraća listu koncerata iz baze podataka.
     * 
     * @return Lista koncerata
     * @throws Exception Ako preuzimanje nije uspešno
     */
    public ArrayList<Koncert> vratiKoncerte() throws Exception {
        return (ArrayList<Koncert>) posaljiZahtev(Operacije.VRATI_KONCERTE, null);
    }

    /**
     * Vraća listu bina iz baze podataka.
     * 
     * @return Lista bina
     * @throws Exception Ako preuzimanje nije uspešno
     */
    public ArrayList<Bina> vratiBine() throws Exception {
        return (ArrayList<Bina>) posaljiZahtev(Operacije.VRATI_BINE, null);
    }

    /**
     * Vraća listu lokacija iz baze podataka.
     * 
     * @return Lista lokacija
     * @throws Exception Ako preuzimanje nije uspešno
     */
    public ArrayList<Lokacija> vratiLokacije() throws Exception {
        return (ArrayList<Lokacija>) posaljiZahtev(Operacije.VRATI_LOKACIJE, null);
    }

    /**
     * Vraća listu opreme iz baze podataka.
     * 
     * @return Lista opreme
     * @throws Exception Ako preuzimanje nije uspešno
     */
    public ArrayList<Oprema> vratiOpreme() throws Exception {
        return (ArrayList<Oprema>) posaljiZahtev(Operacije.VRATI_OPREME, null);
    }

    /**
     * Vraća listu sponzora iz baze podataka.
     * 
     * @return Lista sponzora
     * @throws Exception Ako preuzimanje nije uspešno
     */
    public ArrayList<Sponzor> vratiSponzore() throws Exception {
        return (ArrayList<Sponzor>) posaljiZahtev(Operacije.VRATI_SPONZORE, null);
    }

    /**
     * Vraća listu izvođača za određeni koncert.
     * 
     * @param koncert Koncert za koji se preuzimaju izvođači
     * @return Lista izvođača
     * @throws Exception Ako preuzimanje nije uspešno
     */
    public ArrayList<Izvodjac> vratiIzvodjace(Koncert koncert) throws Exception {
        return (ArrayList<Izvodjac>) posaljiZahtev(Operacije.VRATI_IZVODJACE, koncert);
    }
    
    
    /**
     * Šalje koncert serveru da ga sačuva u JSON fajl.
     *
     * @param koncert Koncert koji treba sačuvati.
     * @throws Exception Ako dođe do greške prilikom komunikacije sa serverom.
     */
    public void sacuvajKoncertUJSON(Koncert koncert) throws Exception {
        posaljiZahtev(Operacije.SACUVAJ_KONCERT_JSON, koncert);
    }

    /**
     * Šalje zahtev serveru i vraća odgovor.
     * 
     * @param operacija Operacija koja se šalje serveru
     * @param parametar Parametar koji se prosleđuje serveru
     * @return Odgovor od servera
     * @throws Exception Ako je server vratio grešku
     */
    private Object posaljiZahtev(int operacija, Object parametar) throws Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev(operacija, parametar);

        ObjectOutputStream out = new ObjectOutputStream(Sesija.getInstance().getSocket().getOutputStream());
        out.writeObject(kz);

        ObjectInputStream in = new ObjectInputStream(Sesija.getInstance().getSocket().getInputStream());
        ServerskiOdgovor serverskiOdgovor = (ServerskiOdgovor) in.readObject();

        if (serverskiOdgovor.getStatusOdgovora().equals(StatusServerskogOdgovora.Error)) {
            throw serverskiOdgovor.getExc();
        } else {
            return serverskiOdgovor.getOdgovor();
        }
    }
}