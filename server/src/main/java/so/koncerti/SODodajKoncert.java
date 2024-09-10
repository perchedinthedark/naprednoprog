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
 * Klasa SODodajKoncert predstavlja konkretnu sistemsku operaciju za dodavanje koncerta u bazu podataka.
 * Nasleđuje apstraktnu klasu ApstraktneSO i implementira metode za validaciju i izvršenje.
 * 
 * @author Ranko
 */
public class SODodajKoncert extends ApstraktneSO {

    /**
     * Validira prosleđeni objekat. Proverava da li je objekat instanca klase Koncert i vrši dodatne provere.
     * 
     * @param ado Objekat koji se validira
     * @throws Exception Ako validacija ne uspe ili neki od uslova nije ispunjen
     */
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

        if (k.getKapacitetKoncerta() < 2 || k.getKapacitetKoncerta() > 100) {
            throw new Exception("Kapacitet koncerta mora biti izmedju 2 i 100!");
        }

        if (k.getIzvodjaci().size() < 2) {
            throw new Exception("Koncert mora imati minimalno 2 izvodjaca!");
        }
    }

    /**
     * Izvršava operaciju dodavanja koncerta i izvođača u bazu podataka.
     * 
     * @param ado Objekat klase Koncert
     * @throws Exception Ako dođe do greške tokom izvršenja
     */
    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception {
        // Vraćamo PreparedStatement sa generisanim ključem
        PreparedStatement ps = DBBroker.getInstance().dodaj(ado);

        // Uzimamo generisani ključ (ID koncerta)
        ResultSet tableKeys = ps.getGeneratedKeys();
        tableKeys.next();
        Long koncertID = tableKeys.getLong(1);

        // Postavljamo koncertID za sve izvođače koji učestvuju na koncertu
        Koncert k = (Koncert) ado;
        k.setKoncertID(koncertID);

        for (Izvodjac i : k.getIzvodjaci()) {
            i.setKoncert(k);
            DBBroker.getInstance().dodaj(i);
        }
    }
}
