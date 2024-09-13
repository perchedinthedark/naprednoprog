package domen;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BinaTest {

    // Test constructor and getter methods
    @Test
    public void testBinaConstructorAndGetters() {
        Lokacija lokacija = new Lokacija(1L, "Lokacija A", "Adresa A", "Tip A", "Kontakt A", "Vlasnik A");
        Oprema oprema = new Oprema(1L, "Oprema A", "Opis A", 1000.0);
        
        Bina bina = new Bina(1L, "Bina A", 500, lokacija, oprema);

        assertEquals(1L, bina.getBinaID());
        assertEquals("Bina A", bina.getNaziv());
        assertEquals(500, bina.getKapacitet());
        assertEquals(lokacija, bina.getLokacija());
        assertEquals(oprema, bina.getOprema());
    }

    // Test setters and ensure they modify the object correctly
    @Test
    public void testSetters() {
        Bina bina = new Bina();
        Lokacija lokacija = new Lokacija(2L, "Lokacija B", "Adresa B", "Tip B", "Kontakt B", "Vlasnik B");
        Oprema oprema = new Oprema(2L, "Oprema B", "Opis B", 2000.0);

        bina.setBinaID(2L);
        bina.setNaziv("Bina B");
        bina.setKapacitet(1000);
        bina.setLokacija(lokacija);
        bina.setOprema(oprema);

        assertEquals(2L, bina.getBinaID());
        assertEquals("Bina B", bina.getNaziv());
        assertEquals(1000, bina.getKapacitet());
        assertEquals(lokacija, bina.getLokacija());
        assertEquals(oprema, bina.getOprema());
    }

    // Test toString method to ensure it returns the correct name
    @Test
    public void testToString() {
        Bina bina = new Bina(1L, "Bina A", 500, null, null);
        assertEquals("Bina A", bina.toString());
    }

    // Test edge case where fields are null or not set
    @Test
    public void testBinaWithNullFields() {
        Bina bina = new Bina(null, null, 0, null, null);
        
        assertNull(bina.getBinaID());
        assertNull(bina.getNaziv());
        assertNull(bina.getLokacija());
        assertNull(bina.getOprema());
        assertEquals(0, bina.getKapacitet());
    }

    // Test that the join method returns the correct SQL JOIN string
    @Test
    public void testJoinMethod() {
        Bina bina = new Bina();
        String expectedJoin = " JOIN LOKACIJA L ON (B.LOKACIJAID = L.LOKACIJAID) "
                + "JOIN OPREMA O ON (B.OPREMAID = O.OPREMAID) ";
        assertEquals(expectedJoin, bina.join());
    }

    @Test
    public void testUslovMethod() {
        // Create a Bina object with a specific BinaID
        Bina bina = new Bina();
        bina.setBinaID(1L);

        // The expected SQL WHERE clause condition based on the BinaID
        String expectedUslov = " binaID = 1";

        // Assert that the uslov method generates the correct SQL condition
        assertEquals(expectedUslov, bina.uslov());
    }
    

    // Test that the koloneZaInsert method returns an empty string (since not implemented)
    @Test
    public void testKoloneZaInsert() {
        Bina bina = new Bina();
        assertEquals("", bina.koloneZaInsert());
    }

    // Test that the vrednostiZaInsert method returns an empty string (since not implemented)
    @Test
    public void testVrednostiZaInsert() {
        Bina bina = new Bina();
        assertEquals("", bina.vrednostiZaInsert());
    }

    // Test that the vrednostiZaUpdate method returns an empty string (since not implemented)
    @Test
    public void testVrednostiZaUpdate() {
        Bina bina = new Bina();
        assertEquals("", bina.vrednostiZaUpdate());
    }

    // Test that the uslovZaSelect method returns an empty string (since not implemented)
    @Test
    public void testUslovZaSelect() {
        Bina bina = new Bina();
        assertEquals("", bina.uslovZaSelect());
    }
}
   