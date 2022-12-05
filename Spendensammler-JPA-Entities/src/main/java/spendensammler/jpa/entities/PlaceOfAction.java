package spendensammler.jpa.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author lu80238
 */
@Entity
public class PlaceOfAction implements Serializable {
    
    // Attribute:
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPlaceOfAction;   
    
    private String landPlaceOfAction;
    private String isocode;
    
    // Verbindungen:
    @ManyToOne
    private Charity charity;
    
    // Konstruktoren: 
    public PlaceOfAction() { 
    }    
    
    public PlaceOfAction(String land, String isocode) {
        this.landPlaceOfAction = land;
        this.isocode = isocode;
    }  
    
    
    // Getter und Setter
    public Long getIdPlaceOfAction() {
        return idPlaceOfAction;
    }

    public void setIdPlaceOfAction(Long idPlaceOfAction) {
        this.idPlaceOfAction = idPlaceOfAction;
    }

    public String getLandPlaceOfAction() {
        return landPlaceOfAction;
    }

    public void setLandPlaceOfAction(String landPlaceOfAction) {
        this.landPlaceOfAction = landPlaceOfAction;
    }

    public String getIsocode() {
        return isocode;
    }

    public void setIsocode(String isocode) {
        this.isocode = isocode;
    }
    
    public Charity getCharity() {
        return charity;
    }

    public void setCharity(Charity charity) {
        this.charity = charity;
    } 
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.idPlaceOfAction);
        return hash;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PlaceOfAction other = (PlaceOfAction) obj;
        return Objects.equals(this.idPlaceOfAction, other.idPlaceOfAction);
    }

    @Override
    public String toString() {
        return "PlaceOfAction{" + "idPlaceOfAction=" + idPlaceOfAction + ", landPlaceOfAction=" + landPlaceOfAction + ", isocode=" + isocode + '}';
    }
    
}