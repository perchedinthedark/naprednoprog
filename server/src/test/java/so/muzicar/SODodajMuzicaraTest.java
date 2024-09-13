package so.muzicar;

import dbb.DBBroker;
import domen.Muzicar;
import domen.ApstraktniDomenskiObjekat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import so.muzicar.SODodajMuzicara;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SODodajMuzicaraTest {

    private SODodajMuzicara soDodajMuzicara;
    private DBBroker mockDBBroker;
    private Muzicar muzicar;

    @BeforeEach
    public void setUp() throws Exception {
        soDodajMuzicara = new SODodajMuzicara();
        mockDBBroker = mock(DBBroker.class);
        muzicar = new Muzicar(); // Example Muzicar object

        // Use reflection to set the private instance field in DBBroker
        Field instanceField = DBBroker.class.getDeclaredField("instance");
        instanceField.setAccessible(true); // Make the field accessible
        instanceField.set(null, mockDBBroker); // Set the mock to the instance field
    }

    @Test
    public void testValidateValidMuzicar() throws Exception {
        // Create a list of existing musicians without a conflicting contact
        ArrayList<Muzicar> existingMuzicari = new ArrayList<>();
        when(mockDBBroker.vrati(any(Muzicar.class))).thenReturn((ArrayList<ApstraktniDomenskiObjekat>) (ArrayList<?>) existingMuzicari);

        // No exception should be thrown
        assertDoesNotThrow(() -> soDodajMuzicara.validate(muzicar));
    }

    @Test
    public void testValidateWithDuplicateContact() throws Exception {
        // Set up an existing musician with the same contact info
        Muzicar existingMuzicar = new Muzicar();
        existingMuzicar.setKontakt("123-456");

        // Set the contact info for the new musician
        muzicar.setKontakt("123-456");

        // Mock the behavior of DBBroker to return a list with the existing musician
        ArrayList<Muzicar> existingMuzicari = new ArrayList<>();
        existingMuzicari.add(existingMuzicar);
        when(mockDBBroker.vrati(any(Muzicar.class))).thenReturn((ArrayList<ApstraktniDomenskiObjekat>) (ArrayList<?>) existingMuzicari);

        // Validate should throw an exception due to duplicate contact
        Exception exception = assertThrows(Exception.class, () -> soDodajMuzicara.validate(muzicar));
        assertEquals("Ovaj kontakt vec postoji u bazi!", exception.getMessage());
    }

    @Test
    public void testExecuteValidMuzicar() throws Exception {
        // Mock the PreparedStatement that the dodaj method will return
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);

        // Mock the behavior of DBBroker's dodaj method to return the mocked PreparedStatement
        when(mockDBBroker.dodaj(muzicar)).thenReturn(mockPreparedStatement);

        // Execute the operation
        soDodajMuzicara.execute(muzicar);

        // Verify that the dodaj method was called once with the correct argument
        verify(mockDBBroker, times(1)).dodaj(muzicar);
    }

    @Test
    public void testExecuteWithSQLException() throws Exception {
        // Mock DBBroker to throw an SQLException when adding the musician
        doThrow(new SQLException("Database error")).when(mockDBBroker).dodaj(muzicar);

        // Execute should throw an exception due to the SQL error
        Exception exception = assertThrows(Exception.class, () -> soDodajMuzicara.execute(muzicar));
        assertEquals("Database error", exception.getMessage());
    }
}
