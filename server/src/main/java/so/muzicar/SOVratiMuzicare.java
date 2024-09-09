package so.muzicar;

import dbb.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.Muzicar;
import java.util.ArrayList;
import so.ApstraktneSO;

/**
 *
 * @author Korisnik
 */
public class SOVratiMuzicare extends ApstraktneSO{
    
    private ArrayList<Muzicar> lista;

    @Override
    protected void validate(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Muzicar)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Muzicar!");
        }
    }

    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception { 
        ArrayList<ApstraktniDomenskiObjekat> muzicari = DBBroker.getInstance().vrati(ado);
        lista = (ArrayList<Muzicar>) (ArrayList<?>) muzicari;
    }

    public ArrayList<Muzicar> getLista() {
        return lista;
    }
    
}
