/**import com.mycompany.services.CharityService;
import com.mycompany.services.UserService;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import spendensammler.jpa.entities.Benutzer;
import spendensammler.jpa.entities.Charity;
import spendensammler.jpa.entities.Charity_Organisation;
import spendensammler.jpa.entities.Quittung;


@ExtendWith(MockitoExtension.class)
public class BenutzerTest {
    
    @InjectMocks
    private UserService userService;
    
    private Benutzer user1;
    private Benutzer user2;
    private Charity charity1;
    private Quittung quittung1;
    
    
    
    @BeforeEach
    void setUp() {
        userService = new UserService();
         
        user1 = new Benutzer("vorname1", "nachname1", 
                "email@1.de", "passwort1", "Admin", true);
         
        
         
        user2 = new Benutzer("vorname2", "nachname2", 
                "", "passwort2", "Admin", true);
        
        charity1 = new Charity("BackendTest1", "gaw11", 5,
                "website1", "worldview1", "fieldsOfAction1",
                "bericht1");
        
        quittung1 = new Quittung(1.00, "text1");
         
        
    }
    
    @Test
    void testCreateBenutzer1() {
        System.out.println("1");
        userService.createNewUser(user1, "stadt1", "stadt2");
        assertEquals(userService.findeBenutzer(user1.getEmailBenutzer()).get(0).getEmailBenutzer(), "email@1.de"); 
        
        userService.removeUser(user1);
        assertEquals(userService.findeBenutzer(user1.getEmailBenutzer()).size(), 0);
    }
    
    @Test
    void testUpdateBenutzer2() {
        System.out.println("2");
        userService.createNewUser(user2, "stadt1", "stadt2");
        assertEquals(userService.findeBenutzer(user2.getEmailBenutzer()).get(0).getEmailBenutzer(), ""); 
        
        userService.updateUser(user2.getIdBenutzer(), "Timo", user2.getNachname(), user2.getEmailBenutzer(), user2.getPasswort(), user2.getRolleBenutzer(), user2.getStatusBenutzer(), user2.getStadt(), user2.getStrasse(), user2.getPostleitzahl());
        System.out.println(userService.findeBenutzer(user2.getEmailBenutzer()).get(0).getVorname());
        assertEquals(userService.findeBenutzer(user2.getEmailBenutzer()).get(0).getVorname(), "Timo");
        
        userService.removeUser(user2);
        assertEquals(userService.findeBenutzer(user2.getEmailBenutzer()).size(), 0);
    }
    
    @Test
    void testCreateQuittung1() {
        userService.createNewQuittung(quittung1);
        
        assertEquals(userService.findAllQuittungen().get(userService.findAllQuittungen().size() - 1).getTextQuittung(), quittung1.getTextQuittung());
        
        userService.updateQuittung(quittung1.getIdQuittung(), 1.00, "textUpdate");
        assertEquals(userService.findAllQuittungen().get(userService.findAllQuittungen().size() - 1).getTextQuittung(), "textUpdate");
        
        int listGroesse = userService.findAllQuittungen().size();
        userService.removeQuittung(quittung1);
        assertEquals(listGroesse - 1, userService.findAllQuittungen().size());
        
        
    }
    
    @Test
    void testDiagramm1() {
        int listGroesse1 = userService.diagrammQuittungen(2022).size();
        
        userService.createNewQuittung(quittung1);
        assertEquals(userService.findAllQuittungen().get(userService.findAllQuittungen().size() - 1).getTextQuittung(), quittung1.getTextQuittung());
        assertEquals(userService.diagrammQuittungen(2022).size(), listGroesse1 + 1);
                
        int listGroesse = userService.findAllQuittungen().size();
        userService.removeQuittung(quittung1);
        assertEquals(listGroesse - 1, userService.findAllQuittungen().size());
        
        
    }

}    
    **/
    
