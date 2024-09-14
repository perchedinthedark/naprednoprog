package so.koncerti;

import dbb.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.Izvodjac;
import domen.Koncert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import so.koncerti.SOIzmeniKoncert;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SOIzmeniKoncertTest {

    private SOIzmeniKoncert soIzmeniKoncert;
    private DBBroker mockDBBroker;
    private Koncert koncert;

    @BeforeEach
    public void setUp() throws Exception {
        soIzmeniKoncert = new SOIzmeniKoncert();
        mockDBBroker = mock(DBBroker.class);
        koncert = new Koncert();

      
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

       
        assertDoesNotThrow(() -> soIzmeniKoncert.validate(koncert));
    }

    @Test
    public void testValidateInvalidKapacitet() {
      
        koncert.setDatumPocetka(new Timestamp(new Date().getTime() + 86400000)); 
        koncert.setDatumZavrsetka(new Timestamp(new Date().getTime() + 172800000));
        koncert.setKapacitetKoncerta(1); // Invalid capacity
        koncert.setIzvodjaci(new ArrayList<>());

        Exception exception = assertThrows(Exception.class, () -> soIzmeniKoncert.validate(koncert));
        assertEquals("Kapacitet grupe mora biti izmedju 2 i 100!", exception.getMessage());
    }

    @Test
    public void testValidateInvalidIzvodjaci() {
       
        koncert.setDatumPocetka(new Timestamp(new Date().getTime() + 86400000)); 
        koncert.setDatumZavrsetka(new Timestamp(new Date().getTime() + 172800000));
        koncert.setKapacitetKoncerta(50);
        koncert.setIzvodjaci(new ArrayList<>()); 

        Exception exception = assertThrows(Exception.class, () -> soIzmeniKoncert.validate(koncert));
        assertEquals("Koncert mora imati minimalno 2 izvodjaca!", exception.getMessage());
    }

    @Test
    public void testExecuteValidKoncert() throws Exception {
       
        koncert.setDatumPocetka(new Timestamp(new Date().getTime() + 86400000)); 
        koncert.setDatumZavrsetka(new Timestamp(new Date().getTime() + 172800000)); 
        koncert.setKapacitetKoncerta(50);
        ArrayList<Izvodjac> izvodjaci = new ArrayList<>();
        Izvodjac izvodjac1 = new Izvodjac();
        Izvodjac izvodjac2 = new Izvodjac();
        izvodjaci.add(izvodjac1);
        izvodjaci.add(izvodjac2);
        koncert.setIzvodjaci(izvodjaci);

        
        soIzmeniKoncert.execute(koncert);

       
        verify(mockDBBroker, times(1)).izmeni(koncert);

        
        verify(mockDBBroker, times(1)).obrisi(izvodjac1);

       
        verify(mockDBBroker, times(2)).dodaj(any(Izvodjac.class));
    }

    @Test
    public void testExecuteWithException() throws Exception {
      
        doThrow(new SQLException("Database error")).when(mockDBBroker).izmeni(koncert);

        Exception exception = assertThrows(Exception.class, () -> soIzmeniKoncert.execute(koncert));
        assertEquals("Database error", exception.getMessage());
    }
}
