package domen;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IzvodjacTest {

    private Izvodjac izvodjac;
    private Koncert koncert;
    private Muzicar muzicar;

    @BeforeEach
    public void setUp() {
        koncert = new Koncert(1L, null, null, 100, null, null, null, null);
        muzicar = new Muzicar(1L, "John", "Guitar", "Rock", "john@example.com");
        izvodjac = new Izvodjac(koncert, 1, "Napomena", muzicar);
    }

    @Test
    public void testDefaultConstructor() {
        Izvodjac emptyIzvodjac = new Izvodjac();
        assertNull(emptyIzvodjac.getKoncert());
        assertEquals(0, emptyIzvodjac.getRbIzvodjaca());
        assertNull(emptyIzvodjac.getNapomena());
        assertNull(emptyIzvodjac.getMuzicar());
    }

    @Test
    public void testParameterizedConstructor() {
        assertEquals(koncert, izvodjac.getKoncert());
        assertEquals(1, izvodjac.getRbIzvodjaca());
        assertEquals("Napomena", izvodjac.getNapomena());
        assertEquals(muzicar, izvodjac.getMuzicar());
    }

    @Test
    public void testGetAndSetMuzicar() {
        assertEquals(muzicar, izvodjac.getMuzicar());
        Muzicar newMuzicar = new Muzicar(2L, "Jane", "Drums", "Jazz", "jane@example.com");
        izvodjac.setMuzicar(newMuzicar);
        assertEquals(newMuzicar, izvodjac.getMuzicar());
    }

    @Test
    public void testGetAndSetKoncert() {
        assertEquals(koncert, izvodjac.getKoncert());
        Koncert newKoncert = new Koncert(2L, null, null, 200, null, null, null, null);
        izvodjac.setKoncert(newKoncert);
        assertEquals(newKoncert, izvodjac.getKoncert());
    }

    @Test
    public void testGetAndSetRbIzvodjaca() {
        assertEquals(1, izvodjac.getRbIzvodjaca());
        izvodjac.setRbIzvodjaca(2);
        assertEquals(2, izvodjac.getRbIzvodjaca());
    }

    @Test
    public void testGetAndSetNapomena() {
        assertEquals("Napomena", izvodjac.getNapomena());
        izvodjac.setNapomena("Nova napomena");
        assertEquals("Nova napomena", izvodjac.getNapomena());
    }

    @Test
    public void testNazivTabele() {
        assertEquals(" Izvodjac ", izvodjac.nazivTabele());
    }

    @Test
    public void testAlijas() {
        assertEquals(" i ", izvodjac.alijas());
    }

    @Test
    public void testJoin() {
        String expectedJoin = "JOIN MUZICAR M ON (M.MUZICARID = I.MUZICARID) "
                            + "JOIN KONCERT K ON (K.KONCERTID = I.KONCERTID) "
                            + "JOIN SPONZOR S ON (S.SPONZORID = K.SPONZORID) "
                            + "JOIN BINA B ON (B.BINAID = K.BINAID) "
                            + "JOIN LOKACIJA L ON (B.LOKACIJAID = L.LOKACIJAID) "
                            + "JOIN OPREMA O ON (B.OPREMAID = O.OPREMAID) "
                            + "JOIN ADMINISTRATOR A ON (A.ADMINISTRATORID = K.ADMINISTRATORID)";
        assertEquals(expectedJoin, izvodjac.join());
    }

    @Test
    public void testUslov() {
        assertEquals(" KoncertID = 1", izvodjac.uslov());
    }

    @Test
    public void testKoloneZaInsert() {
        assertEquals(" (KoncertID, RbIzvodjaca, Napomena, MuzicarID) ", izvodjac.koloneZaInsert());
    }

    @Test
    public void testVrednostiZaInsert() {
        assertEquals(" 1, 1, 'Napomena', 1 ", izvodjac.vrednostiZaInsert());
    }

    @Test
    public void testUslovZaSelect() {
        assertEquals(" WHERE K.KONCERTID = 1", izvodjac.uslovZaSelect());
    }
}
