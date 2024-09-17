package domen;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BinaTest {

    
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

    
    @Test
    public void testToString() {
    	   Lokacija lokacija = new Lokacija(1L, "Lokacija A", "Adresa A", "Tip A", "Kontakt A", "Vlasnik A");
    	   Oprema oprema = new Oprema(1L, "Oprema A", "Opis A", 1000.0);
           Bina bina = new Bina(1L, "Bina A", 500, lokacija, oprema);

           assertEquals("Bina A", bina.toString());
    }

    @Test
    public void testSetNazivInvalid() {
        Bina bina = new Bina();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bina.setNaziv("");
        });
        assertEquals("Naziv ne može biti prazan ili null.", exception.getMessage());
    }

    @Test
    public void testSetKapacitetInvalid() {
        Bina bina = new Bina();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bina.setKapacitet(-1);
        });
        assertEquals("Kapacitet mora biti veći ili jednak nuli.", exception.getMessage());
    }

    @Test
    public void testSetBinaIDInvalid() {
        Bina bina = new Bina();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bina.setBinaID(0L);
        });
        assertEquals("Bina ID mora biti pozitivan broj.", exception.getMessage());
    }
   
    @Test
    public void testJoinMethod() {
        Bina bina = new Bina();
        String expectedJoin = " JOIN LOKACIJA L ON (B.LOKACIJAID = L.LOKACIJAID) "
                + "JOIN OPREMA O ON (B.OPREMAID = O.OPREMAID) ";
        assertEquals(expectedJoin, bina.join());
    }

    @Test
    public void testUslovMethod() {
      
        Bina bina = new Bina();
        bina.setBinaID(1L);

      
        String expectedUslov = " binaID = 1";

    
        assertEquals(expectedUslov, bina.uslov());
    }
    

   
    @Test
    public void testKoloneZaInsert() {
        Bina bina = new Bina();
        assertEquals("", bina.koloneZaInsert());
    }

   
    @Test
    public void testVrednostiZaInsert() {
        Bina bina = new Bina();
        assertEquals("", bina.vrednostiZaInsert());
    }

   
    @Test
    public void testVrednostiZaUpdate() {
        Bina bina = new Bina();
        assertEquals("", bina.vrednostiZaUpdate());
    }

   
    @Test
    public void testUslovZaSelect() {
        Bina bina = new Bina();
        assertEquals("", bina.uslovZaSelect());
    }
}
   