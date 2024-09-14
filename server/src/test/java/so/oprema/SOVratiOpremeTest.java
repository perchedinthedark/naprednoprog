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
        oprema = new Oprema(); 

      
        Field instanceField = DBBroker.class.getDeclaredField("instance");
        instanceField.setAccessible(true); 
        instanceField.set(null, mockDBBroker); 
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
      
        ArrayList<ApstraktniDomenskiObjekat> mockOpremaList = new ArrayList<>();
        mockOpremaList.add(oprema);
        when(mockDBBroker.vrati(oprema)).thenReturn(mockOpremaList);

     
        soVratiOpreme.execute(oprema);

      
        ArrayList<Oprema> listaOprema = soVratiOpreme.getLista();
        assertNotNull(listaOprema);
        assertEquals(1, listaOprema.size());
        assertEquals(oprema, listaOprema.get(0));

      
        verify(mockDBBroker, times(1)).vrati(oprema);
    }

    @Test
    public void testExecuteWithException() throws Exception {
      
        when(mockDBBroker.vrati(oprema)).thenThrow(new SQLException("Database error"));

        Exception exception = assertThrows(Exception.class, () -> soVratiOpreme.execute(oprema));
        assertEquals("Database error", exception.getMessage());
    }
}
