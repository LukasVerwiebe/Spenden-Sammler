
package com.mycompany.model;

import com.mycompany.controller.CharityController;
import com.mycompany.model.SessionData;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;
import spendensammler.jpa.dao.Datenzugriffsobjekt;
import spendensammler.jpa.entities.Charity;
import spendensammler.jpa.entities.Einkommen;
import spendensammler.jpa.entities.Kategorie;

/**
 * Java Bean Klasse: Zur Steuerung der Eingabeformulare und Auflistung
 * aller Charity Objekte in der Datenbank.
 * @author lu80238
 */
@Named(value="eingabeformularModel") // Nur für den Fall das eine weitere Bean mit selben Namen verwendet werden muss
@ViewScoped // Die Bean wir für jeden Request neu erstellt
public class EingabeformularModel implements Serializable{
    
    @Inject// Zur identifizierung einer Konstruktor Funktion
    private CharityController charityController;
    
    @Inject
    private SessionData sessionData;
    
    // Charity
    private Long id;    
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
    
    //Location:
    private String strasse;
    private String stadt;
    private String postleitzahl;
    
    // Contact:
    private String telefon;
    private String telefax;
    private String email;
    
    private List<Charity> charityList;
    private List<Charity> sortableList;    
    private Map<Charity,Boolean> merkerMap = new HashMap<>();
    private Charity charity;
    private static final String KEY_IN_SESSION = "charity"; 
    private String suchen;
    
    private Kategorie kategorie;
    private List<Kategorie> kategorieList;
    private List<Charity> selectedkategorien;

    
    @PostConstruct
    public void init() {
        //kategorieList = charityController.findKategorie(this.id);
    }
    
    /**
     * Konstruktor der Klasse:
     * Innerhalb wird die Liste mit allen Charity Objekten abgerufen und 
     * die aktuelen Obejtkte in einer HashMap gespeichert. Dies ist für 
     * den Späteren Aufruf z.B. für die Löschen Funktion oder Bearbeiten nötig.
     */
    public EingabeformularModel() {
//        Map<String,String> par_map = new HashMap<String,String>();
//        par_map=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        charity = (Charity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get( KEY_IN_SESSION );
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put( KEY_IN_SESSION, null );
        //charityList = charityController.findAll();
    }

    public String getSuchen() {
        return suchen;
    }

    public void setSuchen(String suchen) {
        this.suchen = suchen;
    }

    public void setOrgajahr(int orgajahr) {
        this.orgajahr = orgajahr;
    }

    public int getOrgajahr() {
        return orgajahr;
    }
    
    public static String getKEY_IN_SESSION() {
        return KEY_IN_SESSION;
    }

    public void setMerkerMap(Map<Charity, Boolean> merkerMap) {
        this.merkerMap = merkerMap;
    }
    
    public Map<Charity, Boolean> getMerkerMap() {
        return merkerMap;
    }

    public Long getMembercount() {
        return membercount;
    }

    public Long getEmployeeCount() {
        return employeeCount;
    }

    public Long getVolunteerCount() {
        return volunteerCount;
    }

