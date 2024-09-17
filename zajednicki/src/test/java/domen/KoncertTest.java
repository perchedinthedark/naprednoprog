package domen;


import static org.junit.jupiter.api.Assertions.*;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Timestamp;
import java.util.ArrayList;

public class KoncertTest {

    private Koncert koncert;
    private Bina bina;
    private Sponzor sponzor;
    private Administrator administrator;
    private Muzicar muzicar;
    private Izvodjac izvodjac1, izvodjac2;

    @BeforeEach
    public void setUp() {
      
        Timestamp timestampPocetka = Timestamp.valueOf("2023-09-15 18:00:00");
        Timestamp timestampZavrsetka = Timestamp.valueOf("2023-09-15 20:00:00");

      
        Lokacija lokacija = new Lokacija(1L, "Lokacija A", "Adresa A", "Tip A", "Kontakt A", "Vlasnik A");
 	    Oprema oprema = new Oprema(1L, "Oprema A", "Opis A", 1000.0);
        bina = new Bina(1L, "Bina A", 500, lokacija, oprema);
        sponzor = new Sponzor(1L, "SponsorName", 10000.0, "Financial");
        administrator = new Administrator(1L, "Admin", "AdminLastName", "admin", "password");

    
        koncert = new Koncert(1L, timestampPocetka, timestampZavrsetka, 5000, sponzor, bina, administrator, null);

        muzicar = new Muzicar(1L, "John Doe", "Guitar", "Rock", "john@example.com");
        izvodjac1 = new Izvodjac(koncert, 1, "Lead guitarist", muzicar);

        muzicar = new Muzicar(2L, "Jane Smith", "Vocals", "Rock", "jane@example.com");
        izvodjac2 = new Izvodjac(koncert, 2, "Vocalist", muzicar);
      
        ArrayList<Izvodjac> izvodjaci = new ArrayList<>();
        izvodjaci.add(izvodjac1);
        izvodjaci.add(izvodjac2);

        koncert.setIzvodjaci(izvodjaci);

    }

    @Test
    public void testKoncertToJSON() {
        
        JSONObject json = koncert.toJSON();

        assertEquals(1L, json.getLong("koncertID"));
        assertEquals("2023-09-15 18:00:00.0", json.getString("datumPocetka"));
        assertEquals("2023-09-15 20:00:00.0", json.getString("datumZavrsetka"));
        assertEquals(5000, json.getInt("kapacitet"));
        assertEquals("SponsorName", json.getString("sponzor"));
        assertEquals("Bina A", json.getString("bina"));

       
        assertTrue(json.has("izvodjaci"));
        assertEquals(2, json.getJSONArray("izvodjaci").length());

      
        JSONObject izvodjac1JSON = json.getJSONArray("izvodjaci").getJSONObject(0);
        assertEquals("John Doe", izvodjac1JSON.getString("ime"));
        assertEquals("Guitar", izvodjac1JSON.getString("instrument"));

        JSONObject izvodjac2JSON = json.getJSONArray("izvodjaci").getJSONObject(1);
        assertEquals("Jane Smith", izvodjac2JSON.getString("ime"));
        assertEquals("Vocals", izvodjac2JSON.getString("instrument"));
    }



    @Test
    public void testGettersAndSetters() {
        // Test getter methods
        assertEquals(1L, koncert.getKoncertID());
        assertEquals(Timestamp.valueOf("2023-09-15 18:00:00"), koncert.getDatumPocetka());
        assertEquals(Timestamp.valueOf("2023-09-15 20:00:00"), koncert.getDatumZavrsetka());
        assertEquals(5000, koncert.getKapacitetKoncerta());
        assertEquals(bina, koncert.getBina());
        assertEquals(sponzor, koncert.getSponzor());
        assertEquals(administrator, koncert.getAdministrator());
        assertEquals(2, koncert.getIzvodjaci().size());

      
        Timestamp newTimestamp = Timestamp.valueOf("2023-10-01 18:00:00");
        koncert.setDatumPocetka(newTimestamp);
        assertEquals(newTimestamp, koncert.getDatumPocetka());

        koncert.setKapacitetKoncerta(6000);
        assertEquals(6000, koncert.getKapacitetKoncerta());
    }
    
    @Test
    public void testInvalidDatumPocetkaThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Koncert(1L, null, Timestamp.valueOf("2023-09-15 20:00:00"), 5000, sponzor, bina, administrator, null);
        });
        assertEquals("Datum početka ne može biti null.", exception.getMessage());
    }

    @Test
    public void testInvalidDatumZavrsetkaThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Koncert(1L, Timestamp.valueOf("2023-09-15 18:00:00"), null, 5000, sponzor, bina, administrator, null);
        });
        assertEquals("Datum završetka ne može biti null.", exception.getMessage());
    }

    @Test
    public void testInvalidSponzorThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Koncert(1L, Timestamp.valueOf("2023-09-15 18:00:00"), Timestamp.valueOf("2023-09-15 20:00:00"), 5000, null, bina, administrator, null);
        });
        assertEquals("Sponzor ne može biti null.", exception.getMessage());
    }

    @Test
    public void testInvalidBinaThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Koncert(1L, Timestamp.valueOf("2023-09-15 18:00:00"), Timestamp.valueOf("2023-09-15 20:00:00"), 5000, sponzor, null, administrator, null);
        });
        assertEquals("Bina ne može biti null.", exception.getMessage());
    }

    @Test
    public void testInvalidAdministratorThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Koncert(1L, Timestamp.valueOf("2023-09-15 18:00:00"), Timestamp.valueOf("2023-09-15 20:00:00"), 5000, sponzor, bina, null, null);
        });
        assertEquals("Administrator ne može biti null.", exception.getMessage());
    }

    @Test
    public void testInvalidKapacitetThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Koncert(1L, Timestamp.valueOf("2023-09-15 18:00:00"), Timestamp.valueOf("2023-09-15 20:00:00"), -10, sponzor, bina, administrator, null);
        });
        assertEquals("Kapacitet mora biti pozitivan broj.", exception.getMessage());
    }

}
