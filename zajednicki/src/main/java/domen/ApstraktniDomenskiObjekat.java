package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class ApstraktniDomenskiObjekat implements Serializable{
	
    public abstract String nazivTabele();
    public abstract String alijas();
    public abstract String join();
    public abstract ArrayList<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException;
    public abstract String koloneZaInsert();
    public abstract String uslov();
    public abstract String vrednostiZaInsert();
    public abstract String vrednostiZaUpdate();
    public abstract String uslovZaSelect();

}
