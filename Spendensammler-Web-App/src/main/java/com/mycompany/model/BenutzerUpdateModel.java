
package com.mycompany.model;

import com.mycompany.controller.CharityController;
import com.mycompany.controller.UserController;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import spendensammler.jpa.entities.Benutzer;
import spendensammler.jpa.entities.Charity;

/**
 *
 * @author Lukas
 */
@Named(value="benutzerUpdateModel")
@SessionScoped
public class BenutzerUpdateModel implements Serializable{
    
    @Inject
    private UserController userController;  
    
    @Inject
    private SessionData sessionData;
    
    private Benutzer benutzer;
    
    private Long id;
    private String emailBenutzer;    
    private String vorname;
    private String nachname;
    private String passwort;
    private String strasse;
    private String stadt;
    private int postleitzahl;
    private String rolleBenutzer;
    private boolean statusBenutzer;
        
    
    @PostConstruct
    public void intit() {
        // BenutzerInformationen nach der Registierung:
        benutzer = sessionData.getBenutzer();
    }

    /**
     * Aufruf der Controller Funktion zum Updaten des ausgewählten Benutzer Objekts 
     * und die Speicherung der Änderungen in der Datenbank. 
     * 
     * Nach der Ausführung wird ein Redirect auf die Startseite .xhtml
     * durchgeführt. Dies ist zwingend nötig, da sonst mit F5 immer wieder neue
     * Obejtke angelegt werden können.
     * @return 
     */
    public String updateUser() {
        userController.updateUser(benutzer.getIdBenutzer(), benutzer.getVorname(), benutzer.getNachname(), benutzer.getEmailBenutzer(),
                benutzer.getPasswort(), benutzer.getRolleBenutzer(), benutzer.getStatusBenutzer(), benutzer.getStadt(), benutzer.getStrasse(), benutzer.getPostleitzahl());        
        return "/startseite.xhtml?faces-redirect=true";        
    }
    
    /*
    * Login Funktion:
    */
    public String loginUser() {
        // Der Benutzer wird anhand der Emailadresse gesucht
        Benutzer benutzerSuche;
        /** Es muss eine Fehlermeldung ausgegegeben werden, wenn die Emailadresse
         *  nicht vorhanden ist IndexOutOfBoundsException
         */
        try {
            benutzerSuche = userController.loginUser(emailBenutzer);
        } catch(IndexOutOfBoundsException e) {
            FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Falsche Emailadresse oder Passwort",
							"Please enter correct username and Password"));
            return null;
        }        
        /* Sollte die Emailadresse vorhanden sein, wird das Passwort mit 
           dem eingegeben Passwort verglichen. Der Banutzer ist dann angemeldet
           Ansonsten wird eine Fehlermeldung ausgegegben.
        */
        if(benutzerSuche.getPasswort().equals(this.passwort)) {
            if(benutzerSuche.getStatusBenutzer()) {
                sessionData.setBenutzer(benutzerSuche);
                benutzer = sessionData.getBenutzer();
                sessionData.setEingeloggt(true);
                return "/startseite.xhtml?faces-redirect=true";
            } else {
                FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Dieser Benutzeraccount ist Deaktiviert bitte wenden Sie sich an den Administrator!",
							"Please enter correct username and Password"));
                return null;
            }
            
        } else {
            FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Falsche Emailadresse oder Passwort",
							"Please enter correct username and Password"));
            return null;
        }
    }
    
    /**
     * Logout Funktion:
     */
    public String logOut() {
        // Die Zwischengespeicherten Informationen Löschen:
        this.benutzer = null;
        sessionData.setBenutzer(null);
        // Felder im Loginbereich Leeren
        this.emailBenutzer = "";
        this.passwort = "";
        sessionData.setEingeloggt(false);
        // Zurück zur Startseite:
        return "/startseite.xhtml?faces-redirect=true";
    }
    
    /**
     * Abbrechen Funktion für die Eingabeformulare:
     * Redirect auf die Startseite, damit alle Werte verworfen werden.
     * @return 
     */
    public String abbrechen() {
        return "/startseite.xhtml?faces-redirect=true";                 
    }
    
    
    /**
     * 
     * Beginn Getter und Setter:
     *  
     */
    public Long getId() {
        return id;
    }

    public String getEmailBenutzer() {
        return emailBenutzer;
    }

    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
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

    public boolean isStatusBenutzer() {
        return statusBenutzer;
    }

    public void setBenutzer(Benutzer benutzer) {
        this.benutzer = benutzer;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmailBenutzer(String emailBenutzer) {
        this.emailBenutzer = emailBenutzer;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
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

    public Benutzer getBenutzer() {
        return benutzer;
    }
        
}