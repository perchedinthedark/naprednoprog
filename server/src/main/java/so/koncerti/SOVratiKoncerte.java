package so.koncerti;

import dbb.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.Koncert;
import java.util.ArrayList;
import so.ApstraktneSO;

/**
 *
 * @author Korisnik
 */
public class SOVratiKoncerte extends ApstraktneSO{
    
     private ArrayList<Koncert> lista;

    @Override
    protected void validate(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Koncert)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Koncert!");
        }
    }

    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception {
        ArrayList<ApstraktniDomenskiObjekat> koncerti = DBBroker.getInstance().vrati(ado);
        lista = (ArrayList<Koncert>) (ArrayList<?>) koncerti;
    }

    public ArrayList<Koncert> getLista() {
        return lista;
    }
    
}
