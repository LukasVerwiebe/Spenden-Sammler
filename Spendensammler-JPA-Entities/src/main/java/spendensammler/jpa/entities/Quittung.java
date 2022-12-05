
package spendensammler.jpa.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author Lukas
 */
@Entity
public class Quittung implements Serializable {
    
    // Attribute:
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idQuittung;

    private double summeQuittung;
    private String textQuittung;
    private Date erstelldatum;
    
    // Verbindungen:
    @ManyToOne
    private Charity charity;
    
    @ManyToOne
    private Benutzer benutzer;
    
    public Quittung() {
    }
    
    public Quittung(double summeQuittung, String textQuittung) {
        this.summeQuittung = summeQuittung;
        this.textQuittung = textQuittung;
    }
    
    // Getter und Setter:

    public Long getIdQuittung() {
        return idQuittung;
    }

    public void setIdQuittung(Long idQuittung) {
        this.idQuittung = idQuittung;
    }

    public double getSummeQuittung() {
        return summeQuittung;
    }

    public void setSummeQuittung(double summeQuittung) {
        this.summeQuittung = summeQuittung;
    }

    public String getTextQuittung() {
        return textQuittung;
    }

    public void setTextQuittung(String textQuittung) {
        this.textQuittung = textQuittung;
    }

    public Charity getCharity() {
        return charity;
    }

    public void setCharity(Charity charity) {
        this.charity = charity;
    }

    public Benutzer getBenutzer() {
        return benutzer;
    }

    public void setBenutzer(Benutzer benutzer) {
        this.benutzer = benutzer;
    }

    public Date getErstelldatum() {
        return erstelldatum;
    }

    public void setErstelldatum(Date erstelldatum) {
        this.erstelldatum = erstelldatum;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.idQuittung);
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
        final Quittung other = (Quittung) obj;
        return Objects.equals(this.idQuittung, other.idQuittung);
    }

    @Override
    public String toString() {
        return "Quittung{" + "idQuittung=" + idQuittung + ", summeQuittung=" + summeQuittung + ", textQuittung=" + textQuittung + '}';
    }

    
}
