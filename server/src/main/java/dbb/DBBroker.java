package dbb;

import domen.ApstraktniDomenskiObjekat;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Klasa DBBroker predstavlja sloj za interakciju sa bazom podataka. 
 * Implementira Singleton obrazac kako bi se obezbedila jedinstvena instanca klase 
 * za rad sa bazom podataka. Kroz ovu klasu se izvršavaju operacije kao što su 
 * dodavanje, izmena, brisanje i preuzimanje podataka iz baze.
 * 
 * @author Ranko
 */
public class DBBroker {

    /** Jedinstvena instanca klase DBBroker (Singleton) */
    private static DBBroker instance;

    /** Veza sa bazom podataka */
    private Connection connection;

    /**
     * Privatni konstruktor za klasu DBBroker koji uspostavlja vezu sa bazom 
     * podataka na osnovu parametara iz konfiguracionog fajla.
     */
    private DBBroker() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("dbconfig.properties"));
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Vraća trenutnu vezu sa bazom podataka.
     * 
     * @return Connection objekat koji predstavlja vezu sa bazom podataka
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Vraća jedinstvenu instancu klase DBBroker.
     * Ako instanca ne postoji, kreira se nova.
     * 
     * @return Jedinstvena instanca klase DBBroker
     */
    public static DBBroker getInstance() {
        if (instance == null) {
            instance = new DBBroker();
        }
        return instance;
    }
    
    public static void setInstance(DBBroker dbBroker) {
        instance = dbBroker;
    }

    /**
     * Vraća listu objekata iz baze podataka na osnovu zadatog entiteta.
     * 
     * @param ado ApstraktniDomenskiObjekat koji se pretražuje u bazi podataka
     * @return Lista objekata koji odgovaraju upitu
     * @throws SQLException Ako dođe do greške pri izvršavanju upita
     */
    public ArrayList<ApstraktniDomenskiObjekat> vrati(ApstraktniDomenskiObjekat ado) throws SQLException {
        String upit = "SELECT * FROM " + ado.nazivTabele() + " " + ado.alijas()
                + " " + ado.join() + " " + ado.uslovZaSelect();
        System.out.println(upit);
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(upit);
        return ado.vratiListu(rs);
    }

    /**
     * Dodaje novi entitet u bazu podataka.
     * 
     * @param ado ApstraktniDomenskiObjekat koji se dodaje u bazu podataka
     * @return PreparedStatement za izvršenje operacije dodavanja
     * @throws SQLException Ako dođe do greške pri izvršavanju upita
     */
    public PreparedStatement dodaj(ApstraktniDomenskiObjekat ado) throws SQLException {
        String upit = "INSERT INTO " + ado.nazivTabele() + " "
                + ado.koloneZaInsert() + " VALUES(" + ado.vrednostiZaInsert() + ")";
        System.out.println(upit);
        PreparedStatement ps = connection.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
        ps.executeUpdate();
        return ps;
    }

    /**
     * Ažurira postojeći entitet u bazi podataka.
     * 
     * @param ado ApstraktniDomenskiObjekat koji se ažurira
     * @throws SQLException Ako dođe do greške pri izvršavanju upita
     */
    public void izmeni(ApstraktniDomenskiObjekat ado) throws SQLException {
        String upit = "UPDATE " + ado.nazivTabele() + " SET "
                + ado.vrednostiZaUpdate() + " WHERE " + ado.uslov();
        System.out.println(upit);
        Statement s = connection.createStatement();
        s.executeUpdate(upit);
    }

    /**
     * Briše zadati entitet iz baze podataka.
     * 
     * @param ado ApstraktniDomenskiObjekat koji se briše iz baze podataka
     * @throws SQLException Ako dođe do greške pri izvršavanju upita
     */
    public void obrisi(ApstraktniDomenskiObjekat ado) throws SQLException {
        String upit = "DELETE FROM " + ado.nazivTabele() + " WHERE " + ado.uslov();
        System.out.println(upit);
        Statement s = connection.createStatement();
        s.executeUpdate(upit);
    }
}
