package so.muzicar;

import dbb.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.Muzicar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import so.muzicar.SOIzmeniMuzicara;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SOIzmeniMuzicaraTest {

    private SOIzmeniMuzicara soIzmeniMuzicara;
    private DBBroker mockDBBroker;
    private Muzicar muzicar;

    @BeforeEach
    public void setUp() throws Exception {
        soIzmeniMuzicara = new SOIzmeniMuzicara();
        mockDBBroker = mock(DBBroker.class);
        muzicar = new Muzicar(1L, "John", "Guitar", "Rock", "john@music.com");

        // Use reflection to set the private instance field
        Field instanceField = DBBroker.class.getDeclaredField("instance");
        instanceField.setAccessible(true); // Make the field accessible
        instanceField.set(null, mockDBBroker); // Set the mock to the instance field
    }

    @Test
    public void testValidateValidMuzicar() {
        assertDoesNotThrow(() -> soIzmeniMuzicara.validate(muzicar));
    }

    @Test
    public void testValidateInvalidObject() {
        ApstraktniDomenskiObjekat invalidObject = Mockito.mock(ApstraktniDomenskiObjekat.class);  // Pass an invalid object that extends ApstraktniDomenskiObjekat
        Exception exception = assertThrows(Exception.class, () -> soIzmeniMuzicara.validate(invalidObject));
        assertEquals("Prosledjeni objekat nije instanca klase Muzicar!", exception.getMessage());
    }

    @Test
    public void testValidateDuplicateContact() throws Exception {
        ArrayList<Muzicar> existingMuzicari = new ArrayList<>();
        existingMuzicari.add(new Muzicar(2L, "Jane", "Violin", "Classical", "john@music.com"));
        when(mockDBBroker.vrati(muzicar)).thenReturn((ArrayList) existingMuzicari);

        Exception exception = assertThrows(Exception.class, () -> soIzmeniMuzicara.validate(muzicar));
        assertEquals("Ovaj kontakt vec postoji u bazi!", exception.getMessage());
    }

    @Test
    public void testExecuteValidMuzicar() throws Exception {
        // Execute the system operation
        soIzmeniMuzicara.execute(muzicar);

        // Verify that DBBroker's izmeni method was called with the correct argument
        verify(mockDBBroker, times(1)).izmeni(muzicar);
    }

    @Test
    public void testExecuteWithException() throws Exception {
        // Mock DBBroker to throw an exception
        doThrow(new SQLException("Database error")).when(mockDBBroker).izmeni(muzicar);

        Exception exception = assertThrows(Exception.class, () -> soIzmeniMuzicara.execute(muzicar));
        assertEquals("Database error", exception.getMessage());
    }
}