    public String getManagement() {
        return management;
    }
    
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public int getFoundingYear() {
        return foundingYear;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getWorldview() {
        return worldview;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public String getFieldsOfAction() {
        return fieldsOfAction;
    }

    public String getAdvertingFinancialInspektion() {
        return advertingFinancialInspektion;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public void setFoundingYear(int foundingYear) {
        this.foundingYear = foundingYear;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public void setWorldview(String worldview) {
        this.worldview = worldview;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public void setFieldsOfAction(String fieldsOfAction) {
        this.fieldsOfAction = fieldsOfAction;
    }

    public void setAdvertingFinancialInspektion(String advertingFinancialInspektion) {
        this.advertingFinancialInspektion = advertingFinancialInspektion;
    }

    public void setMembercount(Long membercount) {
        this.membercount = membercount;
    }

    public void setEmployeeCount(Long employeeCount) {
        this.employeeCount = employeeCount;
    }

    public void setVolunteerCount(Long volunteerCount) {
        this.volunteerCount = volunteerCount;
    }

    public void setManagement(String management) {
        this.management = management;
    }

    public List<Charity> getSortableList() {
        return charityList;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getStadt() {
        return stadt;
    }

    public void setStadt(String stadt) {
        this.stadt = stadt;
    }

    public String getPostleitzahl() {
        return postleitzahl;
    }

    public void setPostleitzahl(String postleitzahl) {
        this.postleitzahl = postleitzahl;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    public int aktuellesJahr() {
        // Aktuelles Jahr ermitteln:
        Date dt = new Date();
        int jahr = dt.getYear();
        int aktuellesJahr = jahr + 1900;         
        return aktuellesJahr;
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
    public String newCharity() {
        if((this.foundingYear >= 1900 && this.foundingYear <= aktuellesJahr()) 
                && (this.orgajahr >= 1900 && this.orgajahr <= aktuellesJahr())) {
            
            if(sessionData.getSelektionCheck()) {
                charityController.newCharity(this.name, this.logoPath, this.foundingYear, this.homepage, this.worldview, this.fieldsOfAction, 
                    this.advertingFinancialInspektion, this.membercount, this.employeeCount, this.volunteerCount, this.management, this.orgajahr, 
                    this.strasse, this.stadt, this.postleitzahl, this.telefon, this.telefax, this.email);        
                return "/Selektion.xhtml?faces-redirect=true";
            } else {
                charityController.newCharity(this.name, this.logoPath, this.foundingYear, this.homepage, this.worldview, this.fieldsOfAction, 
                    this.advertingFinancialInspektion, this.membercount, this.employeeCount, this.volunteerCount, this.management, this.orgajahr, 
                    this.strasse, this.stadt, this.postleitzahl, this.telefon, this.telefax, this.email);        
                return "/Admin/AdminVerwaltung.xhtml?faces-redirect=true";
            }
        } else {         
            FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Die Jahres Angaben müssen größer als 1900 sein und kleinergleich das Aktuelle Jahr!",
							"Please enter correct username and Password"));
            return null;
        }
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
                    this.strasse, this.stadt, this.postleitzahl, this.telefon, this.telefax, this.email);        
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
    
    
    /**
     * Aufruf der Controller Funktion zum Updaten eines ausgewählten Charity Objekts 
     * und die Speicherung der Änderungen in der Datenbank. 
     * 
     * Nach der Ausführung wird ein Redirect auf die Startseite index.xhtml
     * durchgeführt. Dies ist zwingend nötig, da sonst mit F5 immer wieder neue
     * Obejtke angelegt werden können.
     * @return 
     */
    public String updateCharity() {
        charityController.updateCharity(charity.getIdCharity(), charity.getName(), charity.getLogoPath(), charity.getFoundingYear(), charity.getHomepage(), charity.getWorldview(),
                charity.getFieldsOfAction(), charity.getAdvertingFinancialInspektion(), charity.getCh_orga().getMembercount(), charity.getCh_orga().getEmployeecount(), 
                charity.getCh_orga().getVolounteersCount(), charity.getCh_orga().getManagmentbody(), charity.getCh_orga().getOrgaYear(), charity.getStrasse(),
                charity.getStadt(), charity.getPostleitzahl(), charity.getTelefon(), charity.getTelefax(), charity.getEmail());
        return "/Admin/AdminVerwaltung.xhtml?faces-redirect=true";
    }
    
    /**
     * Aufruf der Controller Funtktion zur Ermittlung aller in der Datenbank 
     * befindlichen Charity Objekte. 
     * 
     * Wenn das Feld 'Suchen' auf der Seite index.xhtml gefüllt wurde, soll
     * eine gefilterte Suche nach den Objekten stattfinden, in diesem Fall wird
     * die Liste neu geladen und entsprechend reduziert angezeigt.
     * 
     * Der Aufruf der Funktion passiert automatisch beim Laden der Seite
     * index.xhtml
     * @return 
     */
    public List<Charity> getCharityList() {       
        if(suchen == null) {
            charityList = charityController.findAll();
        } else {
            charityList = charityController.findCharity(suchen);
        }        
        return charityList;
    }
    
    /**
     * Funktion zum Löschen von Ausgewählten Objekten:
     * Es wird eine Map mit allen Charity Objekten gefüllt, in dieser wird 
     * fetgehalten, ob die Checkbox zum Lsöchen angehakt war oder nicht.
     * 
     * Wenn nicht, werden die Objekte im nächsten Schritt wieder entfernt aus
     * der Liste, sodass nur die benötigten Obejkte übrig bleiben.
     * Diese werden dann anhand der ID identifiziert und aus der Datenbank
     * enfernt.
     * @return 
     */
    public String markierteElementeLoeschen() {        
        for( Map.Entry<Charity,Boolean> mapEntry : merkerMap.entrySet() ) {
            if( mapEntry.getValue().booleanValue() && !charityList.remove( mapEntry.getKey() ) ) {
//            addFacesMessage( FacesMessage.SEVERITY_ERROR, "Fehler: Datenelement existiert nicht mehr." );
            }
        }  
        
        Iterator it = merkerMap.entrySet().iterator();
	while (it.hasNext()) {
	    Map.Entry<Charity, Boolean> entry = (Map.Entry)it.next();
	    if(entry.getValue() == true) {
                charityController.elementeLoeschen(entry.getKey());
            } 
	}
        merkerMap.clear();
        return null;    
    }
    
    /**
     * Abbrechen Funktion für die Eingabeformulare:
     * Redirect auf die Startseite, damit alle Werte verworfen werden.
     * @return 
     */
    public String abbrechen() {
        if(sessionData.getSelektionCheck()) {
            return "/Selektion.xhtml?faces-redirect=true";
        } else {
            return "/Admin/AdminVerwaltung.xhtml?faces-redirect=true";
        }         
    }
    
    /**
     * Abbrechen Funktion für die OrgaBenutzer Formular:
     * Redirect auf die Startseite, damit alle Werte verworfen werden.
     * @return 
     */
    public String abbrechenOrga() {
        sessionData.setBenutzerOrga(null);
        return "/startseite.xhtml?faces-redirect=true";         
    }
    
    /**
     * Suchfunktion:
     * @return 
     */
    public String suchen() {
        return "index.xhtml";
    }
    
   /**
    * Auswahl für den Löschvorgang wieder entfernen:
    */
    public void auswahlEnternen() {
        Iterator it = merkerMap.entrySet().iterator();
	while (it.hasNext()) {
	    Map.Entry<Charity, Boolean> entry = (Map.Entry)it.next();
	    if(entry.getValue() == true) {
                entry.setValue(false);
            } 
	}
        merkerMap.clear();
    }
    
    public void auswahlSucheEntfernen() {
        this.suchen = "";
        this.charityList = charityController.findAll();
    }
    
    /**
     * Funktion zum Editieren eines einzelnen ausgewähleten Objektes das in der
     * Tabelle ausgewählte Obejekt wird der HashMap hinzugefügt.
     * 
     * Hier wird kein Redirect durchgeführt, da die Daten direkt an das
     * Eingabeformular weitergegeben werden müssen.
     * @return 
     */
    public String einzelnesElementEditieren(int id) {
        //charity = (Charity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get( KEY_IN_SESSION );
        //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put( KEY_IN_SESSION, null );        
        //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put( KEY_IN_SESSION, charity);
        
        
        return "EingabeUpdate.xhtml";        
    }    
    
    /**
     * Rückgabe des Charity Objektes
     * Wird benötigt für die Löschen und Bearbeiten Funktion.
     * @return 
     */
    public Charity getCharity() {
        return charity;
    }
    
    /**
     * Setzen des Charity Objektes
     * Wird benötigt für die Löschen und Bearbeiten Funktion.
     * @param charity 
     */
    public void setCharity(Charity charity) {
        this.charity = charity;
    }
    
}