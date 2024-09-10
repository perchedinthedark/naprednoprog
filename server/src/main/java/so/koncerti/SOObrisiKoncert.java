package so.koncerti;

import dbb.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.Koncert;
import so.ApstraktneSO;

/**
 * Klasa SOObrisiKoncert predstavlja konkretnu sistemsku operaciju za brisanje koncerta iz baze podataka.
 * Nasleđuje apstraktnu klasu ApstraktneSO i implementira metode za validaciju i izvršenje.
 * 
 * @author Ranko
 */
public class SOObrisiKoncert extends ApstraktneSO {

    /**
     * Validira prosleđeni objekat. Proverava da li je objekat instanca klase Koncert.
     * 
     * @param ado Objekat koji se validira
     * @throws Exception Ako prosleđeni objekat nije instanca klase Koncert
     */
    @Override
    protected void validate(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Koncert)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Koncert!");
        }
    }

    /**
     * Izvršava operaciju brisanja koncerta iz baze podataka.
     * 
     * @param ado Objekat klase Koncert
     * @throws Exception Ako dođe do greške tokom izvršenja
     */
    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception {
        DBBroker.getInstance().obrisi(ado);
    }
}