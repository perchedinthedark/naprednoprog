package niti;

import logika.Kontroler;
import domen.Administrator;
import domen.Koncert;
import domen.Muzicar;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;
import konstante.StatusServerskogOdgovora;
import konstante.Operacije;

/**
 * Klasa ObradaKlijentskihZahteva predstavlja nit koja obrađuje zahteve klijenata servera.
 * Svaki klijent se povezuje putem soketa, a svaki zahtev klijenta se obrađuje u posebnoj niti.
 * Ova klasa koristi instancu klase Kontroler za izvršenje poslovnih operacija i šalje odgovor klijentu.
 * 
 * @author Ranko
 */
public class ObradaKlijentskihZahteva extends Thread {

    /** Soket putem kog se klijent povezuje na server */
    private Socket socket;

    /**
     * Konstruktor klase ObradaKlijentskihZahteva koji postavlja soket.
     * 
     * @param socket Soket za komunikaciju sa klijentom
     */
    ObradaKlijentskihZahteva(Socket socket) {
        this.socket = socket;
    }

    /**
     * Metoda koja se izvršava kada se pokrene nit. Prima i obrađuje klijentske zahteve sve dok soket nije zatvoren.
     */
    @Override
    public void run() {
        try {
            while (!socket.isClosed()) {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                KlijentskiZahtev kz = (KlijentskiZahtev) in.readObject();
                ServerskiOdgovor serverskiOdgovor = handleRequest(kz);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(serverskiOdgovor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Obrađuje klijentski zahtev na osnovu operacije i vraća serverski odgovor.
     * 
     * @param kz Klijentski zahtev
     * @return ServerskiOdgovor koji sadrži rezultat obrade zahteva
     */
    private ServerskiOdgovor handleRequest(KlijentskiZahtev kz) {
        ServerskiOdgovor serverskiOdgovor = new ServerskiOdgovor(null, null, StatusServerskogOdgovora.Success);
        try {
            switch (kz.getOperacija()) {
                case Operacije.DODAJ_KONCERT:
                    Kontroler.getInstance().dodajKoncert((Koncert) kz.getParametar());
                    break;
                case Operacije.DODAJ_MUZICARA:
                    Kontroler.getInstance().dodajMuzicara((Muzicar) kz.getParametar());
                    break;
                case Operacije.OBRISI_KONCERT:
                    Kontroler.getInstance().obrisiKoncert((Koncert) kz.getParametar());
                    break;
                case Operacije.OBRISI_MUZICARA:
                    Kontroler.getInstance().obrisiMuzicara((Muzicar) kz.getParametar());
                    break;
                case Operacije.IZMENI_KONCERT:
                    Kontroler.getInstance().izmeniKoncert((Koncert) kz.getParametar());
                    break;
                case Operacije.IZMENI_MUZICARA:
                    Kontroler.getInstance().izmeniMuzicara((Muzicar) kz.getParametar());
                    break;
                case Operacije.VRATI_KONCERTE:
                    serverskiOdgovor.setOdgovor(Kontroler.getInstance().vratiKoncerte());
                    break;
                case Operacije.VRATI_LOKACIJE:
                    serverskiOdgovor.setOdgovor(Kontroler.getInstance().vratiLokacije());
                    break;
                case Operacije.VRATI_BINE:
                    serverskiOdgovor.setOdgovor(Kontroler.getInstance().vratiBine());
                    break;
                case Operacije.VRATI_MUZICARE:
                    serverskiOdgovor.setOdgovor(Kontroler.getInstance().vratiMuzicare());
                    break;
                case Operacije.VRATI_SPONZORE:
                    serverskiOdgovor.setOdgovor(Kontroler.getInstance().vratiSponzore());
                    break;
                case Operacije.VRATI_OPREME:
                    serverskiOdgovor.setOdgovor(Kontroler.getInstance().vratiOpreme());
                    break;
                case Operacije.VRATI_IZVODJACE:
                    serverskiOdgovor.setOdgovor(Kontroler.getInstance().
                            vratiIzvodjace((Koncert) kz.getParametar()));
                    break;
                case Operacije.SACUVAJ_KONCERT_JSON:
                    Koncert koncert = (Koncert) kz.getParametar();
                    Kontroler.getInstance().sacuvajKoncertUJSON(koncert);
                    serverskiOdgovor.setStatusOdgovora(StatusServerskogOdgovora.Success);
                    break;
                case Operacije.LOGIN:
                    Administrator administrator = (Administrator) kz.getParametar();
                    Administrator ulogovani = Kontroler.getInstance().login(administrator);
                    serverskiOdgovor.setOdgovor(ulogovani);
                    break;
                default:
                    return null;
            }
        } catch (Exception e) {
            serverskiOdgovor.setStatusOdgovora(StatusServerskogOdgovora.Error);
            serverskiOdgovor.setExc(e);
        }
        return serverskiOdgovor;
    }
}