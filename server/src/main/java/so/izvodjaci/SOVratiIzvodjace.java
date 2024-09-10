package so.izvodjaci;

import dbb.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.Izvodjac;
import java.util.ArrayList;
import so.ApstraktneSO;

/**
 * Klasa SOVratiIzvodjace predstavlja konkretnu sistemsku operaciju za vraćanje liste izvođača iz baze podataka.
 * Nasleđuje apstraktnu klasu ApstraktneSO i implementira metode za validaciju i izvršenje.
 * 
 * @author Ranko
 */
public class SOVratiIzvodjace extends ApstraktneSO {
    
    /** Lista izvođača koja je vraćena iz baze podataka */
    private ArrayList<Izvodjac> lista;

    /**
     * Validira prosleđeni objekat. Proverava da li je objekat instanca klase Izvodjac.
     * 
     * @param ado Objekat koji se validira
     * @throws Exception Ako prosleđeni objekat nije instanca klase Izvodjac
     */
    @Override
    protected void validate(ApstraktniDomenskiObjekat ado) throws Exception {
         if (!(ado instanceof Izvodjac)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Izvodjac!");
        }
    }

    /**
     * Izvršava operaciju vraćanja izvođača iz baze podataka.
     * 
     * @param ado Objekat klase Izvodjac
     * @throws Exception Ako dođe do greške tokom izvršenja
     */
    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception {
        ArrayList<ApstraktniDomenskiObjekat> izvodjaci = DBBroker.getInstance().vrati(ado);
        lista = (ArrayList<Izvodjac>) (ArrayList<?>) izvodjaci;
    }

    /**
     * Vraća listu izvođača koja je preuzeta iz baze podataka.
     * 
     * @return Lista objekata klase Izvodjac
     */
    public ArrayList<Izvodjac> getLista() {
        return lista;
    }
}
