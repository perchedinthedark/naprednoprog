package domen;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Predstavlja administratora u sistemu, odgovornog za upravljanje pristupom i informacijama.
 * Ova klasa proširuje {@code ApstraktniDomenskiObjekat} i komunicira sa bazom podataka
 * kako bi rukovala podacima vezanim za administratora.
 * 
 * @author Ranko
 */
public class Administrator extends ApstraktniDomenskiObjekat {

    /**
     * Jedinstveni ID administratora.
     */
    private Long administratorID;

    /**
     * Ime administratora.
     */
    private String ime;

    /**
     * Prezime administratora.
     */
    private String prezime;

    /**
     * Korisničko ime administratora koje se koristi za prijavu.
     */
    private String username;

    /**
     * Lozinka administratora koja se koristi za prijavu.
     */
    private String password;

    /**
     * Podrazumevani konstruktor koji inicijalizuje prazan Administrator objekat.
     */
    public Administrator() {
    }

    /**
     * Parametrizovani konstruktor koji inicijalizuje Administrator sa datim vrednostima.
     * 
     * @param administratorID Jedinstveni ID administratora.
     * @param ime Ime administratora.
     * @param prezime Prezime administratora.
     * @param username Korisničko ime administratora.
     * @param password Lozinka administratora.
     */
    public Administrator(Long administratorID, String ime, String prezime, String username, String password) {
    	setAdministratorID(administratorID);
        setIme(ime);
        setPrezime(prezime);
        setUsername(username);
        setPassword(password);
    }

    /**
     * Vraća naziv tabele iz baze podataka koja je povezana sa ovim domenom.
     * 
     * @return Naziv tabele kao string.
     */
    @Override
    public String nazivTabele() {
        return " administrator ";
    }

    /**
     * Vraća alijas koji se koristi za ovu tabelu u SQL upitima.
     * 
     * @return Alijas za tabelu kao string.
     */
    @Override
    public String alijas() {
        return " a ";
    }

    /**
     * Vraća bilo koje JOIN klauzule za SQL upite.
     * 
     * @return Prazan string jer nema povezivanja (JOIN) za tabelu administrator.
     */
    @Override
    public String join() {
        return "";
    }

    /**
     * Konvertuje {@code ResultSet} dobijen iz baze podataka u listu 
     * {@code Administrator} objekata.
     * 
     * @param rs {@code ResultSet} koji sadrži podatke iz baze podataka.
     * @return Lista {@code ApstraktniDomenskiObjekat} koja sadrži administrator objekte.
     * @throws SQLException ako dođe do greške prilikom obrade {@code ResultSet}.
     */
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

    /**
     * Vraća nazive kolona koje će biti korišćene za unos podataka u bazu.
     * 
     * @return String koji sadrži nazive kolona za unos.
     */
    @Override
    public String koloneZaInsert() {
        return " (Ime, Prezime, Username, Password) ";
    }

    /**
     * Vraća uslov koji će biti korišćen u WHERE klauzuli SQL upita.
     * 
     * @return String koji predstavlja uslov baziran na ID administratora.
     */
    @Override
    public String uslov() {
        return " AdministratorID = " + administratorID;
    }

    /**
     * Vraća vrednosti koje će biti unete u bazu podataka prilikom kreiranja novog administratora.
     * 
     * @return String koji sadrži vrednosti za unos.
     */
    @Override
    public String vrednostiZaInsert() {
        return "'" + ime + "', '" + prezime + "', "
                + "'" + username + "', '" + password + "'";
    }

    /**
     * Vraća vrednosti koje će biti ažurirane u bazi podataka za postojećeg administratora.
     * 
     * @return String koji sadrži vrednosti za ažuriranje.
     */
    @Override
    public String vrednostiZaUpdate() {
        return " Ime = '" + ime + "', Prezime = '" + prezime + "', "
                + "Username = '" + username + "', Password = '" + password + "' ";
    }

    /**
     * Vraća uslov koji će biti korišćen u SELECT klauzuli za preuzimanje podataka.
     * 
     * @return Prazan string, što znači da se ne primenjuje specifičan uslov.
     */
    @Override
    public String uslovZaSelect() {
        return "";
    }

    /**
     * Vraća jedinstveni ID administratora.
     * 
     * @return ID administratora.
     */
    public Long getAdministratorID() {
        return administratorID;
    }

    /**
     * Postavlja jedinstveni ID administratora.
     * 
     * @param administratorID ID administratora.
     * @throws IllegalArgumentException ako je ID null ili manji od 1.
     */
    public void setAdministratorID(Long administratorID) {
    	if (administratorID == null || administratorID <= 0) {
            throw new IllegalArgumentException("Administrator ID mora biti pozitivan broj.");
        }
        this.administratorID = administratorID;
    }

    /**
     * Vraća ime administratora.
     * 
     * @return Ime administratora.
     */
    public String getIme() {
        return ime;
    }

    /**
     * Postavlja ime administratora.
     * 
     * @param ime Ime administratora.
     * @throws IllegalArgumentException ako je ime null ili prazno.
     */
    public void setIme(String ime) {
    	if (ime == null || ime.trim().isEmpty()) {
            throw new IllegalArgumentException("Ime ne može biti prazno ili null.");
        }
        this.ime = ime;
    }

    /**
     * Vraća prezime administratora.
     * 
     * @return Prezime administratora.
     * 
     */
    public String getPrezime() {
    	
        return prezime;
    }

    /**
     * Postavlja prezime administratora.
     * 
     * @param prezime Prezime administratora.
     * @throws IllegalArgumentException ako je prezime null ili prazno.
     */
    public void setPrezime(String prezime) {
    	if (prezime == null || prezime.trim().isEmpty()) {
            throw new IllegalArgumentException("Prezime ne može biti prazno ili null.");
        }
        this.prezime = prezime;
    }

    /**
     * Vraća korisničko ime administratora.
     * 
     * @return Korisničko ime administratora.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Postavlja korisničko ime administratora.
     * 
     * @param username Korisničko ime administratora.
     * @throws IllegalArgumentException ako je korisničko ime null ili prazno.
     */
    public void setUsername(String username) {
    	if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Korisničko ime ne može biti prazno ili null.");
        }
        this.username = username;
    }

    /**
     * Vraća lozinku administratora.
     * 
     * @return Lozinka administratora.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Postavlja lozinku administratora.
     * 
     * @param password Lozinka administratora.
     * @throws IllegalArgumentException ako je lozinka null ili kraća od 6 karaktera.
     */
    public void setPassword(String password) {
    	if (password == null || password.length() < 6) {
            throw new IllegalArgumentException("Lozinka mora imati najmanje 6 karaktera.");
        }
        this.password = password;
    }

    /**
     * Vraća string reprezentaciju objekta administratora, prikazujući ime i prezime.
     * 
     * @return String u formatu "{@code ime} {@code prezime}".
     */
    @Override
    public String toString() {
        return ime + " " + prezime;
    }
}