package so.oprema;

import dbb.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.Oprema;
import java.util.ArrayList;
import so.ApstraktneSO;

/**
 *
 * @author Korisnik
 */
public class SOVratiOpreme extends ApstraktneSO{
    
     private ArrayList<Oprema> lista;

    @Override
    protected void validate(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Oprema)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Oprema!");
        }
    }

    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception {
        ArrayList<ApstraktniDomenskiObjekat> opreme = DBBroker.getInstance().vrati(ado);
        lista = (ArrayList<Oprema>) (ArrayList<?>) opreme;
    }

    public ArrayList<Oprema> getLista() {
        return lista;
    }

    
    
}
