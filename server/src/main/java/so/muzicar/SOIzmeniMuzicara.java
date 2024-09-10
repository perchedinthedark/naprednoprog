package so.muzicar;

import dbb.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.Muzicar;
import java.util.ArrayList;
import so.ApstraktneSO;

/**
 * Klasa SOIzmeniMuzicara predstavlja konkretnu sistemsku operaciju za izmenu muzičara u bazi podataka.
 * Nasleđuje apstraktnu klasu ApstraktneSO i implementira metode za validaciju i izvršenje.
 * 
 * @author Ranko
 */
public class SOIzmeniMuzicara extends ApstraktneSO {

    /**
     * Validira prosleđeni objekat. Proverava da li je objekat instanca klase Muzicar i vrši dodatne provere.
     * 
     * @param ado Objekat koji se validira
     * @throws Exception Ako validacija ne uspe ili neki od uslova nije ispunjen
     */
    @Override
    protected void validate(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Muzicar)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Muzicar!");
        }

        Muzicar m = (Muzicar) ado;

        ArrayList<Muzicar> polaznici = (ArrayList<Muzicar>) (ArrayList<?>) DBBroker.getInstance().vrati(ado);

        for (Muzicar muzicar : polaznici) {
            if (muzicar.getKontakt().equals(m.getKontakt())) {
                throw new Exception("Ovaj kontakt vec postoji u bazi!");
            }
        }
    }

    /**
     * Izvršava operaciju izmene muzičara u bazi podataka.
     * 
     * @param ado Objekat klase Muzicar
     * @throws Exception Ako dođe do greške tokom izvršenja
     */
    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception {
        DBBroker.getInstance().izmeni(ado);
    }
}
