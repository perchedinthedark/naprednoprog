package so.bina;

import dbb.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.Bina;
import java.util.ArrayList;
import so.ApstraktneSO;

/**
 * Klasa SOVratiBine predstavlja konkretnu sistemsku operaciju za vraćanje liste bina iz baze podataka.
 * Nasleđuje apstraktnu klasu ApstraktneSO i implementira metode za validaciju i izvršenje.
 * 
 * @author Ranko
 */
public class SOVratiBine extends ApstraktneSO {

    /** Lista bina koja je vraćena iz baze podataka */
    private ArrayList<Bina> lista;

    /**
     * Validira prosleđeni objekat. Proverava da li je objekat instanca klase Bina.
     * 
     * @param ado Objekat koji se validira
     * @throws Exception Ako prosleđeni objekat nije instanca klase Bina
     */
    @Override
    protected void validate(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Bina)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Bina!");
        }
    }

    /**
     * Izvršava operaciju vraćanja bina iz baze podataka.
     * Poziva metodu DBBrokera za preuzimanje bina i prebacuje rezultate u listu.
     * 
     * @param ado Objekat klase Bina
     * @throws Exception Ako dođe do greške tokom izvršenja
     */
    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception {
        ArrayList<ApstraktniDomenskiObjekat> bine = DBBroker.getInstance().vrati(ado);
        lista = (ArrayList<Bina>) (ArrayList<?>) bine;
    }

    /**
     * Vraća listu bina koja je preuzeta iz baze podataka.
     * 
     * @return Lista objekata klase Bina
     */
    public ArrayList<Bina> getLista() {
        return lista;
    }
}