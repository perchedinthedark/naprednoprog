package so.koncerti;

import dbb.DBBroker;
import domen.Koncert;
import domen.ApstraktniDomenskiObjekat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import so.koncerti.SOObrisiKoncert;

import java.lang.reflect.Field;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SOObrisiKoncertTest {

    private SOObrisiKoncert soObrisiKoncert;
    private DBBroker mockDBBroker;
    private Koncert koncert;

    @BeforeEach
    public void setUp() throws Exception {
        soObrisiKoncert = new SOObrisiKoncert();
        mockDBBroker = mock(DBBroker.class);
        koncert = new Koncert(); // Example Koncert object

        // Use reflection to set the private instance field in DBBroker
        Field instanceField = DBBroker.class.getDeclaredField("instance");
        instanceField.setAccessible(true); 
        instanceField.set(null, mockDBBroker); 
    }

    @Test
    public void testValidateValidKoncert() {
        assertDoesNotThrow(() -> soObrisiKoncert.validate(koncert));
    }

    @Test
    public void testValidateInvalidObject() {
        ApstraktniDomenskiObjekat invalidObject = Mockito.mock(ApstraktniDomenskiObjekat.class);
        Exception exception = assertThrows(Exception.class, () -> soObrisiKoncert.validate(invalidObject));
        assertEquals("Prosledjeni objekat nije instanca klase Koncert!", exception.getMessage());
    }

    @Test
    public void testExecuteWithValidKoncert() throws Exception {
        // Execute the system operation
        soObrisiKoncert.execute(koncert);

        // Verify that DBBroker's obrisi method was called with the correct argument
        verify(mockDBBroker, times(1)).obrisi(koncert);
    }

    @Test
    public void testExecuteWithException() throws Exception {
        // Mock DBBroker to throw an exception
        doThrow(new SQLException("Database error")).when(mockDBBroker).obrisi(koncert);

        Exception exception = assertThrows(Exception.class, () -> soObrisiKoncert.execute(koncert));
        assertEquals("Database error", exception.getMessage());
    }
}
