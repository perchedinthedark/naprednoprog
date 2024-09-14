package so.koncerti;

import dbb.DBBroker;
import domen.Koncert;
import domen.ApstraktniDomenskiObjekat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import so.koncerti.SOVratiKoncerte;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SOVratiKoncerteTest {

    private SOVratiKoncerte soVratiKoncerte;
    private DBBroker mockDBBroker;
    private Koncert koncert;

    @BeforeEach
    public void setUp() throws Exception {
        soVratiKoncerte = new SOVratiKoncerte();
        mockDBBroker = mock(DBBroker.class);
        koncert = new Koncert(); 

       
        Field instanceField = DBBroker.class.getDeclaredField("instance");
        instanceField.setAccessible(true); 
        instanceField.set(null, mockDBBroker); 
    }

    @Test
    public void testValidateValidKoncert() {
        assertDoesNotThrow(() -> soVratiKoncerte.validate(koncert));
    }

    @Test
    public void testValidateInvalidObject() {
        ApstraktniDomenskiObjekat invalidObject = Mockito.mock(ApstraktniDomenskiObjekat.class);
        Exception exception = assertThrows(Exception.class, () -> soVratiKoncerte.validate(invalidObject));
        assertEquals("Prosledjeni objekat nije instanca klase Koncert!", exception.getMessage());
    }

    @Test
    public void testExecuteWithValidKoncert() throws Exception {
      
        ArrayList<ApstraktniDomenskiObjekat> mockKoncertList = new ArrayList<>();
        mockKoncertList.add(koncert);
        when(mockDBBroker.vrati(koncert)).thenReturn(mockKoncertList);

       
        soVratiKoncerte.execute(koncert);

       
        ArrayList<Koncert> listaKoncerata = soVratiKoncerte.getLista();
        assertNotNull(listaKoncerata);
        assertEquals(1, listaKoncerata.size());
        assertEquals(koncert, listaKoncerata.get(0));

       
        verify(mockDBBroker, times(1)).vrati(koncert);
    }

    @Test
    public void testExecuteWithException() throws Exception {
      
        when(mockDBBroker.vrati(koncert)).thenThrow(new SQLException("Database error"));

        Exception exception = assertThrows(Exception.class, () -> soVratiKoncerte.execute(koncert));
        assertEquals("Database error", exception.getMessage());
    }
}
