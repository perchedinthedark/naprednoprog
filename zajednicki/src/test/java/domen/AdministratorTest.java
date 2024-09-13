package domen;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AdministratorTest {

    private Administrator administrator;

    @BeforeEach
    public void setUp() {
        administrator = new Administrator(1L, "John", "Doe", "johndoe", "password123");
    }

    @Test
    public void testDefaultConstructor() {
        Administrator emptyAdmin = new Administrator();
        assertNull(emptyAdmin.getAdministratorID());
        assertNull(emptyAdmin.getIme());
        assertNull(emptyAdmin.getPrezime());
        assertNull(emptyAdmin.getUsername());
        assertNull(emptyAdmin.getPassword());
    }

    @Test
    public void testParameterizedConstructor() {
        assertEquals(1L, administrator.getAdministratorID());
        assertEquals("John", administrator.getIme());
        assertEquals("Doe", administrator.getPrezime());
        assertEquals("johndoe", administrator.getUsername());
        assertEquals("password123", administrator.getPassword());
    }

    @Test
    public void testGetIme() {
        assertEquals("John", administrator.getIme());
    }

    @Test
    public void testSetIme() {
        administrator.setIme("Jane");
        assertEquals("Jane", administrator.getIme());
    }

    @Test
    public void testGetPrezime() {
        assertEquals("Doe", administrator.getPrezime());
    }

    @Test
    public void testSetPrezime() {
        administrator.setPrezime("Smith");
        assertEquals("Smith", administrator.getPrezime());
    }

    @Test
    public void testGetUsername() {
        assertEquals("johndoe", administrator.getUsername());
    }

    @Test
    public void testSetUsername() {
        administrator.setUsername("janesmith");
        assertEquals("janesmith", administrator.getUsername());
    }

    @Test
    public void testGetPassword() {
        assertEquals("password123", administrator.getPassword());
    }

    @Test
    public void testSetPassword() {
        administrator.setPassword("newpassword");
        assertEquals("newpassword", administrator.getPassword());
    }

    @Test
    public void testNazivTabele() {
        assertEquals(" administrator ", administrator.nazivTabele());
    }

    @Test
    public void testAlijas() {
        assertEquals(" a ", administrator.alijas());
    }

    @Test
    public void testJoin() {
        assertEquals("", administrator.join());
    }

    @Test
    public void testKoloneZaInsert() {
        assertEquals(" (Ime, Prezime, Username, Password) ", administrator.koloneZaInsert());
    }

    @Test
    public void testVrednostiZaInsert() {
        assertEquals("'John', 'Doe', 'johndoe', 'password123'", administrator.vrednostiZaInsert());
    }

    @Test
    public void testVrednostiZaUpdate() {
        assertEquals(" Ime = 'John', Prezime = 'Doe', Username = 'johndoe', Password = 'password123' ",
                     administrator.vrednostiZaUpdate());
    }

    @Test
    public void testUslov() {
        assertEquals(" AdministratorID = 1", administrator.uslov());
    }

    @Test
    public void testToString() {
        assertEquals("John Doe", administrator.toString());
    }
}
