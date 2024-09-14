package so.koncerti;

import dbb.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.Izvodjac;
import domen.Koncert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import so.koncerti.SODodajKoncert;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SODodajKoncertTest {

    private SODodajKoncert soDodajKoncert;
    private DBBroker mockDBBroker;
    private Koncert koncert;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;

    @BeforeEach
    public void setUp() throws Exception {
        soDodajKoncert = new SODodajKoncert();
        mockDBBroker = mock(DBBroker.class);
        koncert = new Koncert();
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);

    
        Field instanceField = DBBroker.class.getDeclaredField("instance");
        instanceField.setAccessible(true);
        instanceField.set(null, mockDBBroker);
    }

    @Test
    public void testValidateValidKoncert() throws Exception {
     
        koncert.setDatumPocetka(new Timestamp(new Date().getTime() + 86400000)); 
        koncert.setDatumZavrsetka(new Timestamp(new Date().getTime() + 172800000)); 
        koncert.setKapacitetKoncerta(50);
        ArrayList<Izvodjac> izvodjaci = new ArrayList<>();
        izvodjaci.add(new Izvodjac());
        izvodjaci.add(new Izvodjac());
        koncert.setIzvodjaci(izvodjaci);

      
        assertDoesNotThrow(() -> soDodajKoncert.validate(koncert));
    }

    @Test
    public void testValidateInvalidKapacitet() {
       
        koncert.setDatumPocetka(new Timestamp(new Date().getTime() + 86400000));
        koncert.setDatumZavrsetka(new Timestamp(new Date().getTime() + 172800000)); 
        koncert.setKapacitetKoncerta(1);
        koncert.setIzvodjaci(new ArrayList<>());

        Exception exception = assertThrows(Exception.class, () -> soDodajKoncert.validate(koncert));
        assertEquals("Kapacitet koncerta mora biti izmedju 2 i 100!", exception.getMessage());
    }

    @Test
    public void testValidateInvalidIzvodjaci() {
       
        koncert.setDatumPocetka(new Timestamp(new Date().getTime() + 86400000)); 
        koncert.setDatumZavrsetka(new Timestamp(new Date().getTime() + 172800000));
        koncert.setKapacitetKoncerta(50);
        koncert.setIzvodjaci(new ArrayList<>()); 

        Exception exception = assertThrows(Exception.class, () -> soDodajKoncert.validate(koncert));
        assertEquals("Koncert mora imati minimalno 2 izvodjaca!", exception.getMessage());
    }

    @Test
    public void testExecuteValidKoncert() throws Exception {
       
        koncert.setDatumPocetka(new Timestamp(new Date().getTime() + 86400000));
        koncert.setDatumZavrsetka(new Timestamp(new Date().getTime() + 172800000));
        koncert.setKapacitetKoncerta(50);
        ArrayList<Izvodjac> izvodjaci = new ArrayList<>();
        izvodjaci.add(new Izvodjac());
        izvodjaci.add(new Izvodjac());
        koncert.setIzvodjaci(izvodjaci);

      
        when(mockDBBroker.dodaj(koncert)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.getGeneratedKeys()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getLong(1)).thenReturn(1L);

       
        soDodajKoncert.execute(koncert);

       
        verify(mockDBBroker, times(1)).dodaj(koncert);

       
        verify(mockDBBroker, times(2)).dodaj(any(Izvodjac.class));
    }

    @Test
    public void testExecuteWithException() throws Exception {
      
        when(mockDBBroker.dodaj(koncert)).thenThrow(new SQLException("Database error"));

        Exception exception = assertThrows(Exception.class, () -> soDodajKoncert.execute(koncert));
        assertEquals("Database error", exception.getMessage());
    }
}
