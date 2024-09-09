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

public class Komunikacija {

    private static Komunikacija instance;

    private Komunikacija() {
    }

    public static Komunikacija getInstance() {
        if (instance == null) {
            instance = new Komunikacija();
        }
        return instance;
    }

    public Administrator login(Administrator administrator) throws Exception {
        return (Administrator) posaljiZahtev(Operacije.LOGIN, administrator);
    }

    public void dodajMuzicara(Muzicar muzicar) throws Exception {
        posaljiZahtev(Operacije.DODAJ_MUZICARA, muzicar);
    }

    public void dodajKoncert(Koncert koncert) throws Exception {
        posaljiZahtev(Operacije.DODAJ_KONCERT, koncert);
    }

    public void obrisiMuzicara(Muzicar muzicar) throws Exception {
        posaljiZahtev(Operacije.OBRISI_MUZICARA, muzicar);
    }

    public void obrisiKoncert(Koncert koncert) throws Exception {
        posaljiZahtev(Operacije.OBRISI_KONCERT, koncert);
    }

    public void izmeniMuzicara(Muzicar muzicar) throws Exception {
        posaljiZahtev(Operacije.IZMENI_MUZICARA, muzicar);
    }

    public void izmeniKoncert(Koncert koncert) throws Exception {
        posaljiZahtev(Operacije.IZMENI_KONCERT, koncert);
    }

    public ArrayList<Muzicar> vratiMuzicare() throws Exception {
        return (ArrayList<Muzicar>) posaljiZahtev(Operacije.VRATI_MUZICARE, null);
    }

    public ArrayList<Koncert> vratiKoncerte() throws Exception {
        return (ArrayList<Koncert>) posaljiZahtev(Operacije.VRATI_KONCERTE, null);
    }

    public ArrayList<Bina> vratiBine() throws Exception {
        return (ArrayList<Bina>) posaljiZahtev(Operacije.VRATI_BINE, null);
    }

    public ArrayList<Lokacija> vratiLokacije() throws Exception {
        return (ArrayList<Lokacija>) posaljiZahtev(Operacije.VRATI_LOKACIJE, null);
    }
    
    public ArrayList<Oprema> vratiOpreme() throws Exception {
        return (ArrayList<Oprema>) posaljiZahtev(Operacije.VRATI_OPREME, null);
    }

    public ArrayList<Sponzor> vratiSponzore() throws Exception {
        return (ArrayList<Sponzor>) posaljiZahtev(Operacije.VRATI_SPONZORE, null);
    }

    public ArrayList<Izvodjac> vratiIzvodjace(Koncert koncert) throws Exception {
        return (ArrayList<Izvodjac>) posaljiZahtev(Operacije.VRATI_IZVODJACE, koncert);
    }

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
