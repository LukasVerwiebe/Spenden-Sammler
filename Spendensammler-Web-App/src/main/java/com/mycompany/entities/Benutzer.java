package spendensammler.jpa.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author lu80238
 */
@Entity
public class Benutzer implements Serializable {
    
    // Attribute:
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idBenutzer;   
    
    @Column(unique=true)
    @Email
    private String emailBenutzer;
    
    private String vorname;
    private String nachname;
    private String passwort;
    private String strasse;
    private String stadt;
    private int postleitzahl;
    private String rolleBenutzer;
    private boolean statusBenutzer;
    private Date erstelldatum;
    
    // Verbindungen:
    @OneToOne
    private Charity charity;
    
    @OneToMany(mappedBy = "benutzer", cascade = { CascadeType.REMOVE, CascadeType.PERSIST })
    private List<Quittung> quittung = new ArrayList<>();
    
    // Konstruktoren: 
    public Benutzer() { 
    }    
    
    public Benutzer(String vorname, String nachname, String email, String passwort, String rolle, boolean status) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.emailBenutzer = email;
        this.passwort = passwort;
        this.rolleBenutzer = rolle;
        this.statusBenutzer = status;
    }   
    
    // Getter:

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getIdBenutzer() {
        return idBenutzer;
    }

    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public String getEmailBenutzer() {
        return emailBenutzer;
    }

    public String getPasswort() {
        return passwort;
    }

    public String getStrasse() {
        return strasse;
    }

    public String getStadt() {
        return stadt;
    }

    public int getPostleitzahl() {
        return postleitzahl;
    }

    public String getRolleBenutzer() {
        return rolleBenutzer;
    }

    public boolean getStatusBenutzer() {
        return statusBenutzer;
    }

    public boolean isStatusBenutzer() {
        return statusBenutzer;
    }

    public List<Quittung> getQuittung() {
        return quittung;
    }
    
    
    // Setter:

    public void setIdBenutzer(Long idBenutzer) {
        this.idBenutzer = idBenutzer;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public void setEmailBenutzer(String emailBenutzer) {
        this.emailBenutzer = emailBenutzer;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public void setStadt(String stadt) {
        this.stadt = stadt;
    }

    public void setPostleitzahl(int postleitzahl) {
        this.postleitzahl = postleitzahl;
    }

    public void setRolleBenutzer(String rolleBenutzer) {
        this.rolleBenutzer = rolleBenutzer;
    }

    public void setStatusBenutzer(boolean statusBenutzer) {
        this.statusBenutzer = statusBenutzer;
    } 

    public Charity getCharity() {
        return charity;
    }

    public void setCharity(Charity charity) {
        this.charity = charity;
    }

    public void setQuittung(List<Quittung> quittung) {
        this.quittung = quittung;
    }

    public Date getErstelldatum() {
        return erstelldatum;
    }

    public void setErstelldatum(Date erstelldatum) {
        this.erstelldatum = erstelldatum;
    }

        
    
    // Weitere Methoden:

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.idBenutzer);
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
        final Benutzer other = (Benutzer) obj;
        return Objects.equals(this.idBenutzer, other.idBenutzer);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + idBenutzer + ", vornamename=" + vorname + ", nachname=" + nachname + ", email=" + emailBenutzer + ", passwort=" + passwort + ", strasse=" + strasse + ", stadt=" + stadt + ", postleitzahl=" + postleitzahl + '}';
    } 
    
}