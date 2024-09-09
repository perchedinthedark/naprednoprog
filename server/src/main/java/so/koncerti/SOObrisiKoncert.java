package so.koncerti;

import dbb.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.Koncert;
import so.ApstraktneSO;

/**
 *
 * @author Korisnik
 */
public class SOObrisiKoncert extends ApstraktneSO{

    @Override
    protected void validate(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Koncert)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Koncert!");
        }
    }

    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception {
         DBBroker.getInstance().obrisi(ado);
    }
    
}
