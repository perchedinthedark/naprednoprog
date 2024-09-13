package domen;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class LokacijaTest {

    private Lokacija lokacija;

    @BeforeEach
    public void setUp() {
        lokacija = new Lokacija(1L, "Stadium", "123 Main St", "Open Air", "123-456-7890", "City Council");
    }

    @Test
    public void testDefaultConstructor() {
        Lokacija emptyLokacija = new Lokacija();
        assertNull(emptyLokacija.getLokacjiaID());
        assertNull(emptyLokacija.getNaziv());
        assertNull(emptyLokacija.getAdresa());
        assertNull(emptyLokacija.getTip());
        assertNull(emptyLokacija.getKontakt());
        assertNull(emptyLokacija.getVlasnik());
    }

    @Test
    public void testParameterizedConstructor() {
        assertEquals(1L, lokacija.getLokacjiaID());
        assertEquals("Stadium", lokacija.getNaziv());
        assertEquals("123 Main St", lokacija.getAdresa());
        assertEquals("Open Air", lokacija.getTip());
        assertEquals("123-456-7890", lokacija.getKontakt());
        assertEquals("City Council", lokacija.getVlasnik());
    }

    @Test
    public void testGetAndSetLokacjiaID() {
        assertEquals(1L, lokacija.getLokacjiaID());
        lokacija.setLokacjiaID(2L);
        assertEquals(2L, lokacija.getLokacjiaID());
    }

    @Test
    public void testGetAndSetNaziv() {
        assertEquals("Stadium", lokacija.getNaziv());
        lokacija.setNaziv("Arena");
        assertEquals("Arena", lokacija.getNaziv());
    }

    @Test
    public void testGetAndSetAdresa() {
        assertEquals("123 Main St", lokacija.getAdresa());
        lokacija.setAdresa("456 Park Ave");
        assertEquals("456 Park Ave", lokacija.getAdresa());
    }

    @Test
    public void testGetAndSetTip() {
        assertEquals("Open Air", lokacija.getTip());
        lokacija.setTip("Indoor");
        assertEquals("Indoor", lokacija.getTip());
    }

    @Test
    public void testGetAndSetKontakt() {
        assertEquals("123-456-7890", lokacija.getKontakt());
        lokacija.setKontakt("987-654-3210");
        assertEquals("987-654-3210", lokacija.getKontakt());
    }

    @Test
    public void testGetAndSetVlasnik() {
        assertEquals("City Council", lokacija.getVlasnik());
        lokacija.setVlasnik("Private Owner");
        assertEquals("Private Owner", lokacija.getVlasnik());
    }

    @Test
    public void testNazivTabele() {
        assertEquals(" lokacija ", lokacija.nazivTabele());
    }

    @Test
    public void testAlijas() {
        assertEquals(" l ", lokacija.alijas());
    }

    @Test
    public void testJoin() {
        assertEquals("", lokacija.join());
    }

    @Test
    public void testUslov() {
        assertEquals(" LokacijaID = 1", lokacija.uslov());
    }

    @Test
    public void testVrednostiZaInsert() {
        assertEquals("", lokacija.vrednostiZaInsert());
    }

    @Test
    public void testVrednostiZaUpdate() {
        assertEquals("", lokacija.vrednostiZaUpdate());
    }

    @Test
    public void testUslovZaSelect() {
        assertEquals("", lokacija.uslovZaSelect());
    }

   
}
