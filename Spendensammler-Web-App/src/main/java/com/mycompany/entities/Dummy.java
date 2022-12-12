package spendensammler.jpa.entities;

import jakarta.persistence.CascadeType;
import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

/**
 *
 * @author lu80238
 */
@Entity
public class Dummy implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private int id;
    private String name;
    
    public Dummy(){
    System.out.println("printe");
        this.id = 5;
        this.name = "paul";
    
    }
    
    public String scream(){
    return "hallo";
    }

    
}