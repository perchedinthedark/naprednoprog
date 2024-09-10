package so.login;

import dbb.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.Administrator;
import java.util.ArrayList;
import so.ApstraktneSO;

/**
 * Klasa SOLogin predstavlja konkretnu sistemsku operaciju za prijavljivanje administratora u sistem.
 * Nasleđuje apstraktnu klasu ApstraktneSO i implementira metode za validaciju i izvršenje.
 * 
 * @author Ranko
 */
public class SOLogin extends ApstraktneSO {

    /** Administrator koji je uspešno prijavljen u sistem */
    private Administrator ulogovani;

    /**
     * Validira prosleđeni objekat. Proverava da li je objekat instanca klase Administrator.
     * 
     * @param ado Objekat koji se validira
     * @throws Exception Ako prosleđeni objekat nije instanca klase Administrator
     */
    @Override
    protected void validate(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Administrator)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Administrator!");
        }
    }

    /**
     * Izvršava operaciju prijavljivanja administratora.
     * Pretražuje bazu podataka kako bi našla administratora sa odgovarajućim kredencijalima.
     * 
     * @param ado Objekat klase Administrator
     * @throws Exception Ako administrator sa zadatim kredencijalima ne postoji
     */
    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception {

        Administrator a = (Administrator) ado;

        ArrayList<Administrator> administratori
                = (ArrayList<Administrator>) (ArrayList<?>) DBBroker.getInstance().vrati(ado);

        for (Administrator administrator : administratori) {
            if (administrator.getUsername().equals(a.getUsername())
                    && administrator.getPassword().equals(a.getPassword())) {
                ulogovani = administrator;
                return;
            }
        }

        throw new Exception("Ne postoji administrator sa tim kredencijalima.");
    }

    /**
     * Vraća administratora koji je uspešno prijavljen u sistem.
     * 
     * @return Administrator koji je prijavljen
     */
    public Administrator getUlogovani() {
        return ulogovani;
    }
}
