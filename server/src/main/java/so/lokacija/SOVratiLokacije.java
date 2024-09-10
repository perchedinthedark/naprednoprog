package so.lokacija;

import dbb.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.Lokacija;
import java.util.ArrayList;
import so.ApstraktneSO;

/**
 * Klasa SOVratiLokacije predstavlja konkretnu sistemsku operaciju za vraćanje liste lokacija iz baze podataka.
 * Nasleđuje apstraktnu klasu ApstraktneSO i implementira metode za validaciju i izvršenje.
 * 
 * @author Ranko
 */
public class SOVratiLokacije extends ApstraktneSO {
    
    /** Lista lokacija koja je vraćena iz baze podataka */
    private ArrayList<Lokacija> lista;

    /**
     * Validira prosleđeni objekat. Proverava da li je objekat instanca klase Lokacija.
     * 
     * @param ado Objekat koji se validira
     * @throws Exception Ako prosleđeni objekat nije instanca klase Lokacija
     */
    @Override
    protected void validate(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Lokacija)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Lokacija!");
        }
    }

    /**
     * Izvršava operaciju vraćanja lokacija iz baze podataka.
     * 
     * @param ado Objekat klase Lokacija
     * @throws Exception Ako dođe do greške tokom izvršenja
     */
    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception {
        ArrayList<ApstraktniDomenskiObjekat> lokacije = DBBroker.getInstance().vrati(ado);
        lista = (ArrayList<Lokacija>) (ArrayList<?>) lokacije;
    }

    /**
     * Vraća listu lokacija koja je preuzeta iz baze podataka.
     * 
     * @return Lista objekata klase Lokacija
     */
    public ArrayList<Lokacija> getLista() {
        return lista;
    }
}

