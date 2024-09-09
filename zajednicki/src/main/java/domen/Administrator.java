package domen;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Korisnik
 */
public class Administrator extends ApstraktniDomenskiObjekat{
    
    private Long administratorID;
    private String ime;
    private String prezime;
    private String username;
    private String password;

    public Administrator() {
    }

    public Administrator(Long administratorID, String ime, String prezime, String username, String password) {
        this.administratorID = administratorID;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
    }
    
    
    
    

    @Override
    public String nazivTabele() {
        return " administrator ";
    }

    @Override
    public String alijas() {
        return " a ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        
        ArrayList<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            Administrator a = new Administrator(rs.getLong("AdministratorID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("Username"), rs.getString("Password"));

            lista.add(a);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        
         return " (Ime, Prezime, Username, Password) ";
    }

    @Override
    public String uslov() { 
        return " AdministratorID = " + administratorID;
    }

    @Override
    public String vrednostiZaInsert() {
          return "'" + ime + "', '" + prezime + "', "
                + "'" + username + "', '" + password + "'";
    }

    @Override
    public String vrednostiZaUpdate() {
        
        return " Ime = '" + ime + "', Prezime = '" + prezime + "', "
                + "Username = '" + username + "', Password = '" + password + "' ";
    }

    @Override
    public String uslovZaSelect() {
        
     return "";   
    }

    public Long getAdministratorID() {
        return administratorID;
    }

    public void setAdministratorID(Long administratorID) {
        this.administratorID = administratorID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public String toString() {
        return ime + " " + prezime;
    }
}

