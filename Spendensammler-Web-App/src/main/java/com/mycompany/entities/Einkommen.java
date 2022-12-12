
package spendensammler.jpa.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Lukas
 */
@Entity
public class Einkommen implements Serializable {
    
    // Attribute:
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idEinkommen;
    
    private int jahrEinkommen;
    private double geldEinkommen;
    
    // Verbindungen:
    @ManyToOne
    private Charity charity;
    
    public Einkommen() {        
    }
    
    public Einkommen(int jahrEinkommen, double geldEinkommen) {  
        this.jahrEinkommen = jahrEinkommen;
        this.geldEinkommen = geldEinkommen;
    }
        
    // Getter und Setter:

    public Long getIdEinkommen() {
        return idEinkommen;
    }

    public void setIdEinkommen(Long idEinkommen) {
        this.idEinkommen = idEinkommen;
    }

    public int getJahrEinkommen() {
        return jahrEinkommen;
    }

    public void setJahrEinkommen(int jahrEinkommen) {
        this.jahrEinkommen = jahrEinkommen;
    }

    public double getGeldEinkommen() {
        return geldEinkommen;
    }

    public void setGeldEinkommen(double geldEinkommen) {
        this.geldEinkommen = geldEinkommen;
    }

    public Charity getCharity() {
        return charity;
    }

    public void setCharity(Charity charity) {
        this.charity = charity;
    }

    @Override
    public String toString() {
        return "Einkommen{" + "idEinkommen=" + idEinkommen + ", jahrEinkommen=" + jahrEinkommen + ", geldEinkommen=" + geldEinkommen + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.idEinkommen);
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
        final Einkommen other = (Einkommen) obj;
        return Objects.equals(this.idEinkommen, other.idEinkommen);
    }
    
}
