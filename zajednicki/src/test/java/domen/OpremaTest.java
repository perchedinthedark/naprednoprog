package domen;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Oprema.
 */
public class OpremaTest {

    private Oprema oprema;

    @BeforeEach
    public void setUp() {
        oprema = new Oprema();
    }

    @Test
    public void testDefaultConstructor() {
        assertNull(oprema.getOpremaID());
        assertNull(oprema.getNaziv());
        assertNull(oprema.getOpis());
        assertEquals(0, oprema.getUkupnaCena());
    }

    @Test
    public void testParameterizedConstructor() {
        Oprema paramOprema = new Oprema(1L, "Sound System", "High quality speakers", 5000);

        assertEquals(1L, paramOprema.getOpremaID());
        assertEquals("Sound System", paramOprema.getNaziv());
        assertEquals("High quality speakers", paramOprema.getOpis());
        assertEquals(5000, paramOprema.getUkupnaCena());
    }

    @Test
    public void testSetAndGetOpremaID() {
        oprema.setOpremaID(2L);
        assertEquals(2L, oprema.getOpremaID());
    }

    @Test
    public void testInvalidOpremaIDThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            oprema.setOpremaID(null);
        });
        assertEquals("Oprema ID mora biti pozitivan broj.", exception.getMessage());
    }
    
    @Test
    public void testSetAndGetNaziv() {
        oprema.setNaziv("Lighting");
        assertEquals("Lighting", oprema.getNaziv());
    }
    
    @Test
    public void testInvalidNazivThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            oprema.setNaziv(null);
        });
        assertEquals("Naziv ne može biti prazan ili null.", exception.getMessage());
    }

    @Test
    public void testSetAndGetOpis() {
        oprema.setOpis("LED stage lights");
        assertEquals("LED stage lights", oprema.getOpis());
    }
    
    @Test
    public void testInvalidOpisThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            oprema.setOpis(null);
        });
        assertEquals("Opis ne može biti prazan ili null.", exception.getMessage());
    }

    @Test
    public void testSetAndGetUkupnaCena() {
        oprema.setUkupnaCena(10000);
        assertEquals(10000, oprema.getUkupnaCena());
    }
    
    @Test
    public void testInvalidUkupnaCenaThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            oprema.setUkupnaCena(-500);
        });
        assertEquals("Cena ne može biti negativna.", exception.getMessage());
    }

  
    @Test
    public void testNazivTabele() {
        assertEquals(" Oprema ", oprema.nazivTabele());
    }

    @Test
    public void testAlijas() {
        assertEquals(" o ", oprema.alijas());
    }

    @Test
    public void testJoin() {
        assertEquals("", oprema.join());
    }

    @Test
    public void testUslov() {
        oprema.setOpremaID(3L);
        assertEquals(" opremaID = 3", oprema.uslov());
    }

    @Test
    public void testKoloneZaInsert() {
        assertEquals("", oprema.koloneZaInsert());
    }

    @Test
    public void testVrednostiZaInsert() {
        assertEquals("", oprema.vrednostiZaInsert());
    }

    @Test
    public void testVrednostiZaUpdate() {
        assertEquals("", oprema.vrednostiZaUpdate());
    }

    @Test
    public void testUslovZaSelect() {
        assertEquals("", oprema.uslovZaSelect());
    }
}
