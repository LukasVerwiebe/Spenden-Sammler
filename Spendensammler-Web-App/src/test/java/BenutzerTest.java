//import com.mycompany.services.CharityService;
//import com.mycompany.services.UserService;
//import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
//import static org.junit.Assert.*;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.junit.jupiter.MockitoExtension;
//import spendensammler.jpa.entities.Benutzer;
//import spendensammler.jpa.entities.Charity;
//import spendensammler.jpa.entities.Charity_Organisation;
//
//
//@ExtendWith(MockitoExtension.class)
//public class BenutzerTest {
//    
//    @InjectMocks
//    private UserService userService;
//    
//    private Benutzer user1;
//    private Benutzer user2;
//    private Benutzer user3;
//    private Benutzer user4;
//    
//    @BeforeEach
//    void setUp() {
//        userService = new UserService();
//         
//        user1 = new Benutzer("vorname1", "nachname1", 
//                "email@1.de", "passwort1", "Admin", true);
//         
//        
//         
//        user2 = new Benutzer("vorname2", "nachname2", 
//                "", "passwort2", "Admin", true);
//         
//        
//    }
//    
//    @Test
//    void testCreateBenutzer1() {
//        System.out.println("1");
//        userService.createNewUser(user1, "stadt1", "stadt2");
//        assertEquals(userService.findeBenutzer(user1.getEmailBenutzer()).get(0).getEmailBenutzer(), "email@1.de"); 
//        
//        userService.removeUser(user1);
//        assertEquals(userService.findeBenutzer(user1.getEmailBenutzer()).size(), 0);
//    }
//    
//    @Test
//    void testUpdateBenutzer2() {
//        System.out.println("2");
//        userService.createNewUser(user2, "stadt1", "stadt2");
//        assertEquals(userService.findeBenutzer(user2.getEmailBenutzer()).get(0).getEmailBenutzer(), ""); 
//        
//        userService.updateUser(user2.getIdBenutzer(), "Timo", user2.getNachname(), user2.getEmailBenutzer(), user2.getPasswort(), user2.getRolleBenutzer(), user2.getStatusBenutzer(), user2.getStadt(), user2.getStrasse(), user2.getPostleitzahl());
//        System.out.println(userService.findeBenutzer(user2.getEmailBenutzer()).get(0).getVorname());
//        assertEquals(userService.findeBenutzer(user2.getEmailBenutzer()).get(0).getVorname(), "Timo");
//        
//        userService.removeUser(user2);
//        assertEquals(userService.findeBenutzer(user2.getEmailBenutzer()).size(), 0);
//    }
//    
////    @Test
////    void testUniqueEmail() {
////        System.out.println("3");
////        boolean thrown = false;
////        try{
////        userService.createNewUser(user1, "stadt1", "stadt2");
////        userService.createNewUser(user1, "stadt2", "stadt3");
////        userService.removeUser(user1);
////        } catch (Exception e) {
////          thrown = true;            
////        }
////        assertTrue(thrown);
////        userService.removeUser(user2);
////        assertEquals(userService.findeBenutzer(user2.getEmailBenutzer()).size(), 0);
////                
////    }
//}    
//    
//    