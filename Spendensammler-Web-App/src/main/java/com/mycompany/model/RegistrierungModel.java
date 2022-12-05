
package com.mycompany.model;

import com.mycompany.controller.CharityController;
import com.mycompany.controller.UserController;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import org.primefaces.PrimeFaces;
import spendensammler.jpa.entities.Charity;
import spendensammler.jpa.entities.Benutzer;

/**
 *
 * @author lu80238
 */
@Named(value="registrierungModel") // Nur für den Fall das eine weitere Bean mit selben Namen verwendet werden muss
@RequestScoped // Die Bean wir für jeden Request neu erstellt
public class RegistrierungModel {
    
    @Inject// Zur identifizierung einer Konstruktor Funktion
    private UserController userController;
    
    @Inject
    private SessionData sessionData;
    
    private Long id; 
    private String vorname;
    private String nachname;
    private String strasse;
    private String stadt;
    private String passwort;
    private String email;
    private int postleitzahl;
    private String rolleBenutzer;
    
    private Benutzer user;
    private static final String KEY_IN_SESSION = "user"; 
    
    public RegistrierungModel() {
    }
    
    /**
     * Methode zum anlegen eines neuen Benutzers
     * @return 
     */
    public String newUser() {
        if(!isValidEmailAddress(this.email)) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Die Emailadresse ist nicht korrekt!");
            PrimeFaces.current().dialog().showMessageDynamic(message);
            return null;
        } else {
            if(userController.findeBenutzer(email).isEmpty()) {
                if(this.passwort.length() >= 8) {
//                    if(this.rolleBenutzer.equals("Organisation")) {
//                        // Zwischenablage des Benutzers:
//                        Benutzer benutzer = new Benutzer(this.vorname, this.nachname, this.email, this.passwort, "Organisation", false);  
//                        benutzer.setPostleitzahl(this.postleitzahl);
//                        benutzer.setStadt(this.stadt);
//                        benutzer.setStrasse(this.strasse);
//                        sessionData.setBenutzerOrga(benutzer);
//                        return "/User/BenutzerOrgaAnlegen.xhtml?faces-redirect=true";
//                    }
                                        
                    userController.newUser(this.vorname, this.nachname, this.email, this.passwort, this.strasse, this.stadt, "Benutzer");
                    return "/User/UserRegisterBestaetigung.xhtml?faces-redirect=true";
                } else {
                    FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Das Kennwort muss mindestens 8 Zeichen lang sein!",
							"Please enter correct username and Password"));
                    return null;
                }
            }
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Die Emailadresse wird bereits verwendet!");
            PrimeFaces.current().dialog().showMessageDynamic(message);
            return null;
        }
    }
    
    /**
     * Methode um eine Emailadresse zu Prüfen und Festzustellen ob es sich bei 
     * einem String um eine Emailadresse handelt
     * @return 
     */
    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
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
     * Rückgabe des User Objektes
     * Wird benötigt für die Löschen und Bearbeiten Funktion.
     * @return 
     */
    public Benutzer getUser() {
        return user;
    }
    
    /**
     * Setzen des User Objektes
     * Wird benötigt für die Löschen und Bearbeiten Funktion.
     * @param user 
     */
    public void setUser(Benutzer user) {
        this.user = user;
    }
    
    // Getter und Setter:

    public Long getId() {
        return id;
    }

    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public String getStrasse() {
        return strasse;
    }

    public String getStadt() {
        return stadt;
    }

    public String getPasswort() {
        return passwort;
    }

    public String getEmail() {
        return email;
    }

    public int getPostleitzahl() {
        return postleitzahl;
    }

    public static String getKEY_IN_SESSION() {
        return KEY_IN_SESSION;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public void setStadt(String stadt) {
        this.stadt = stadt;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPostleitzahl(int postleitzahl) {
        this.postleitzahl = postleitzahl;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRolleBenutzer() {
        return rolleBenutzer;
    }

    public void setRolleBenutzer(String rolleBenutzer) {
        this.rolleBenutzer = rolleBenutzer;
    }
    
}