package so.bina;

import dbb.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.Bina;
import java.util.ArrayList;
import so.ApstraktneSO;

/**
 *
 * @author Korisnik
 */
public class SOVratiBine extends ApstraktneSO{
    
    private ArrayList<Bina> lista;

    @Override
    protected void validate(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Bina)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Bina!");
        }
    }

    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception { 
        ArrayList<ApstraktniDomenskiObjekat> bine = DBBroker.getInstance().vrati(ado);
        lista = (ArrayList<Bina>) (ArrayList<?>) bine;
    }

    public ArrayList<Bina> getLista() {
        return lista;
    }
    
}
