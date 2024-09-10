package so;

import dbb.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import java.sql.SQLException;
/**
 * Apstraktna klasa ApstraktneSO definiše šablon za izvršenje sistemskih operacija (SO).
 * Svaka konkretna sistemska operacija nasleđuje ovu klasu i implementira metode za validaciju
 * i izvršenje operacije. Metoda `templateExecute` koristi šablonski obrazac i poziva
 * validaciju i izvršenje, sa mehanizmom za commit i rollback u slučaju greške.
 * 
 * @author Ranko
 */
public abstract class ApstraktneSO {

    /**
     * Apstraktna metoda za validaciju prosleđenog objekta.
     * Svaka konkretna SO mora implementirati ovu metodu kako bi validirala objekat pre izvršenja.
     * 
     * @param ado Objekat koji se validira
     * @throws Exception Ako validacija ne uspe
     */
    protected abstract void validate(ApstraktniDomenskiObjekat ado) throws Exception;

    /**
     * Apstraktna metoda za izvršenje operacije.
     * Svaka konkretna SO mora implementirati ovu metodu kako bi izvršila specifičnu operaciju.
     * 
     * @param ado Objekat nad kojim se izvršava operacija
     * @throws Exception Ako dođe do greške tokom izvršenja
     */
    protected abstract void execute(ApstraktniDomenskiObjekat ado) throws Exception;

    /**
     * Šablonska metoda koja prvo validira, zatim izvršava operaciju, i na kraju izvršava commit.
     * Ako dođe do greške tokom validacije ili izvršenja, poziva se rollback.
     * 
     * @param ado Objekat nad kojim se izvršava operacija
     * @throws Exception Ako dođe do greške tokom validacije ili izvršenja operacije
     */
    public void templateExecute(ApstraktniDomenskiObjekat ado) throws Exception {
        try {
            validate(ado);
            execute(ado);
            commit();
        } catch (Exception e) {
            rollback();
            throw e;
        }
    }

    /**
     * Izvršava commit transakcije u bazi podataka.
     * 
     * @throws SQLException Ako dođe do greške prilikom commita
     */
    public void commit() throws SQLException {
        DBBroker.getInstance().getConnection().commit();
    }

    /**
     * Izvršava rollback transakcije u bazi podataka u slučaju greške.
     * 
     * @throws SQLException Ako dođe do greške prilikom rollbacka
     */
    public void rollback() throws SQLException {
        DBBroker.getInstance().getConnection().rollback();
    }
}
