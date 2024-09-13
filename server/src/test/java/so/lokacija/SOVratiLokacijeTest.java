package so.lokacija;

import dbb.DBBroker;
import domen.Lokacija;
import domen.ApstraktniDomenskiObjekat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import so.lokacija.SOVratiLokacije;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SOVratiLokacijeTest {

    private SOVratiLokacije soVratiLokacije;
    private DBBroker mockDBBroker;
    private Lokacija lokacija;

    @BeforeEach
    public void setUp() throws Exception {
        soVratiLokacije = new SOVratiLokacije();
        mockDBBroker = mock(DBBroker.class);
        lokacija = new Lokacija(); // Example Lokacija object

        // Use reflection to set the private instance field in DBBroker
        Field instanceField = DBBroker.class.getDeclaredField("instance");
        instanceField.setAccessible(true); // Make the field accessible
        instanceField.set(null, mockDBBroker); // Set the mock to the instance field
    }

    @Test
    public void testValidateValidLokacija() {
        assertDoesNotThrow(() -> soVratiLokacije.validate(lokacija));
    }

    @Test
    public void testValidateInvalidObject() {
        ApstraktniDomenskiObjekat invalidObject = Mockito.mock(ApstraktniDomenskiObjekat.class);
        Exception exception = assertThrows(Exception.class, () -> soVratiLokacije.validate(invalidObject));
        assertEquals("Prosledjeni objekat nije instanca klase Lokacija!", exception.getMessage());
    }

    @Test
    public void testExecuteWithValidLokacija() throws Exception {
        // Mock the behavior of DBBroker to return a list of Lokacija objects
        ArrayList<ApstraktniDomenskiObjekat> mockLokacijeList = new ArrayList<>();
        mockLokacijeList.add(lokacija);
        when(mockDBBroker.vrati(lokacija)).thenReturn(mockLokacijeList);

        // Execute the system operation
        soVratiLokacije.execute(lokacija);

        // Verify that the list of Lokacija is populated
        ArrayList<Lokacija> listaLokacija = soVratiLokacije.getLista();
        assertNotNull(listaLokacija);
        assertEquals(1, listaLokacija.size());
        assertEquals(lokacija, listaLokacija.get(0));

        // Verify that DBBroker's vrati method was called with the correct argument
        verify(mockDBBroker, times(1)).vrati(lokacija);
    }

    @Test
    public void testExecuteWithException() throws Exception {
        // Mock DBBroker to throw an exception
        when(mockDBBroker.vrati(lokacija)).thenThrow(new SQLException("Database error"));

        Exception exception = assertThrows(Exception.class, () -> soVratiLokacije.execute(lokacija));
        assertEquals("Database error", exception.getMessage());
    }
}
