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
public class SOIzmeniMuzicara extends ApstraktneSO{

    @Override
    protected void validate(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Muzicar)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Muzicar!");
        }

        Muzicar m = (Muzicar) ado;

        ArrayList<Muzicar> polaznici = (ArrayList<Muzicar>) (ArrayList<?>) DBBroker.getInstance().vrati(ado);

        for (Muzicar muzicar : polaznici) {
            if (muzicar.getKontakt().equals(m.getKontakt())) {
                throw new Exception("Ovaj kontakt vec postoji u bazi!");
            }
        }
    }

    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception {
         DBBroker.getInstance().izmeni(ado);
    }
    
}
