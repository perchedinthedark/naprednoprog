package so.sponzor;

import dbb.DBBroker;
import domen.Sponzor;
import domen.ApstraktniDomenskiObjekat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import so.sponzor.SOVratiSponzore;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SOVratiSponzoreTest {

    private SOVratiSponzore soVratiSponzore;
    private DBBroker mockDBBroker;
    private Sponzor sponzor;

    @BeforeEach
    public void setUp() throws Exception {
        soVratiSponzore = new SOVratiSponzore();
        mockDBBroker = mock(DBBroker.class);
        sponzor = new Sponzor(); // Example Sponzor object

        // Use reflection to set the private instance field in DBBroker
        Field instanceField = DBBroker.class.getDeclaredField("instance");
        instanceField.setAccessible(true); // Make the field accessible
        instanceField.set(null, mockDBBroker); // Set the mock to the instance field
    }

    @Test
    public void testValidateValidSponzor() {
        assertDoesNotThrow(() -> soVratiSponzore.validate(sponzor));
    }

    @Test
    public void testValidateInvalidObject() {
        ApstraktniDomenskiObjekat invalidObject = Mockito.mock(ApstraktniDomenskiObjekat.class);
        Exception exception = assertThrows(Exception.class, () -> soVratiSponzore.validate(invalidObject));
        assertEquals("Prosledjeni objekat nije instanca klase Sponzor!", exception.getMessage());
    }

    @Test
    public void testExecuteWithValidSponzor() throws Exception {
        // Mock the behavior of DBBroker to return a list of Sponzor objects
        ArrayList<ApstraktniDomenskiObjekat> mockSponzorList = new ArrayList<>();
        mockSponzorList.add(sponzor);
        when(mockDBBroker.vrati(sponzor)).thenReturn(mockSponzorList);

        // Execute the system operation
        soVratiSponzore.execute(sponzor);

        // Verify that the list of Sponzori is populated
        ArrayList<Sponzor> listaSponzora = soVratiSponzore.getLista();
        assertNotNull(listaSponzora);
        assertEquals(1, listaSponzora.size());
        assertEquals(sponzor, listaSponzora.get(0));

        // Verify that DBBroker's vrati method was called with the correct argument
        verify(mockDBBroker, times(1)).vrati(sponzor);
    }

    @Test
    public void testExecuteWithException() throws Exception {
        // Mock DBBroker to throw an exception
        when(mockDBBroker.vrati(sponzor)).thenThrow(new SQLException("Database error"));

        Exception exception = assertThrows(Exception.class, () -> soVratiSponzore.execute(sponzor));
        assertEquals("Database error", exception.getMessage());
    }
}
