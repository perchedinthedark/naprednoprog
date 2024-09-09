package so.lokacija;

import dbb.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.Lokacija;
import java.util.ArrayList;
import so.ApstraktneSO;

/**
 *
 * @author Korisnik
 */
public class SOVratiLokacije extends ApstraktneSO{
    
     private ArrayList<Lokacija> lista;

    @Override
    protected void validate(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Lokacija)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Lokacija!");
        }
    }

    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception {
        ArrayList<ApstraktniDomenskiObjekat> lokacije = DBBroker.getInstance().vrati(ado);
        lista = (ArrayList<Lokacija>) (ArrayList<?>) lokacije;
    }

    public ArrayList<Lokacija> getLista() {
        return lista;
    }
    
}
