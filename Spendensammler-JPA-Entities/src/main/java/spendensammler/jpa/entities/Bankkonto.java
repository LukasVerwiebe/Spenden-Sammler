
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
public class Bankkonto implements Serializable {
    
    // Attribute:
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idBankkonto;
    
    private String ibanBankkonto;
    private String inhaberBankkonto;
    private String bicBankkonto;
    private boolean deletedBankkonto;
    private boolean aktivKonto;    
    private String paypalid;
    
    // Verbindungen:
    @ManyToOne
    private Charity charity;
    
    public Bankkonto() {        
    }
    
    public Bankkonto(String ibanBankkonto, String inhaberBankkonto, String bicBankkonto, boolean deletedBankkonto) {
        this.ibanBankkonto = ibanBankkonto;
        this.inhaberBankkonto = inhaberBankkonto;
        this.bicBankkonto = bicBankkonto;
        this.deletedBankkonto = deletedBankkonto;
    }
    
    // Getter und Setter:

    public Long getIdBankkonto() {
        return idBankkonto;
    }

    public void setIdBankkonto(Long idBankkonto) {
        this.idBankkonto = idBankkonto;
    }

    public String getIbanBankkonto() {
        return ibanBankkonto;
    }

    public void setIbanBankkonto(String ibanBankkonto) {
        this.ibanBankkonto = ibanBankkonto;
    }

    public String getInhaberBankkonto() {
        return inhaberBankkonto;
    }

    public void setInhaberBankkonto(String inhaberBankkonto) {
        this.inhaberBankkonto = inhaberBankkonto;
    }

    public String getBicBankkonto() {
        return bicBankkonto;
    }

    public void setBicBankkonto(String bicBankkonto) {
        this.bicBankkonto = bicBankkonto;
    }

    public boolean isDeletedBankkonto() {
        return deletedBankkonto;
    }

    public void setDeletedBankkonto(boolean deletedBankkonto) {
        this.deletedBankkonto = deletedBankkonto;
    }

    public Charity getCharity() {
        return charity;
    }

    public void setCharity(Charity charity) {
        this.charity = charity;
    }

    public boolean isAktivKonto() {
        return aktivKonto;
    }

    public void setAktivKonto(boolean aktivKonto) {
        this.aktivKonto = aktivKonto;
    }

    public String getPaypalid() {
        return paypalid;
    }

    public void setPaypalid(String paypalid) {
        this.paypalid = paypalid;
    }
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.idBankkonto);
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
        final Bankkonto other = (Bankkonto) obj;
        return Objects.equals(this.idBankkonto, other.idBankkonto);
    }

    @Override
    public String toString() {
        return "Bankkonto{" + "idBankkonto=" + idBankkonto + ", ibanBankkonto=" + ibanBankkonto + ", inhaberBankkonto=" + inhaberBankkonto + ", bicBankkonto=" + bicBankkonto + ", deletedBankkonto=" + deletedBankkonto + '}';
    }
       
    
}
