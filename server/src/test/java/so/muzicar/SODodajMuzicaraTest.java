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
        muzicar = new Muzicar(); 

       
        Field instanceField = DBBroker.class.getDeclaredField("instance");
        instanceField.setAccessible(true); 
        instanceField.set(null, mockDBBroker);
    }

    @Test
    public void testValidateValidMuzicar() throws Exception {
       
        ArrayList<Muzicar> existingMuzicari = new ArrayList<>();
        when(mockDBBroker.vrati(any(Muzicar.class))).thenReturn((ArrayList<ApstraktniDomenskiObjekat>) (ArrayList<?>) existingMuzicari);

      
        assertDoesNotThrow(() -> soDodajMuzicara.validate(muzicar));
    }

    @Test
    public void testValidateWithDuplicateContact() throws Exception {
      
        Muzicar existingMuzicar = new Muzicar();
        existingMuzicar.setKontakt("123-456");

      
        muzicar.setKontakt("123-456");

     
        ArrayList<Muzicar> existingMuzicari = new ArrayList<>();
        existingMuzicari.add(existingMuzicar);
        when(mockDBBroker.vrati(any(Muzicar.class))).thenReturn((ArrayList<ApstraktniDomenskiObjekat>) (ArrayList<?>) existingMuzicari);

      
        Exception exception = assertThrows(Exception.class, () -> soDodajMuzicara.validate(muzicar));
        assertEquals("Ovaj kontakt vec postoji u bazi!", exception.getMessage());
    }

    @Test
    public void testExecuteValidMuzicar() throws Exception {
    
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);

      
        when(mockDBBroker.dodaj(muzicar)).thenReturn(mockPreparedStatement);

       
        soDodajMuzicara.execute(muzicar);

      
        verify(mockDBBroker, times(1)).dodaj(muzicar);
    }

    @Test
    public void testExecuteWithSQLException() throws Exception {
      
        doThrow(new SQLException("Database error")).when(mockDBBroker).dodaj(muzicar);

        
        Exception exception = assertThrows(Exception.class, () -> soDodajMuzicara.execute(muzicar));
        assertEquals("Database error", exception.getMessage());
    }
}
