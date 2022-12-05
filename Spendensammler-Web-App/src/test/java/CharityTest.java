
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
   
   
   

}
