
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
@Named(value="benutzerBearbeitenAdminModel")
@SessionScoped
public class BenutzerBearbeitenAdminModel implements Serializable{
    
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
    
    private Benutzer selectedBenutzer;
    
    @PostConstruct
    public void intit() {
        // BenutzerInformationen nach der Registierung:
        //benutzer = sessionData.getBenutzer();
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
        userController.updateUser(selectedBenutzer.getIdBenutzer(), selectedBenutzer.getVorname(), selectedBenutzer.getNachname(), selectedBenutzer.getEmailBenutzer(),
                selectedBenutzer.getPasswort(), selectedBenutzer.getRolleBenutzer(), selectedBenutzer.getStatusBenutzer(), selectedBenutzer.getStadt(), selectedBenutzer.getStrasse(), selectedBenutzer.getPostleitzahl());        
        return "/Admin/AdminBenutzer.xhtml?faces-redirect=true";        
    }
    
    /**
     * Abbrechen Funktion für die Eingabeformulare:
     * Redirect auf die Startseite, damit alle Werte verworfen werden.
     * @return 
     */
    public String abbrechen() {
        return "/Admin/AdminBenutzer.xhtml?faces-redirect=true";                 
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

    public Benutzer getSelectedBenutzer() {
        return selectedBenutzer;
    }

    public void setSelectedBenutzer(Benutzer selectedBenutzer) {
        this.selectedBenutzer = selectedBenutzer;
    }
    
    
}