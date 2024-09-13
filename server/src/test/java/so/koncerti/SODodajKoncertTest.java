package so.koncerti;

import dbb.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.Izvodjac;
import domen.Koncert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import so.koncerti.SODodajKoncert;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SODodajKoncertTest {

    private SODodajKoncert soDodajKoncert;
    private DBBroker mockDBBroker;
    private Koncert koncert;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;

    @BeforeEach
    public void setUp() throws Exception {
        soDodajKoncert = new SODodajKoncert();
        mockDBBroker = mock(DBBroker.class);
        koncert = new Koncert();
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);

        // Use reflection to set the private instance field in DBBroker
        Field instanceField = DBBroker.class.getDeclaredField("instance");
        instanceField.setAccessible(true);
        instanceField.set(null, mockDBBroker);
    }

    @Test
    public void testValidateValidKoncert() throws Exception {
        // Set up valid concert
        koncert.setDatumPocetka(new Timestamp(new Date().getTime() + 86400000)); // 1 day in future
        koncert.setDatumZavrsetka(new Timestamp(new Date().getTime() + 172800000)); // 2 days in future
        koncert.setKapacitetKoncerta(50);
        ArrayList<Izvodjac> izvodjaci = new ArrayList<>();
        izvodjaci.add(new Izvodjac());
        izvodjaci.add(new Izvodjac());
        koncert.setIzvodjaci(izvodjaci);

        // Validate without throwing exceptions
        assertDoesNotThrow(() -> soDodajKoncert.validate(koncert));
    }

    @Test
    public void testValidateInvalidKapacitet() {
        // Set up invalid capacity
        koncert.setDatumPocetka(new Timestamp(new Date().getTime() + 86400000)); // 1 day in future
        koncert.setDatumZavrsetka(new Timestamp(new Date().getTime() + 172800000)); // 2 days in future
        koncert.setKapacitetKoncerta(1); // Invalid capacity
        koncert.setIzvodjaci(new ArrayList<>());

        Exception exception = assertThrows(Exception.class, () -> soDodajKoncert.validate(koncert));
        assertEquals("Kapacitet koncerta mora biti izmedju 2 i 100!", exception.getMessage());
    }

    @Test
    public void testValidateInvalidIzvodjaci() {
        // Set up concert with less than 2 performers
        koncert.setDatumPocetka(new Timestamp(new Date().getTime() + 86400000)); // 1 day in future
        koncert.setDatumZavrsetka(new Timestamp(new Date().getTime() + 172800000)); // 2 days in future
        koncert.setKapacitetKoncerta(50);
        koncert.setIzvodjaci(new ArrayList<>()); // No performers

        Exception exception = assertThrows(Exception.class, () -> soDodajKoncert.validate(koncert));
        assertEquals("Koncert mora imati minimalno 2 izvodjaca!", exception.getMessage());
    }

    @Test
    public void testExecuteValidKoncert() throws Exception {
        // Set up valid concert
        koncert.setDatumPocetka(new Timestamp(new Date().getTime() + 86400000)); // 1 day in future
        koncert.setDatumZavrsetka(new Timestamp(new Date().getTime() + 172800000)); // 2 days in future
        koncert.setKapacitetKoncerta(50);
        ArrayList<Izvodjac> izvodjaci = new ArrayList<>();
        izvodjaci.add(new Izvodjac());
        izvodjaci.add(new Izvodjac());
        koncert.setIzvodjaci(izvodjaci);

        // Mock behavior for adding concert and fetching generated keys
        when(mockDBBroker.dodaj(koncert)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.getGeneratedKeys()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getLong(1)).thenReturn(1L);

        // Execute the system operation
        soDodajKoncert.execute(koncert);

        // Verify that DBBroker's dodaj method was called with the correct argument for the concert
        verify(mockDBBroker, times(1)).dodaj(koncert);

        // Verify that DBBroker's dodaj method was called for each performer
        verify(mockDBBroker, times(2)).dodaj(any(Izvodjac.class));
    }

    @Test
    public void testExecuteWithException() throws Exception {
        // Mock DBBroker to throw an exception
        when(mockDBBroker.dodaj(koncert)).thenThrow(new SQLException("Database error"));

        Exception exception = assertThrows(Exception.class, () -> soDodajKoncert.execute(koncert));
        assertEquals("Database error", exception.getMessage());
    }
}
