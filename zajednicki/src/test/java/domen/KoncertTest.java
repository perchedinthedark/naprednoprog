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
        // Set up a sample timestamp for the test
        Timestamp timestampPocetka = Timestamp.valueOf("2023-09-15 18:00:00");
        Timestamp timestampZavrsetka = Timestamp.valueOf("2023-09-15 20:00:00");

        // Setting up sample objects for the concert
        bina = new Bina(1L, "Main Stage", 5000, null, null);
        sponzor = new Sponzor(1L, "SponsorName", 10000.0, "Financial");
        administrator = new Administrator(1L, "Admin", "AdminLastName", "admin", "password");

        muzicar = new Muzicar(1L, "John Doe", "Guitar", "Rock", "john@example.com");
        izvodjac1 = new Izvodjac(null, 1, "Lead guitarist", muzicar);

        muzicar = new Muzicar(2L, "Jane Smith", "Vocals", "Rock", "jane@example.com");
        izvodjac2 = new Izvodjac(null, 2, "Vocalist", muzicar);

        ArrayList<Izvodjac> izvodjaci = new ArrayList<>();
        izvodjaci.add(izvodjac1);
        izvodjaci.add(izvodjac2);

        // Initialize Koncert object with the timestamp values
        koncert = new Koncert(1L, timestampPocetka, timestampZavrsetka, 5000, sponzor, bina, administrator, izvodjaci);
    }

    @Test
    public void testKoncertToJSON() {
        // Test the toJSON method of Koncert class
        JSONObject json = koncert.toJSON();

        assertEquals(1L, json.getLong("koncertID"));
        assertEquals("2023-09-15 18:00:00.0", json.getString("datumPocetka"));
        assertEquals("2023-09-15 20:00:00.0", json.getString("datumZavrsetka"));
        assertEquals(5000, json.getInt("kapacitet"));
        assertEquals("SponsorName", json.getString("sponzor"));
        assertEquals("Main Stage", json.getString("bina"));

        // Check the array of Izvodjac objects
        assertTrue(json.has("izvodjaci"));
        assertEquals(2, json.getJSONArray("izvodjaci").length());

        // Check individual izvodjac objects within the array
        JSONObject izvodjac1JSON = json.getJSONArray("izvodjaci").getJSONObject(0);
        assertEquals("John Doe", izvodjac1JSON.getString("ime"));
        assertEquals("Guitar", izvodjac1JSON.getString("instrument"));

        JSONObject izvodjac2JSON = json.getJSONArray("izvodjaci").getJSONObject(1);
        assertEquals("Jane Smith", izvodjac2JSON.getString("ime"));
        assertEquals("Vocals", izvodjac2JSON.getString("instrument"));
    }

    @Test
    public void testKoncertNullValues() {
        // Initialize a concert with null values for bina, sponzor, and izvodjaci
        koncert = new Koncert(2L, null, null, 3000, null, null, null, null);

        // Test the toJSON method and ensure null values are handled correctly
        JSONObject json = koncert.toJSON();

        assertEquals(2L, json.getLong("koncertID"));
        assertEquals(JSONObject.NULL, json.get("datumPocetka"));
        assertEquals(JSONObject.NULL, json.get("datumZavrsetka"));
        assertEquals(3000, json.getInt("kapacitet"));
        assertEquals(JSONObject.NULL, json.get("sponzor"));
        assertEquals(JSONObject.NULL, json.get("bina"));
        assertEquals(0, json.getJSONArray("izvodjaci").length()); // No izvodjaci
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

        // Test setter methods
        Timestamp newTimestamp = Timestamp.valueOf("2023-10-01 18:00:00");
        koncert.setDatumPocetka(newTimestamp);
        assertEquals(newTimestamp, koncert.getDatumPocetka());

        koncert.setKapacitetKoncerta(6000);
        assertEquals(6000, koncert.getKapacitetKoncerta());
    }
}
