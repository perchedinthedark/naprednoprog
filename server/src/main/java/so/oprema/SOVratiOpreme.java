package so.oprema;

import dbb.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.Oprema;
import java.util.ArrayList;
import so.ApstraktneSO;

/**
 * Klasa SOVratiOpreme predstavlja konkretnu sistemsku operaciju za vraćanje liste opreme iz baze podataka.
 * Nasleđuje apstraktnu klasu ApstraktneSO i implementira metode za validaciju i izvršenje.
 * 
 * @author Ranko
 */
public class SOVratiOpreme extends ApstraktneSO {
    
    /** Lista opreme koja je vraćena iz baze podataka */
    private ArrayList<Oprema> lista;

    /**
     * Validira prosleđeni objekat. Proverava da li je objekat instanca klase Oprema.
     * 
     * @param ado Objekat koji se validira
     * @throws Exception Ako prosleđeni objekat nije instanca klase Oprema
     */
    @Override
    protected void validate(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Oprema)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Oprema!");
        }
    }

    /**
     * Izvršava operaciju vraćanja opreme iz baze podataka.
     * 
     * @param ado Objekat klase Oprema
     * @throws Exception Ako dođe do greške tokom izvršenja
     */
    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception {
        ArrayList<ApstraktniDomenskiObjekat> opreme = DBBroker.getInstance().vrati(ado);
        lista = (ArrayList<Oprema>) (ArrayList<?>) opreme;
    }

    /**
     * Vraća listu opreme koja je preuzeta iz baze podataka.
     * 
     * @return Lista objekata klase Oprema
     */
    public ArrayList<Oprema> getLista() {
        return lista;
    }
}
