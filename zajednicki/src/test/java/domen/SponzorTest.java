package domen;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Sponzor.
 */
public class SponzorTest {

    private Sponzor sponzor;

    @BeforeEach
    public void setUp() {
        sponzor = new Sponzor();
    }

    @Test
    public void testDefaultConstructor() {
        assertNull(sponzor.getSponzorID());
        assertNull(sponzor.getNaziv());
        assertEquals(0.0, sponzor.getIznosKontribucije());
        assertNull(sponzor.getTipSponzorstva());
    }

    @Test
    public void testParameterizedConstructor() {
        Sponzor paramSponzor = new Sponzor(1L, "ABC Corp", 10000.0, "Financial");

        assertEquals(1L, paramSponzor.getSponzorID());
        assertEquals("ABC Corp", paramSponzor.getNaziv());
        assertEquals(10000.0, paramSponzor.getIznosKontribucije());
        assertEquals("Financial", paramSponzor.getTipSponzorstva());
    }

    @Test
    public void testSetAndGetSponzorID() {
        sponzor.setSponzorID(2L);
        assertEquals(2L, sponzor.getSponzorID());
    }
    
    @Test
    public void testInvalidSponzorIDThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            sponzor.setSponzorID(null);
        });
        assertEquals("Sponzor ID mora biti pozitivan broj.", exception.getMessage());
    }

    @Test
    public void testSetAndGetNaziv() {
        sponzor.setNaziv("XYZ Media");
        assertEquals("XYZ Media", sponzor.getNaziv());
    }
    
    @Test
    public void testInvalidNazivThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            sponzor.setNaziv(null);
        });
        assertEquals("Naziv ne može biti prazan ili null.", exception.getMessage());
    }

    @Test
    public void testSetAndGetIznosKontribucije() {
        sponzor.setIznosKontribucije(20000.0);
        assertEquals(20000.0, sponzor.getIznosKontribucije());
    }
    
    @Test
    public void testInvalidIznosKontribucijeThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            sponzor.setIznosKontribucije(-1000);
        });
        assertEquals("Iznos kontribucije ne može biti negativan.", exception.getMessage());
    }


    @Test
    public void testSetAndGetTipSponzorstva() {
        sponzor.setTipSponzorstva("Media");
        assertEquals("Media", sponzor.getTipSponzorstva());
    }
    
    @Test
    public void testInvalidTipSponzorstvaThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            sponzor.setTipSponzorstva(null);
        });
        assertEquals("Tip sponzorstva ne može biti prazan ili null.", exception.getMessage());
    }

    @Test
    public void testToString() {
        sponzor.setNaziv("DEF Company");
        assertEquals("DEF Company", sponzor.toString());
    }

    @Test
    public void testNazivTabele() {
        assertEquals(" sponzor ", sponzor.nazivTabele());
    }

    @Test
    public void testAlijas() {
        assertEquals(" s ", sponzor.alijas());
    }

    @Test
    public void testJoin() {
        assertEquals("", sponzor.join());
    }

    @Test
    public void testUslov() {
        sponzor.setSponzorID(3L);
        assertEquals(" SponzorID = 3", sponzor.uslov());
    }

    @Test
    public void testKoloneZaInsert() {
        assertEquals("", sponzor.koloneZaInsert());
    }

    @Test
    public void testVrednostiZaInsert() {
        assertEquals("", sponzor.vrednostiZaInsert());
    }

    @Test
    public void testVrednostiZaUpdate() {
        assertEquals("", sponzor.vrednostiZaUpdate());
    }

    @Test
    public void testUslovZaSelect() {
        assertEquals("", sponzor.uslovZaSelect());
    }
}
