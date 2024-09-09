package so;

import dbb.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import java.sql.SQLException;

/**
 *
 * @author Ana
 */
public abstract class ApstraktneSO {
    
    protected abstract void validate(ApstraktniDomenskiObjekat ado) throws Exception;
    protected abstract void execute(ApstraktniDomenskiObjekat ado) throws Exception;

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

    public void commit() throws SQLException {
        DBBroker.getInstance().getConnection().commit();
    }

    public void rollback() throws SQLException {
        DBBroker.getInstance().getConnection().rollback();
    }
}
