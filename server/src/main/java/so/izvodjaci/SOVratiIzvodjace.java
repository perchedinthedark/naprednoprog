package so.izvodjaci;

import dbb.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.Izvodjac;
import java.util.ArrayList;
import so.ApstraktneSO;

/**
 *
 * @author Korisnik
 */
public class SOVratiIzvodjace extends ApstraktneSO{
    
    private ArrayList<Izvodjac> lista;

    @Override
    protected void validate(ApstraktniDomenskiObjekat ado) throws Exception {
         if (!(ado instanceof Izvodjac)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Izvodjac!");
        }
    }

    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception {
        ArrayList<ApstraktniDomenskiObjekat> izvodjaci = DBBroker.getInstance().vrati(ado);
        lista = (ArrayList<Izvodjac>) (ArrayList<?>) izvodjaci;
    }

    public ArrayList<Izvodjac> getLista() {
        return lista;
    }
    
}
