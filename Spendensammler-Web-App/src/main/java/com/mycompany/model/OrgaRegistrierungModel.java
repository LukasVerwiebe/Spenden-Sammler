
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
import java.io.Serializable;
import java.util.Date;
import org.primefaces.PrimeFaces;
import spendensammler.jpa.entities.Charity;
import spendensammler.jpa.entities.Benutzer;

/**
 *
 * @author lu80238
 */
@Named(value="orgaRegistrierungModel") // Nur für den Fall das eine weitere Bean mit selben Namen verwendet werden muss
@RequestScoped // Die Bean wir für jeden Request neu erstellt
public class OrgaRegistrierungModel implements Serializable {
    
    @Inject// Zur identifizierung einer Konstruktor Funktion
    private UserController userController;
    
    @Inject// Zur identifizierung einer Konstruktor Funktion
    private CharityController charityController;
    
    @Inject
    private SessionData sessionData;
    
    private Long id; 
    private String vorname;
    private String nachname;
    private String strasse;
    private String stadt;
    private String passwort;
    private String email;
    private String postleitzahl;
    private String rolleBenutzer;
    
    // Charity   
    private String name;    
    private String logoPath;
    private int foundingYear;
    private String homepage;
    private String worldview;
    private boolean deleted;
    private String fieldsOfAction;
    private String advertingFinancialInspektion;
    
    // Charity_orga
    private int orgajahr;
    private Long membercount;
    private Long employeeCount;
    private Long volunteerCount;
    private String management;
    
    // Contact:
    private String telefon;
    private String telefax;
    private String orgaemail;
    
    private Benutzer user;
    private static final String KEY_IN_SESSION = "user"; 
    
    
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
                    
                    // Zwischenablage des Benutzers:
                    Benutzer benutzer = new Benutzer(null, null, this.email, this.passwort, "Organisation", false);  
                    benutzer.setPostleitzahl(0);
                    benutzer.setStadt(null);
                    benutzer.setStrasse(null);
                    sessionData.setBenutzerOrga(benutzer);
                        
                    // Orga Anlegen:
                    newCharityOrga();
                    return "/Orga/OrgaRegisterBestaetigung.xhtml?faces-redirect=true";
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
     * Aufruf der Controller Funktion zum anlegen eines neuen Charity Objekts 
     * und die Speicherung von diesem in der Datenbank. 
     * 
     * Nach der Ausführung wird ein Redirect auf die Startseite index.xhtml
     * durchgeführt. Dies ist zwingend nötig, da sonst mit F5 immer wieder neue
     * Obejtke angelegt werden können.
     * @return 
     */
    public String newCharityOrga() {
        if((this.foundingYear >= 1900 && this.foundingYear <= aktuellesJahr()) 
                && (this.orgajahr >= 1900 && this.orgajahr <= aktuellesJahr())) {
            
            charityController.newCharityOrga(this.name, this.logoPath, this.foundingYear, this.homepage, this.worldview, this.fieldsOfAction, 
            this.advertingFinancialInspektion, this.membercount, this.employeeCount, this.volunteerCount, this.management, this.orgajahr, 
                    this.strasse, this.stadt, this.postleitzahl, this.telefon, this.telefax, this.orgaemail);        
            return "/startseite.xhtml?faces-redirect=true"; 
        } else {
            FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Die Jahres Angaben müssen größer als 1900 sein und kleinergleich das Aktuelle Jahr!",
							"Please enter correct username and Password"));
            return null;
        }  
    }
    
    public int aktuellesJahr() {
        // Aktuelles Jahr ermitteln:
        Date dt = new Date();
        int jahr = dt.getYear();
        int aktuellesJahr = jahr + 1900;         
        return aktuellesJahr;
    }    
    
    /**
     * Abbrechen Funktion für die Eingabeformulare:
     * Redirect auf die Startseite, damit alle Werte verworfen werden.
     * @return 
     */
    public String abbrechen() {        
        return "/startseite.xhtml?faces-redirect=true";        
    }
    
    public String register() {        
        return "/Orga/OrgaRegistrieren.xhtml?faces-redirect=true";        
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

    public String getPostleitzahl() {
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

    public void setPostleitzahl(String postleitzahl) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public int getFoundingYear() {
        return foundingYear;
    }

    public void setFoundingYear(int foundingYear) {
        this.foundingYear = foundingYear;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getWorldview() {
        return worldview;
    }

    public void setWorldview(String worldview) {
        this.worldview = worldview;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getFieldsOfAction() {
        return fieldsOfAction;
    }

    public void setFieldsOfAction(String fieldsOfAction) {
        this.fieldsOfAction = fieldsOfAction;
    }

    public String getAdvertingFinancialInspektion() {
        return advertingFinancialInspektion;
    }

    public void setAdvertingFinancialInspektion(String advertingFinancialInspektion) {
        this.advertingFinancialInspektion = advertingFinancialInspektion;
    }

    public int getOrgajahr() {
        return orgajahr;
    }

    public void setOrgajahr(int orgajahr) {
        this.orgajahr = orgajahr;
    }

    public Long getMembercount() {
        return membercount;
    }

    public void setMembercount(Long membercount) {
        this.membercount = membercount;
    }

    public Long getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(Long employeeCount) {
        this.employeeCount = employeeCount;
    }

    public Long getVolunteerCount() {
        return volunteerCount;
    }

    public void setVolunteerCount(Long volunteerCount) {
        this.volunteerCount = volunteerCount;
    }

    public String getManagement() {
        return management;
    }

    public void setManagement(String management) {
        this.management = management;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getTelefax() {
        return telefax;
    }

    public void setTelefax(String telefax) {
        this.telefax = telefax;
    }

    public String getOrgaemail() {
        return orgaemail;
    }

    public void setOrgaemail(String orgaemail) {
        this.orgaemail = orgaemail;
    }
    
}