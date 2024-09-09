package so.koncerti;

import dbb.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.Izvodjac;
import domen.Koncert;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import so.ApstraktneSO;

/**
 *
 * @author Korisnik
 */
public class SODodajKoncert extends ApstraktneSO{

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
            throw new Exception("Kapacitet koncerta mora biti izmedju 2 i 100!");
        }

        if (k.getIzvodjaci().size() < 2) {
            throw new Exception("Koncert mora imati minimalno 2 izvodjaca!");
        }
    }

    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception {
        
        // vracamo ps sa generisanim kljucem
        PreparedStatement ps = DBBroker.getInstance().dodaj(ado);

        // uzimamo taj kljuc
        ResultSet tableKeys = ps.getGeneratedKeys();
        tableKeys.next();
        Long koncertID = tableKeys.getLong(1);

        // setujemo ga za nasu grupu
        Koncert k = (Koncert) ado;
        k.setKoncertID(koncertID);

        // dodajemo redom ucenika po ucenika
        // nakon sto setujemo da potice iz nase grupe
        for (Izvodjac i : k.getIzvodjaci()) {
            i.setKoncert(k);
            DBBroker.getInstance().dodaj(i);
        }

    }
    }
    

