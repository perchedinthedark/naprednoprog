package so.muzicar;

import dbb.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.Muzicar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import so.muzicar.SOObrisiMuzicara;

import java.lang.reflect.Field;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SOObrisiMuzicaraTest {

    private SOObrisiMuzicara soObrisiMuzicara;
    private DBBroker mockDBBroker;
    private Muzicar muzicar;

    @BeforeEach
    public void setUp() throws Exception {
        soObrisiMuzicara = new SOObrisiMuzicara();
        mockDBBroker = mock(DBBroker.class);
        muzicar = new Muzicar(); // Example Muzicar object

        // Use reflection to set the private instance field in DBBroker
        Field instanceField = DBBroker.class.getDeclaredField("instance");
        instanceField.setAccessible(true);
        instanceField.set(null, mockDBBroker);
    }

    @Test
    public void testValidateValidMuzicar() {
        assertDoesNotThrow(() -> soObrisiMuzicara.validate(muzicar));
    }

    @Test
    public void testValidateInvalidObject() {
        ApstraktniDomenskiObjekat invalidObject = Mockito.mock(ApstraktniDomenskiObjekat.class);
        Exception exception = assertThrows(Exception.class, () -> soObrisiMuzicara.validate(invalidObject));
        assertEquals("Prosledjeni objekat nije instanca klase Muzicar!", exception.getMessage());
    }

    @Test
    public void testExecuteValidMuzicar() throws Exception {
        // Execute the system operation
        soObrisiMuzicara.execute(muzicar);

        // Verify that DBBroker's obrisi method was called with the correct argument
        verify(mockDBBroker, times(1)).obrisi(muzicar);
    }

    @Test
    public void testExecuteWithException() throws Exception {
        // Mock DBBroker to throw an exception
        doThrow(new SQLException("Database error")).when(mockDBBroker).obrisi(muzicar);

        Exception exception = assertThrows(Exception.class, () -> soObrisiMuzicara.execute(muzicar));
        assertEquals("Database error", exception.getMessage());
    }
}
