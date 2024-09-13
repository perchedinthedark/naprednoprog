package so.izvodjaci;

import dbb.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.Izvodjac;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import so.izvodjaci.SOVratiIzvodjace;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SOVratiIzvodjaceTest {

    private SOVratiIzvodjace soVratiIzvodjace;
    private DBBroker mockDBBroker;
    private Izvodjac izvodjac;

    @BeforeEach
    public void setUp() throws Exception {
        soVratiIzvodjace = new SOVratiIzvodjace();
        mockDBBroker = mock(DBBroker.class);
        izvodjac = new Izvodjac(); // Example Izvodjac object

        // Use reflection to set the private instance field in DBBroker
        Field instanceField = DBBroker.class.getDeclaredField("instance");
        instanceField.setAccessible(true); // Make the field accessible
        instanceField.set(null, mockDBBroker); // Set the mock to the instance field
    }

    @Test
    public void testValidateValidIzvodjac() {
        assertDoesNotThrow(() -> soVratiIzvodjace.validate(izvodjac));
    }

    @Test
    public void testValidateInvalidObject() {
        ApstraktniDomenskiObjekat invalidObject = Mockito.mock(ApstraktniDomenskiObjekat.class);
        Exception exception = assertThrows(Exception.class, () -> soVratiIzvodjace.validate(invalidObject));
        assertEquals("Prosledjeni objekat nije instanca klase Izvodjac!", exception.getMessage());
    }

    @Test
    public void testExecuteWithValidIzvodjac() throws Exception {
        // Mock the behavior of DBBroker to return a list of Izvodjac objects
        ArrayList<ApstraktniDomenskiObjekat> mockIzvodjacList = new ArrayList<>();
        mockIzvodjacList.add(izvodjac);
        when(mockDBBroker.vrati(izvodjac)).thenReturn(mockIzvodjacList);

        // Execute the system operation
        soVratiIzvodjace.execute(izvodjac);

        // Verify that the list of Izvodjaci is populated
        ArrayList<Izvodjac> listaIzvodjaca = soVratiIzvodjace.getLista();
        assertNotNull(listaIzvodjaca);
        assertEquals(1, listaIzvodjaca.size());
        assertEquals(izvodjac, listaIzvodjaca.get(0));

        // Verify that DBBroker's vrati method was called with the correct argument
        verify(mockDBBroker, times(1)).vrati(izvodjac);
    }

    @Test
    public void testExecuteWithException() throws Exception {
        // Mock DBBroker to throw an exception
        when(mockDBBroker.vrati(izvodjac)).thenThrow(new SQLException("Database error"));

        Exception exception = assertThrows(Exception.class, () -> soVratiIzvodjace.execute(izvodjac));
        assertEquals("Database error", exception.getMessage());
    }
}
