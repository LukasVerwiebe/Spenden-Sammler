package spendensammler.jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author lu80238
 */
@Entity
public class Kategorie implements Serializable {
    
    // Attribute:
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idKategorie;   
    
    private String nameKategorie;
    
    // Verbindungen:    
    @ManyToOne
    private Charity charity;
    

    // Konstruktoren: 
    public Kategorie() { 
    }    
    
    public Kategorie(String name) {
        this.nameKategorie = name;
    }  
    
    
    // Getter und Setter
    public Long getIdKategorie() {
        return idKategorie;
    }

    public void setIdKategorie(Long idKategorie) {
        this.idKategorie = idKategorie;
    }

    public String getNameKategorie() {
        return nameKategorie;
    }

    public void setNameKategorie(String nameKategorie) {
        this.nameKategorie = nameKategorie;
    }

    public Charity getCharity() {
        return charity;
    }

    public void setCharity(Charity charity) {
        this.charity = charity;
    }
    
    

//    public Set<Charity> getCharity() {
//        return charity;
//    }
//
//    public void setCharity(Set<Charity> charity) {
//        this.charity = charity;
//    }
//    
//    public void addCharity(Charity charity) {
//        this.charity.add(charity);
//    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.idKategorie);
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
        final Kategorie other = (Kategorie) obj;
        return Objects.equals(this.idKategorie, other.idKategorie);
    }

    @Override
    public String toString() {
        return "Kategorie{" + "idKategorie=" + idKategorie + ", nameKategorie=" + nameKategorie + '}';
    }   
    
}