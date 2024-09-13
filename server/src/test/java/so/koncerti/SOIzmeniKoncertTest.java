package so.koncerti;

import dbb.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.Izvodjac;
import domen.Koncert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import so.koncerti.SOIzmeniKoncert;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SOIzmeniKoncertTest {

    private SOIzmeniKoncert soIzmeniKoncert;
    private DBBroker mockDBBroker;
    private Koncert koncert;

    @BeforeEach
    public void setUp() throws Exception {
        soIzmeniKoncert = new SOIzmeniKoncert();
        mockDBBroker = mock(DBBroker.class);
        koncert = new Koncert();

        // Use reflection to set the private instance field in DBBroker
        Field instanceField = DBBroker.class.getDeclaredField("instance");
        instanceField.setAccessible(true);
        instanceField.set(null, mockDBBroker);
    }

    @Test
    public void testValidateValidKoncert() throws Exception {
        // Set up a valid concert
        koncert.setDatumPocetka(new Timestamp(new Date().getTime() + 86400000)); // 1 day in future
        koncert.setDatumZavrsetka(new Timestamp(new Date().getTime() + 172800000)); // 2 days in future
        koncert.setKapacitetKoncerta(50);
        ArrayList<Izvodjac> izvodjaci = new ArrayList<>();
        izvodjaci.add(new Izvodjac());
        izvodjaci.add(new Izvodjac());
        koncert.setIzvodjaci(izvodjaci);

        // Validate without throwing exceptions
        assertDoesNotThrow(() -> soIzmeniKoncert.validate(koncert));
    }

    @Test
    public void testValidateInvalidKapacitet() {
        // Set up invalid concert capacity
        koncert.setDatumPocetka(new Timestamp(new Date().getTime() + 86400000)); // 1 day in future
        koncert.setDatumZavrsetka(new Timestamp(new Date().getTime() + 172800000)); // 2 days in future
        koncert.setKapacitetKoncerta(1); // Invalid capacity
        koncert.setIzvodjaci(new ArrayList<>());

        Exception exception = assertThrows(Exception.class, () -> soIzmeniKoncert.validate(koncert));
        assertEquals("Kapacitet grupe mora biti izmedju 2 i 100!", exception.getMessage());
    }

    @Test
    public void testValidateInvalidIzvodjaci() {
        // Set up a concert with fewer than 2 performers
        koncert.setDatumPocetka(new Timestamp(new Date().getTime() + 86400000)); // 1 day in future
        koncert.setDatumZavrsetka(new Timestamp(new Date().getTime() + 172800000)); // 2 days in future
        koncert.setKapacitetKoncerta(50);
        koncert.setIzvodjaci(new ArrayList<>()); // No performers

        Exception exception = assertThrows(Exception.class, () -> soIzmeniKoncert.validate(koncert));
        assertEquals("Koncert mora imati minimalno 2 izvodjaca!", exception.getMessage());
    }

    @Test
    public void testExecuteValidKoncert() throws Exception {
        // Set up a valid concert
        koncert.setDatumPocetka(new Timestamp(new Date().getTime() + 86400000)); // 1 day in future
        koncert.setDatumZavrsetka(new Timestamp(new Date().getTime() + 172800000)); // 2 days in future
        koncert.setKapacitetKoncerta(50);
        ArrayList<Izvodjac> izvodjaci = new ArrayList<>();
        Izvodjac izvodjac1 = new Izvodjac();
        Izvodjac izvodjac2 = new Izvodjac();
        izvodjaci.add(izvodjac1);
        izvodjaci.add(izvodjac2);
        koncert.setIzvodjaci(izvodjaci);

        // Execute the system operation
        soIzmeniKoncert.execute(koncert);

        // Verify that DBBroker's izmeni method was called for the concert
        verify(mockDBBroker, times(1)).izmeni(koncert);

        // Verify that DBBroker's obrisi method was called for the first performer
        verify(mockDBBroker, times(1)).obrisi(izvodjac1);

        // Verify that DBBroker's dodaj method was called for each performer
        verify(mockDBBroker, times(2)).dodaj(any(Izvodjac.class));
    }

    @Test
    public void testExecuteWithException() throws Exception {
        // Mock DBBroker to throw an exception when modifying a concert
        doThrow(new SQLException("Database error")).when(mockDBBroker).izmeni(koncert);

        Exception exception = assertThrows(Exception.class, () -> soIzmeniKoncert.execute(koncert));
        assertEquals("Database error", exception.getMessage());
    }
}
