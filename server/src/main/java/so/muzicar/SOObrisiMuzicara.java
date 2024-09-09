package so.muzicar;

import dbb.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.Muzicar;
import so.ApstraktneSO;

/**
 *
 * @author Korisnik
 */
public class SOObrisiMuzicara extends ApstraktneSO{

    @Override
    protected void validate(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Muzicar)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Muzicar!");
        }
    }

    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception {
         DBBroker.getInstance().obrisi(ado);
    }
    
}
