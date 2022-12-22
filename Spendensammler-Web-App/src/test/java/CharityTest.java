/**
import com.mycompany.services.CharityService;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import spendensammler.jpa.entities.Charity;
import spendensammler.jpa.entities.Charity_Organisation;
import spendensammler.jpa.entities.Kategorie;


@ExtendWith(MockitoExtension.class)
public class CharityTest {
   
   @InjectMocks
   private CharityService charityService;
   
   
   private Charity charity1; 
   private Charity charity2;
   private Charity charity3;
   private Charity charity4;
   private Charity charityFilter;
   private Charity_Organisation chOrga1;
   private Charity_Organisation chOrga2;
   private Charity_Organisation chOrga3;
   private Charity_Organisation chOrga4;
   private Charity_Organisation chOrga5;
   
   
  
   
    
    
    @BeforeEach
    void setUp() {
         charityService = new CharityService();
         
         charity1 = new Charity("BackendTest1", "gaw11", 5,
                "website1", "worldview1", "fieldsOfAction1",
                "bericht1");
         
         chOrga1 = new Charity_Organisation();
         charity1.setCh_orga(chOrga1);
         chOrga1.setCharity(charity1);
         
         charity2 = new Charity("BackendTest2", "gaw11", 5,
                "website1", "worldview1", "fieldsOfAction1",
                "bericht1");
         
         chOrga2 = new Charity_Organisation();
         charity2.setCh_orga(chOrga2);
         chOrga2.setCharity(charity2);
         
         charity3 = new Charity("BackendTest3", "gaw11", 5,
                "website1", "worldview1", "fieldsOfAction1",
                "bericht1");
         
         chOrga3 = new Charity_Organisation();
         charity3.setCh_orga(chOrga3);
         chOrga3.setCharity(charity3);
         
         charity4 = new Charity("BackendTest4", "gaw11", 5,
                "website1", "worldview1", "fieldsOfAction1",
                "bericht1");
         
         chOrga4 = new Charity_Organisation();
         charity4.setCh_orga(chOrga4);
         chOrga4.setCharity(charity4);
         
         
         charityFilter = new Charity("CharityFilter", "gaw11", 5,
                "website1", "FilterTest", "fieldsOfAction1",
                "bericht1");
         
         chOrga5 = new Charity_Organisation();
         charityFilter.setCh_orga(chOrga5);
         chOrga5.setCharity(charityFilter);
         
         
    }
    
    
    
    
    @Test
    @Order(1)
    void testCharity1() {
        System.out.println("1");
        charityService.createNewCharity(charity1);
         assertEquals(charityService.findeCharity(charity1.getName()).get(0).getName(),"BackendTest1");
         
        charityService.updateCharity(charity1.getIdCharity(), charity1.getName(), "@!§$%&/()=?äüö", 1999, "website2", "woldview2", "action2", "bericht2", 222L, 22L, 222L, "managment2", 1999, "strasse2", "stadt2", "plz2", "222222", "222223", "test2@test2.de");
        assertEquals(charityService.findeCharity(charity1.getName()).get(0).getLogoPath(), "@!§$%&/()=?äüö");
         
        charityService.removeCharity(charity1);
        System.out.println(charityService.findeCharity(charity1.getName()));
        assertEquals(charityService.findeCharity(charity1.getName()).size(), 0);
    }
    
    @Test
    @Order(2)
    void testCharity2() {
        
        charityService.createNewCharity(charity2);        
        assertEquals(charityService.findeCharity(charity2.getName()).get(0).getName(),"BackendTest2");
        
        charityService.updateCharity(charity2.getIdCharity(), charity2.getName(), "bild2", 1999, "website2", "woldview2", "action2", "bericht2", 9223372036854775807L, 22L, 222L, "managment2", 1999, "strasse2", "stadt2", "plz2", "222222", "222223", "test2@test2.de");
        assertEquals(charityService.findeCharity(charity2.getName()).get(0).getFoundingYear(), 1999);
        
        charityService.removeCharity(charity2);
        assertEquals(charityService.findeCharity(charity2.getName()).size(), 0);
         
    }     
         
    @Test
    @Order(3)        
    void testCharity3() {
        
        charityService.createNewCharity(charity3);
        assertEquals(charityService.findeCharity(charity3.getName()).get(0).getName(),"BackendTest3");
        
        charityService.updateCharity(charity3.getIdCharity(), charity3.getName(), "bild2", 1999, "website2", "woldview2", "action2", "bericht2", 222L, 22L, 222L, "managment2", 1999, "strasse2", "stadt2", "plz2", "222222", "222223", "test2@test2.de");
        assertEquals(charityService.findeCharity(charity3.getName()).get(0).getLogoPath(), "bild2");
        assertEquals(charityService.findeCharity(charity3.getName()).get(0).getFoundingYear(), 1999);
        assertEquals(charityService.findeCharity(charity3.getName()).get(0).getHomepage(), "website2");
        assertEquals(charityService.findeCharity(charity3.getName()).get(0).getWorldview(), "woldview2");
        assertEquals(charityService.findeCharity(charity3.getName()).get(0).getFieldsOfAction(), "action2");
        assertEquals(charityService.findeCharity(charity3.getName()).get(0).getAdvertingFinancialInspektion(), "bericht2");
        assertEquals(charityService.findeCharity(charity3.getName()).get(0).getCh_orga().getMembercount().toString(), "222");
        assertEquals(charityService.findeCharity(charity3.getName()).get(0).getCh_orga().getEmployeecount().toString(), "22");
        assertEquals(charityService.findeCharity(charity3.getName()).get(0).getCh_orga().getVolounteersCount().toString(), "222");
        assertEquals(charityService.findeCharity(charity3.getName()).get(0).getCh_orga().getManagmentbody(), "managment2");
        assertEquals(charityService.findeCharity(charity3.getName()).get(0).getCh_orga().getOrgaYear(), 1999);
        assertEquals(charityService.findeCharity(charity3.getName()).get(0).getStrasse(), "strasse2");
        assertEquals(charityService.findeCharity(charity3.getName()).get(0).getStadt(), "stadt2");
        assertEquals(charityService.findeCharity(charity3.getName()).get(0).getPostleitzahl(), "plz2");
        assertEquals(charityService.findeCharity(charity3.getName()).get(0).getTelefon(), "222222");
        assertEquals(charityService.findeCharity(charity3.getName()).get(0).getTelefax(), "222223");
        assertEquals(charityService.findeCharity(charity3.getName()).get(0).getEmail(), "test2@test2.de");
                                                        
        
        charityService.removeCharity(charity3);
        assertEquals(charityService.findeCharity(charity3.getName()).size(), 0);
    }    
    
    @Test
    @Order(4)        
    void testCharity4() {
        
        charityService.createNewCharity(charity4);
        assertEquals(charityService.findeCharity(charity4.getName()).get(0).getName(),"BackendTest4");
        
        charityService.updateCharity(charity4.getIdCharity(), charity4.getName(), "bild2", 1999, "website2", "woldview2", "action2", "bericht2", 222L, 22L, 222L, 
                        "managment2managment2managment2managment2managment2managment2managment2managment2managment2managment2managment2managment2managment2managment2"
                                + "managment2managment2managment2managment2managment2managment2managment2managment2managment2managment2managment2asdfg", 1999, "strasse2", "stadt2", "plz2", "222222", "222223", "test2@test2.de");
        assertEquals(charityService.findeCharity(charity4.getName()).get(0).getFoundingYear(), 1999);
        
        charityService.removeCharity(charity4);
        assertEquals(charityService.findeCharity(charity4.getName()).size(), 0);
         
    } 
    
    @Test
    @Order(5)        
    void testCharityFilter() {
        
        charityService.createNewCharity(charityFilter);
        assertEquals(charityService.findeCharity(charityFilter.getName()).get(0).getName(),"CharityFilter");
        
        
        assertEquals(charityService.gefilterteCharities(charityFilter.getWorldview(), "", "").size(), 1);
        
        charityService.removeCharity(charityFilter);
        assertEquals(charityService.findeCharity(charityFilter.getName()).size(), 0);
         
    }

}
**/