package so.muzicar;

import dbb.DBBroker;
import domen.Muzicar;
import domen.ApstraktniDomenskiObjekat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import so.muzicar.SOVratiMuzicare;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SOVratiMuzicareTest {

    private SOVratiMuzicare soVratiMuzicare;
    private DBBroker mockDBBroker;
    private Muzicar muzicar;

    @BeforeEach
    public void setUp() throws Exception {
        soVratiMuzicare = new SOVratiMuzicare();
        mockDBBroker = mock(DBBroker.class);
        muzicar = new Muzicar(); // Example Muzicar object

        // Use reflection to set the private instance field in DBBroker
        Field instanceField = DBBroker.class.getDeclaredField("instance");
        instanceField.setAccessible(true); // Make the field accessible
        instanceField.set(null, mockDBBroker); // Set the mock to the instance field
    }

    @Test
    public void testValidateValidMuzicar() {
        assertDoesNotThrow(() -> soVratiMuzicare.validate(muzicar));
    }

    @Test
    public void testValidateInvalidObject() {
        ApstraktniDomenskiObjekat invalidObject = Mockito.mock(ApstraktniDomenskiObjekat.class);
        Exception exception = assertThrows(Exception.class, () -> soVratiMuzicare.validate(invalidObject));
        assertEquals("Prosledjeni objekat nije instanca klase Muzicar!", exception.getMessage());
    }

    @Test
    public void testExecuteWithValidMuzicar() throws Exception {
        // Mock the behavior of DBBroker to return a list of Muzicar objects
        ArrayList<ApstraktniDomenskiObjekat> mockMuzicarList = new ArrayList<>();
        mockMuzicarList.add(muzicar);
        when(mockDBBroker.vrati(muzicar)).thenReturn(mockMuzicarList);

        // Execute the system operation
        soVratiMuzicare.execute(muzicar);

        // Verify that the list of Muzicari is populated
        ArrayList<Muzicar> listaMuzicara = soVratiMuzicare.getLista();
        assertNotNull(listaMuzicara);
        assertEquals(1, listaMuzicara.size());
        assertEquals(muzicar, listaMuzicara.get(0));

        // Verify that DBBroker's vrati method was called with the correct argument
        verify(mockDBBroker, times(1)).vrati(muzicar);
    }

    @Test
    public void testExecuteWithException() throws Exception {
        // Mock DBBroker to throw an exception
        when(mockDBBroker.vrati(muzicar)).thenThrow(new SQLException("Database error"));

        Exception exception = assertThrows(Exception.class, () -> soVratiMuzicare.execute(muzicar));
        assertEquals("Database error", exception.getMessage());
    }
}
