package so.koncerti;

import dbb.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.Izvodjac;
import domen.Koncert;
import java.util.Date;
import so.ApstraktneSO;

/**
 *
 * @author Korisnik
 */
public class SOIzmeniKoncert extends ApstraktneSO{

    @Override
    protected void validate(ApstraktniDomenskiObjekat ado) throws Exception {
        
        if (!(ado instanceof Koncert)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Koncert!");
        }

        Koncert k = (Koncert) ado;

        if (!k.getDatumPocetka().after(new Date())) {
            throw new Exception("Datum pocetka mora biti posle danasnjeg datuma!");
        }

        if (!k.getDatumZavrsetka().after(k.getDatumPocetka())) {
            throw new Exception("Datum zavrsetka mora biti posle datum pocetka!");
        }

        if (k.getKapacitetKoncerta()< 2 || k.getKapacitetKoncerta() > 100) {
            throw new Exception("Kapacitet grupe mora biti izmedju 2 i 100!");
        }

        if (k.getIzvodjaci().size() < 2) {
            throw new Exception("Koncert mora imati minimalno 2 izvodjaca!");
        }
        
    }

    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception {
        // updatujemo grupu
        DBBroker.getInstance().izmeni(ado);

        Koncert k = (Koncert) ado;
        // obrisemo stare ucenike
        // sledeca linija koda izvrsava naredbu
        // DELETE FROM UCENIK WHERE GRUPAID = nasID
        // cime se brisu SVI ucenici nase grupe ODJEDNOM !!!
        DBBroker.getInstance().obrisi(k.getIzvodjaci().get(0));

        // dodamo nove
        for (Izvodjac i : k.getIzvodjaci()) {
            DBBroker.getInstance().dodaj(i);
        }
        
        // ovaj nacin nije optimalan, mogu da te pitaju sta ako imas 
        // milion stavki, onda bi milion brisala i milion novih dodavala
        // sto oduzima mnogo resursa
        // optimalan nacin je da imamo uvid u to (neki status) koja stavka je 
        // obrisana, koja izmenjena, koja dodata i te odredjene
        // da brisemo, menjamo i dodajemo
        // ali mi smo odradili na ovaj daleko laksi i brzi nacin
        // ako te pitaju za bolji nacin i zasto si ovako radila, samo ovo ispricas
        
    }
    }
    

