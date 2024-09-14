package so.bina;

import dbb.DBBroker;
import domen.Bina;
import domen.ApstraktniDomenskiObjekat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import so.bina.SOVratiBine;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SOVratiBineTest {

    private SOVratiBine soVratiBine;
    private DBBroker mockDBBroker;
    private Bina bina;

    @BeforeEach
    public void setUp() throws Exception {
        soVratiBine = new SOVratiBine();
        mockDBBroker = mock(DBBroker.class);
        bina = new Bina(); 

       
        Field instanceField = DBBroker.class.getDeclaredField("instance");
        instanceField.setAccessible(true); 
        instanceField.set(null, mockDBBroker);
    }

    @Test
    public void testValidateValidBina() {
        assertDoesNotThrow(() -> soVratiBine.validate(bina));
    }

    @Test
    public void testValidateInvalidObject() {
        ApstraktniDomenskiObjekat invalidObject = Mockito.mock(ApstraktniDomenskiObjekat.class);
        Exception exception = assertThrows(Exception.class, () -> soVratiBine.validate(invalidObject));
        assertEquals("Prosledjeni objekat nije instanca klase Bina!", exception.getMessage());
    }

    @Test
    public void testExecuteWithValidBina() throws Exception {
       
        ArrayList<ApstraktniDomenskiObjekat> mockBinaList = new ArrayList<>();
        mockBinaList.add(bina);
        when(mockDBBroker.vrati(bina)).thenReturn(mockBinaList);

       
        soVratiBine.execute(bina);

       
        ArrayList<Bina> listaBina = soVratiBine.getLista();
        assertNotNull(listaBina);
        assertEquals(1, listaBina.size());
        assertEquals(bina, listaBina.get(0));

      
        verify(mockDBBroker, times(1)).vrati(bina);
    }

    @Test
    public void testExecuteWithException() throws Exception {
     
        when(mockDBBroker.vrati(bina)).thenThrow(new SQLException("Database error"));

        Exception exception = assertThrows(Exception.class, () -> soVratiBine.execute(bina));
        assertEquals("Database error", exception.getMessage());
    }
}
