package domen;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Muzicar.
 */
public class MuzicarTest {

    private Muzicar muzicar;

    @BeforeEach
    public void setUp() {
        muzicar = new Muzicar();
    }

    @Test
    public void testDefaultConstructor() {
        assertNull(muzicar.getMuzicarID());
        assertNull(muzicar.getIme());
        assertNull(muzicar.getInstrument());
        assertNull(muzicar.getZanr());
        assertNull(muzicar.getKontakt());
    }

    @Test
    public void testParameterizedConstructor() {
        Muzicar paramMuzicar = new Muzicar(1L, "John Doe", "Guitar", "Rock", "123-456-7890");

        assertEquals(1L, paramMuzicar.getMuzicarID());
        assertEquals("John Doe", paramMuzicar.getIme());
        assertEquals("Guitar", paramMuzicar.getInstrument());
        assertEquals("Rock", paramMuzicar.getZanr());
        assertEquals("123-456-7890", paramMuzicar.getKontakt());
    }

    @Test
    public void testSetAndGetMuzicarID() {
        muzicar.setMuzicarID(5L);
        assertEquals(5L, muzicar.getMuzicarID());
    }
    
    @Test
    public void testInvalidMuzicarIDThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            muzicar.setMuzicarID(null);
        });
        assertEquals("Muzicar ID mora biti pozitivan broj.", exception.getMessage());
    }

    @Test
    public void testSetAndGetIme() {
        muzicar.setIme("Alice Smith");
        assertEquals("Alice Smith", muzicar.getIme());
    }
    
    @Test
    public void testInvalidImeThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            muzicar.setIme(null);
        });
        assertEquals("Ime ne može biti prazno ili null.", exception.getMessage());
    }

    @Test
    public void testSetAndGetInstrument() {
        muzicar.setInstrument("Piano");
        assertEquals("Piano", muzicar.getInstrument());
    }
    
    @Test
    public void testInvalidInstrumentThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            muzicar.setInstrument(null);
        });
        assertEquals("Instrument ne može biti prazan ili null.", exception.getMessage());
    }


    @Test
    public void testSetAndGetZanr() {
        muzicar.setZanr("Jazz");
        assertEquals("Jazz", muzicar.getZanr());
    }
    
    @Test
    public void testInvalidZanrThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            muzicar.setZanr(null);
        });
        assertEquals("Žanr ne može biti prazan ili null.", exception.getMessage());
    }

    @Test
    public void testSetAndGetKontakt() {
        muzicar.setKontakt("987-654-3210");
        assertEquals("987-654-3210", muzicar.getKontakt());
    }
    
    @Test
    public void testInvalidKontaktThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            muzicar.setKontakt(null);
        });
        assertEquals("Kontakt informacije ne mogu biti prazne ili null.", exception.getMessage());
    }

    @Test
    public void testToString() {
        muzicar.setIme("Bob Dylan");
        assertEquals("Bob Dylan", muzicar.toString());
    }

    @Test
    public void testVrednostiZaInsert() {
        muzicar.setIme("Bob Dylan");
        muzicar.setInstrument("Harmonica");
        muzicar.setZanr("Folk");
        muzicar.setKontakt("555-1234");

        String expectedInsertValues = "'Bob Dylan', 'Harmonica', 'Folk', '555-1234'";
        assertEquals(expectedInsertValues, muzicar.vrednostiZaInsert());
    }

    @Test
    public void testVrednostiZaUpdate() {
        muzicar.setInstrument("Drums");
        muzicar.setZanr("Rock");
        muzicar.setKontakt("987-654-3210");

        String expectedUpdateValues = " instrument = 'Drums', zanr = 'Rock', kontakt = '987-654-3210' ";
        assertEquals(expectedUpdateValues, muzicar.vrednostiZaUpdate());
    }

    @Test
    public void testUslov() {
        muzicar.setMuzicarID(7L);
        assertEquals(" MuzicarID = 7", muzicar.uslov());
    }

    @Test
    public void testKoloneZaInsert() {
        String expectedColumns = " (Ime, Instrument, Zanr, Kontakt) ";
        assertEquals(expectedColumns, muzicar.koloneZaInsert());
    }

    @Test
    public void testNazivTabele() {
        assertEquals(" muzicar ", muzicar.nazivTabele());
    }

    @Test
    public void testAlijas() {
        assertEquals(" m ", muzicar.alijas());
    }

    @Test
    public void testJoin() {
        assertEquals("", muzicar.join());
    }

    @Test
    public void testUslovZaSelect() {
        assertEquals("", muzicar.uslovZaSelect());
    }
}
