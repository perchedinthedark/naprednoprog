package so.izvodjaci;

import dbb.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.Izvodjac;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import so.izvodjaci.SOVratiIzvodjace;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SOVratiIzvodjaceTest {

    private SOVratiIzvodjace soVratiIzvodjace;
    private DBBroker mockDBBroker;
    private Izvodjac izvodjac;

    @BeforeEach
    public void setUp() throws Exception {
        soVratiIzvodjace = new SOVratiIzvodjace();
        mockDBBroker = mock(DBBroker.class);
        izvodjac = new Izvodjac(); 

       
        Field instanceField = DBBroker.class.getDeclaredField("instance");
        instanceField.setAccessible(true); 
        instanceField.set(null, mockDBBroker); 
    }

    @Test
    public void testValidateValidIzvodjac() {
        assertDoesNotThrow(() -> soVratiIzvodjace.validate(izvodjac));
    }

    @Test
    public void testValidateInvalidObject() {
        ApstraktniDomenskiObjekat invalidObject = Mockito.mock(ApstraktniDomenskiObjekat.class);
        Exception exception = assertThrows(Exception.class, () -> soVratiIzvodjace.validate(invalidObject));
        assertEquals("Prosledjeni objekat nije instanca klase Izvodjac!", exception.getMessage());
    }

    @Test
    public void testExecuteWithValidIzvodjac() throws Exception {
     
        ArrayList<ApstraktniDomenskiObjekat> mockIzvodjacList = new ArrayList<>();
        mockIzvodjacList.add(izvodjac);
        when(mockDBBroker.vrati(izvodjac)).thenReturn(mockIzvodjacList);

       
        soVratiIzvodjace.execute(izvodjac);

      
        ArrayList<Izvodjac> listaIzvodjaca = soVratiIzvodjace.getLista();
        assertNotNull(listaIzvodjaca);
        assertEquals(1, listaIzvodjaca.size());
        assertEquals(izvodjac, listaIzvodjaca.get(0));

       
        verify(mockDBBroker, times(1)).vrati(izvodjac);
    }

    @Test
    public void testExecuteWithException() throws Exception {
     
        when(mockDBBroker.vrati(izvodjac)).thenThrow(new SQLException("Database error"));

        Exception exception = assertThrows(Exception.class, () -> soVratiIzvodjace.execute(izvodjac));
        assertEquals("Database error", exception.getMessage());
    }
}
