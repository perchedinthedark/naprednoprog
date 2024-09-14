package so.login;


import dbb.DBBroker;
import domen.Administrator;
import domen.ApstraktniDomenskiObjekat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import so.login.SOLogin;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SOLoginTest {

    private SOLogin soLogin;
    private DBBroker mockDBBroker;
    private Administrator validAdmin;
    private Administrator invalidAdmin;

    @BeforeEach
    public void setUp() throws Exception {
        soLogin = new SOLogin();
        mockDBBroker = mock(DBBroker.class);
        validAdmin = new Administrator(1L, "John", "Doe", "john", "pass123");
        invalidAdmin = new Administrator(1L, "Jane", "Doe", "jane", "wrongpass");

      
        Field instanceField = DBBroker.class.getDeclaredField("instance");
        instanceField.setAccessible(true);
        instanceField.set(null, mockDBBroker); 
    }

    @Test
    public void testValidateValidAdmin() {
        assertDoesNotThrow(() -> soLogin.validate(validAdmin));
    }

    @Test
    public void testValidateInvalidObject() {
        ApstraktniDomenskiObjekat invalidObject = Mockito.mock(ApstraktniDomenskiObjekat.class);
        Exception exception = assertThrows(Exception.class, () -> soLogin.validate(invalidObject));
        assertEquals("Prosledjeni objekat nije instanca klase Administrator!", exception.getMessage());
    }

    @Test
    public void testExecuteWithValidCredentials() throws Exception {
       
        ArrayList<Administrator> mockAdminList = new ArrayList<>();
        mockAdminList.add(validAdmin);
        when(mockDBBroker.vrati(validAdmin)).thenReturn((ArrayList<ApstraktniDomenskiObjekat>)(ArrayList<?>) mockAdminList);

     
        soLogin.execute(validAdmin);

       
        Administrator loggedInAdmin = soLogin.getUlogovani();
        assertNotNull(loggedInAdmin);
        assertEquals(validAdmin, loggedInAdmin);

       
        verify(mockDBBroker, times(1)).vrati(validAdmin);
    }

    @Test
    public void testExecuteWithInvalidCredentials() throws Exception {
       
        ArrayList<Administrator> mockAdminList = new ArrayList<>();
        mockAdminList.add(invalidAdmin);
        when(mockDBBroker.vrati(validAdmin)).thenReturn((ArrayList<ApstraktniDomenskiObjekat>)(ArrayList<?>) mockAdminList);

      
        Exception exception = assertThrows(Exception.class, () -> soLogin.execute(validAdmin));
        assertEquals("Ne postoji administrator sa tim kredencijalima.", exception.getMessage());

      
        verify(mockDBBroker, times(1)).vrati(validAdmin);
    }

    @Test
    public void testExecuteWithSQLException() throws Exception {
      
        when(mockDBBroker.vrati(validAdmin)).thenThrow(new SQLException("Database error"));

        Exception exception = assertThrows(Exception.class, () -> soLogin.execute(validAdmin));
        assertEquals("Database error", exception.getMessage());
    }
}
