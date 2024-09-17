package domen;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;

public class IzvodjacTest {

    private Izvodjac izvodjac;
    private Koncert koncert;
    private Muzicar muzicar;
    private Sponzor sponzor;
    private Bina bina;
    private Administrator administrator;

    @BeforeEach
    public void setUp() {
    	
        Timestamp datumPocetka = Timestamp.valueOf("2024-09-17 20:00:00");
        Timestamp datumZavrsetka = Timestamp.valueOf("2024-09-17 22:00:00");
        
     
        sponzor = new Sponzor(1L, "Sponzor A", 5000.0, "Finansijski");
        Lokacija lokacija = new Lokacija(1L, "Lokacija A", "Adresa A", "Tip A", "Kontakt A", "Vlasnik A");
 	    Oprema oprema = new Oprema(1L, "Oprema A", "Opis A", 1000.0);
        bina = new Bina(1L, "Bina A", 500, lokacija, oprema);
        administrator = new Administrator(1L, "John", "Doe", "admin", "password");
        
        
        koncert = new Koncert(1L, datumPocetka, datumZavrsetka, 100, sponzor, bina, administrator, null);
        muzicar = new Muzicar(1L, "John", "Guitar", "Rock", "john@example.com");
        izvodjac = new Izvodjac(koncert, 1, "Napomena", muzicar);
    }
    
    @Test
    public void testInvalidKoncertThrowsException() {
       
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Izvodjac(null, 1, "Napomena", muzicar);
        });

        assertEquals("Koncert ne može biti null.", exception.getMessage());
    }

    @Test
    public void testInvalidMuzicarThrowsException() {
     
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Izvodjac(koncert, 1, "Napomena", null);
        });

        assertEquals("Muzičar ne može biti null.", exception.getMessage());
    }

    @Test
    public void testInvalidRbIzvodjacaThrowsException() {
       
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Izvodjac(koncert, 0, "Napomena", muzicar);
        });

        assertEquals("Redni broj izvođača mora biti pozitivan broj.", exception.getMessage());
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

       
         Timestamp datumPocetka = Timestamp.valueOf("2024-09-18 18:00:00");
         Timestamp datumZavrsetka = Timestamp.valueOf("2024-09-18 20:00:00");
         Sponzor newSponzor = new Sponzor(2L, "Sponzor B", 7000.0, "Medijski");
         Lokacija lokacija = new Lokacija(1L, "Lokacija A", "Adresa A", "Tip A", "Kontakt A", "Vlasnik A");
  	     Oprema oprema = new Oprema(1L, "Oprema A", "Opis A", 1000.0);
         Bina newBina = new Bina(1L, "Bina A", 500, lokacija, oprema);
         Administrator newAdmin = new Administrator(2L, "Jane", "Doe", "admin2", "password2");

         Koncert newKoncert = new Koncert(2L, datumPocetka, datumZavrsetka, 200, newSponzor, newBina, newAdmin, null);
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
