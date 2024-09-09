package so.sponzor;

import dbb.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.Sponzor;
import java.util.ArrayList;
import so.ApstraktneSO;

/**
 *
 * @author Korisnik
 */
public class SOVratiSponzore extends ApstraktneSO{
    
     private ArrayList<Sponzor> lista;

    @Override
    protected void validate(ApstraktniDomenskiObjekat ado) throws Exception {
         if (!(ado instanceof Sponzor)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Sponzor!");
        }
    }

    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception {
         ArrayList<ApstraktniDomenskiObjekat> sponzori = DBBroker.getInstance().vrati(ado);
        lista = (ArrayList<Sponzor>) (ArrayList<?>) sponzori;
    }

    public ArrayList<Sponzor> getLista() {
        return lista;
    }
    
}
