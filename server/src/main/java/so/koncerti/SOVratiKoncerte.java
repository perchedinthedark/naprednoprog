package so.koncerti;

import dbb.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.Koncert;
import java.util.ArrayList;
import so.ApstraktneSO;

/**
 * Klasa SOVratiKoncerte predstavlja konkretnu sistemsku operaciju za vraćanje liste koncerata iz baze podataka.
 * Nasleđuje apstraktnu klasu ApstraktneSO i implementira metode za validaciju i izvršenje.
 * 
 * @author Ranko
 */
public class SOVratiKoncerte extends ApstraktneSO {
    
    /** Lista koncerata koja je vraćena iz baze podataka */
    private ArrayList<Koncert> lista;

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
     * Izvršava operaciju vraćanja koncerata iz baze podataka.
     * 
     * @param ado Objekat klase Koncert
     * @throws Exception Ako dođe do greške tokom izvršenja
     */
    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception {
        ArrayList<ApstraktniDomenskiObjekat> koncerti = DBBroker.getInstance().vrati(ado);
        lista = (ArrayList<Koncert>) (ArrayList<?>) koncerti;
    }

    /**
     * Vraća listu koncerata koja je preuzeta iz baze podataka.
     * 
     * @return Lista objekata klase Koncert
     */
    public ArrayList<Koncert> getLista() {
        return lista;
    }
}