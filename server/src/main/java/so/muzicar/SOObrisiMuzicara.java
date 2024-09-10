package so.muzicar;

import dbb.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.Muzicar;
import so.ApstraktneSO;
/**
 * Klasa SOObrisiMuzicara predstavlja konkretnu sistemsku operaciju za brisanje muzičara iz baze podataka.
 * Nasleđuje apstraktnu klasu ApstraktneSO i implementira metode za validaciju i izvršenje.
 * 
 * @author Ranko
 */
public class SOObrisiMuzicara extends ApstraktneSO {

    /**
     * Validira prosleđeni objekat. Proverava da li je objekat instanca klase Muzicar.
     * 
     * @param ado Objekat koji se validira
     * @throws Exception Ako prosleđeni objekat nije instanca klase Muzicar
     */
    @Override
    protected void validate(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Muzicar)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Muzicar!");
        }
    }

    /**
     * Izvršava operaciju brisanja muzičara iz baze podataka.
     * 
     * @param ado Objekat klase Muzicar
     * @throws Exception Ako dođe do greške tokom izvršenja
     */
    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception {
        DBBroker.getInstance().obrisi(ado);
    }
}