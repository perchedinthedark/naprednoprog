package so.sponzor;

import dbb.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.Sponzor;
import java.util.ArrayList;
import so.ApstraktneSO;

/**
 * Klasa SOVratiSponzore predstavlja konkretnu sistemsku operaciju za vraćanje liste sponzora iz baze podataka.
 * Nasleđuje apstraktnu klasu ApstraktneSO i implementira metode za validaciju i izvršenje.
 * 
 * @author Ranko
 */
public class SOVratiSponzore extends ApstraktneSO {
    
    /** Lista sponzora koja je vraćena iz baze podataka */
    private ArrayList<Sponzor> lista;

    /**
     * Validira prosleđeni objekat. Proverava da li je objekat instanca klase Sponzor.
     * 
     * @param ado Objekat koji se validira
     * @throws Exception Ako prosleđeni objekat nije instanca klase Sponzor
     */
    @Override
    protected void validate(ApstraktniDomenskiObjekat ado) throws Exception {
         if (!(ado instanceof Sponzor)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Sponzor!");
        }
    }

    /**
     * Izvršava operaciju vraćanja sponzora iz baze podataka.
     * 
     * @param ado Objekat klase Sponzor
     * @throws Exception Ako dođe do greške tokom izvršenja
     */
    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception {
        ArrayList<ApstraktniDomenskiObjekat> sponzori = DBBroker.getInstance().vrati(ado);
        lista = (ArrayList<Sponzor>) (ArrayList<?>) sponzori;
    }

    /**
     * Vraća listu sponzora koja je preuzeta iz baze podataka.
     * 
     * @return Lista objekata klase Sponzor
     */
    public ArrayList<Sponzor> getLista() {
        return lista;
    }
}