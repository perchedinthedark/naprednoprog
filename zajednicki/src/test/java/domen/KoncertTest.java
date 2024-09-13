package domen;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class KoncertTest {

    private Koncert koncert;
    private Sponzor sponzor;
    private Bina bina;
    private Administrator administrator;
    private ArrayList<Izvodjac> izvodjaci;

    @BeforeEach
    public void setUp() {
        sponzor = new Sponzor(1L, "Coca-Cola", 5000.0, "Zlatni");
        bina = new Bina(1L, "Main Stage", 10000, null, null);
        administrator = new Administrator(1L, "John", "Doe", "john.doe", "password");
        izvodjaci = new ArrayList<>();
        izvodjaci.add(new Izvodjac(null, 1, "Napomena", new Muzicar(1L, "Band A", "Guitar", "Rock", "band@example.com")));
        
        koncert = new Koncert(1L, new Date(), new Date(), 5000, sponzor, bina, administrator, izvodjaci);
    }

    @Test
    public void testDefaultConstructor() {
        Koncert emptyKoncert = new Koncert();
        assertNull(emptyKoncert.getKoncertID());
        assertNull(emptyKoncert.getDatumPocetka());
        assertNull(emptyKoncert.getDatumZavrsetka());
        assertEquals(0, emptyKoncert.getKapacitetKoncerta());
        assertNull(emptyKoncert.getSponzor());
        assertNull(emptyKoncert.getBina());
        assertNull(emptyKoncert.getAdministrator());
        assertNull(emptyKoncert.getIzvodjaci());
    }

    @Test
    public void testParameterizedConstructor() {
        assertEquals(1L, koncert.getKoncertID());
        assertNotNull(koncert.getDatumPocetka());
        assertNotNull(koncert.getDatumZavrsetka());
        assertEquals(5000, koncert.getKapacitetKoncerta());
        assertEquals(sponzor, koncert.getSponzor());
        assertEquals(bina, koncert.getBina());
        assertEquals(administrator, koncert.getAdministrator());
        assertEquals(izvodjaci, koncert.getIzvodjaci());
    }

    @Test
    public void testGetAndSetKoncertID() {
        assertEquals(1L, koncert.getKoncertID());
        koncert.setKoncertID(2L);
        assertEquals(2L, koncert.getKoncertID());
    }

    @Test
    public void testGetAndSetDatumPocetka() {
        Date newDate = new Date();
        koncert.setDatumPocetka(newDate);
        assertEquals(newDate, koncert.getDatumPocetka());
    }

    @Test
    public void testGetAndSetDatumZavrsetka() {
        Date newDate = new Date();
        koncert.setDatumZavrsetka(newDate);
        assertEquals(newDate, koncert.getDatumZavrsetka());
    }

    @Test
    public void testGetAndSetKapacitetKoncerta() {
        assertEquals(5000, koncert.getKapacitetKoncerta());
        koncert.setKapacitetKoncerta(10000);
        assertEquals(10000, koncert.getKapacitetKoncerta());
    }

    @Test
    public void testGetAndSetSponzor() {
        assertEquals(sponzor, koncert.getSponzor());
        Sponzor newSponzor = new Sponzor(2L, "Pepsi", 7000.0, "Srebrni");
        koncert.setSponzor(newSponzor);
        assertEquals(newSponzor, koncert.getSponzor());
    }

    @Test
    public void testGetAndSetBina() {
        assertEquals(bina, koncert.getBina());
        Bina newBina = new Bina(2L, "Second Stage", 8000, null, null);
        koncert.setBina(newBina);
        assertEquals(newBina, koncert.getBina());
    }

    @Test
    public void testGetAndSetAdministrator() {
        assertEquals(administrator, koncert.getAdministrator());
        Administrator newAdmin = new Administrator(2L, "Jane", "Smith", "jane.smith", "password123");
        koncert.setAdministrator(newAdmin);
        assertEquals(newAdmin, koncert.getAdministrator());
    }

    @Test
    public void testGetAndSetIzvodjaci() {
        assertEquals(izvodjaci, koncert.getIzvodjaci());
        ArrayList<Izvodjac> newIzvodjaci = new ArrayList<>();
        newIzvodjaci.add(new Izvodjac(null, 2, "Napomena 2", new Muzicar(2L, "Band B", "Drums", "Jazz", "bandb@example.com")));
        koncert.setIzvodjaci(newIzvodjaci);
        assertEquals(newIzvodjaci, koncert.getIzvodjaci());
    }

    @Test
    public void testNazivTabele() {
        assertEquals(" Koncert ", koncert.nazivTabele());
    }

    @Test
    public void testAlijas() {
        assertEquals(" k ", koncert.alijas());
    }

    @Test
    public void testJoin() {
        String expectedJoin = " JOIN SPONZOR S ON (S.SPONZORID = K.SPONZORID) "
                            + "JOIN BINA B ON (B.BINAID = K.BINAID) "
                            + "JOIN LOKACIJA L ON (B.LOKACIJAID = L.LOKACIJAID) "
                            + "JOIN OPREMA O ON (B.OPREMAID = O.OPREMAID) "
                            + "JOIN ADMINISTRATOR A ON (A.ADMINISTRATORID = K.ADMINISTRATORID)";
        assertEquals(expectedJoin, koncert.join());
    }

    @Test
    public void testVrednostiZaInsert() {
        String expectedInsertValues = "'" + new java.sql.Date(koncert.getDatumPocetka().getTime()) + "', "
                                    + "'" + new java.sql.Date(koncert.getDatumZavrsetka().getTime()) + "', "
                                    + " 5000, " + sponzor.getSponzorID() + ", " + bina.getBinaID() + ", " + administrator.getAdministratorID();
        assertEquals(expectedInsertValues, koncert.vrednostiZaInsert());
    }

    @Test
    public void testUslov() {
        assertEquals(" KoncertID = 1", koncert.uslov());
    }

    @Test
    public void testToJSON() {
        JSONObject json = koncert.toJSON();
        assertEquals(koncert.getKoncertID(), json.getLong("koncertID"));
        assertEquals(koncert.getDatumPocetka().toString(), json.getString("datumPocetka"));
        assertEquals(koncert.getDatumZavrsetka().toString(), json.getString("datumZavrsetka"));
        assertEquals(koncert.getKapacitetKoncerta(), json.getInt("kapacitet"));
        assertEquals(koncert.getSponzor().getNaziv(), json.getString("sponzor"));
        assertEquals(koncert.getBina().getNaziv(), json.getString("bina"));

        assertTrue(json.has("izvodjaci"));
    }
}
