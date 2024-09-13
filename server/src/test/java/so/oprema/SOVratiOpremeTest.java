package so.oprema;

import dbb.DBBroker;
import domen.Oprema;
import domen.ApstraktniDomenskiObjekat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import so.oprema.SOVratiOpreme;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SOVratiOpremeTest {

    private SOVratiOpreme soVratiOpreme;
    private DBBroker mockDBBroker;
    private Oprema oprema;

    @BeforeEach
    public void setUp() throws Exception {
        soVratiOpreme = new SOVratiOpreme();
        mockDBBroker = mock(DBBroker.class);
        oprema = new Oprema(); // Example Oprema object

        // Use reflection to set the private instance field in DBBroker
        Field instanceField = DBBroker.class.getDeclaredField("instance");
        instanceField.setAccessible(true); // Make the field accessible
        instanceField.set(null, mockDBBroker); // Set the mock to the instance field
    }

    @Test
    public void testValidateValidOprema() {
        assertDoesNotThrow(() -> soVratiOpreme.validate(oprema));
    }

    @Test
    public void testValidateInvalidObject() {
        ApstraktniDomenskiObjekat invalidObject = Mockito.mock(ApstraktniDomenskiObjekat.class);
        Exception exception = assertThrows(Exception.class, () -> soVratiOpreme.validate(invalidObject));
        assertEquals("Prosledjeni objekat nije instanca klase Oprema!", exception.getMessage());
    }

    @Test
    public void testExecuteWithValidOprema() throws Exception {
        // Mock the behavior of DBBroker to return a list of Oprema objects
        ArrayList<ApstraktniDomenskiObjekat> mockOpremaList = new ArrayList<>();
        mockOpremaList.add(oprema);
        when(mockDBBroker.vrati(oprema)).thenReturn(mockOpremaList);

        // Execute the system operation
        soVratiOpreme.execute(oprema);

        // Verify that the list of Oprema is populated
        ArrayList<Oprema> listaOprema = soVratiOpreme.getLista();
        assertNotNull(listaOprema);
        assertEquals(1, listaOprema.size());
        assertEquals(oprema, listaOprema.get(0));

        // Verify that DBBroker's vrati method was called with the correct argument
        verify(mockDBBroker, times(1)).vrati(oprema);
    }

    @Test
    public void testExecuteWithException() throws Exception {
        // Mock DBBroker to throw an exception
        when(mockDBBroker.vrati(oprema)).thenThrow(new SQLException("Database error"));

        Exception exception = assertThrows(Exception.class, () -> soVratiOpreme.execute(oprema));
        assertEquals("Database error", exception.getMessage());
    }
}
