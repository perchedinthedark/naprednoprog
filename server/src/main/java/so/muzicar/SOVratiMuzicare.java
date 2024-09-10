package so.muzicar;

import dbb.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.Muzicar;
import java.util.ArrayList;
import so.ApstraktneSO;

/**
 * Klasa SOVratiMuzicare predstavlja konkretnu sistemsku operaciju za vraćanje liste muzičara iz baze podataka.
 * Nasleđuje apstraktnu klasu ApstraktneSO i implementira metode za validaciju i izvršenje.
 * 
 * @author Ranko
 */
public class SOVratiMuzicare extends ApstraktneSO {
    
    /** Lista muzičara koja je vraćena iz baze podataka */
    private ArrayList<Muzicar> lista;

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
     * Izvršava operaciju vraćanja muzičara iz baze podataka.
     * 
     * @param ado Objekat klase Muzicar
     * @throws Exception Ako dođe do greške tokom izvršenja
     */
    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception { 
        ArrayList<ApstraktniDomenskiObjekat> muzicari = DBBroker.getInstance().vrati(ado);
        lista = (ArrayList<Muzicar>) (ArrayList<?>) muzicari;
    }

    /**
     * Vraća listu muzičara koja je preuzeta iz baze podataka.
     * 
     * @return Lista objekata klase Muzicar
     */
    public ArrayList<Muzicar> getLista() {
        return lista;
    }
}