
package com.mycompany.model;

import com.mycompany.controller.CharityController;
import com.mycompany.model.SessionData;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.bean.ManagedProperty;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import spendensammler.jpa.entities.Charity;
import spendensammler.jpa.entities.Kategorie;
import spendensammler.jpa.entities.PlaceOfAction;

@Named(value="organisationsVerwaltungModel")
@ViewScoped
public class OrganisationsVerwaltungModel implements Serializable{
    
    @Inject
    private CharityController charityController;
    
    @Inject
    private IndexModel indexView;
    
    @Inject
    private KategorieUpdateModel kategorieUpdateView;
    
    @Inject
    private PlaceOfActionUpdateModel placeOfActionUpdateView;
    
    @Inject
    private SessionData sessionData;
    
    private Charity selectedCharity;
    
    private Long id;    
    private String name;    
    private String logoPath;
    private int foundingYear;
    private String homepage;
    private String worldview;
    private boolean deleted;
    private String fieldsOfAction;
    private String advertingFinancialInspektion;
    private int orgajahr;
    private Long membercount;
    private Long employeeCount;
    private Long volunteerCount;
    private String management;
    
    private String strasse;
    private String stadt;
    private String postleitzahl;
    
    private String telefon;
    private String telefax;
    private String email;
    
    private long idKategorie;
    private String nameKategorie;
    private Long idPlaceOfAction;   
    private String landPlaceOfAction;
    
    private Kategorie kategorie;
    private List<Kategorie> kategorieList;
    private Kategorie selectedkategorien;
    private List<Kategorie> kategorienliste;
    
    private PlaceOfAction placeofaction;
    private List<PlaceOfAction> placeOfActionList;
    private PlaceOfAction selectedplaceofaction;
    private List<PlaceOfAction> placeofactionliste;
    

    @PostConstruct
    public void intit() {
        selectedCharity = sessionData.getBenutzer().getCharity();
        kategorienliste = selectedCharity.getKategorie();
        placeofactionliste = selectedCharity.getPlaceofaction();
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
        if(sessionData.getSelektionCheck()) {
            charityController.updateCharity(selectedCharity.getIdCharity(), selectedCharity.getName(), selectedCharity.getLogoPath(), selectedCharity.getFoundingYear(), selectedCharity.getHomepage(), selectedCharity.getWorldview(),
                selectedCharity.getFieldsOfAction(), selectedCharity.getAdvertingFinancialInspektion(), selectedCharity.getCh_orga().getMembercount(), selectedCharity.getCh_orga().getEmployeecount(), 
                selectedCharity.getCh_orga().getVolounteersCount(), selectedCharity.getCh_orga().getManagmentbody(), selectedCharity.getCh_orga().getOrgaYear(), 
                selectedCharity.getStrasse(), selectedCharity.getStadt(), selectedCharity.getPostleitzahl(), selectedCharity.getTelefon(), selectedCharity.getTelefax(), selectedCharity.getEmail());
            return "/Selektion.xhtml?faces-redirect=true";
        } else {
            charityController.updateCharity(selectedCharity.getIdCharity(), selectedCharity.getName(), selectedCharity.getLogoPath(), selectedCharity.getFoundingYear(), selectedCharity.getHomepage(), selectedCharity.getWorldview(),
                selectedCharity.getFieldsOfAction(), selectedCharity.getAdvertingFinancialInspektion(), selectedCharity.getCh_orga().getMembercount(), selectedCharity.getCh_orga().getEmployeecount(), 
                selectedCharity.getCh_orga().getVolounteersCount(), selectedCharity.getCh_orga().getManagmentbody(), selectedCharity.getCh_orga().getOrgaYear(), 
                selectedCharity.getStrasse(), selectedCharity.getStadt(), selectedCharity.getPostleitzahl(), selectedCharity.getTelefon(), selectedCharity.getTelefax(), selectedCharity.getEmail());
            return "/Orga/OrganisationsVerwaltung.xhtml?faces-redirect=true";
        }
    }
    
    public String updateSelectedCharity(Charity charity) {
        setSelectedCharity(charity);
        return "/EingabeUpdate.xhtml";
    }
    
    public String updateSelectedKategorie() {
        kategorieUpdateView.setSelectedKategorie(kategorieList.get(0));
        return "/KategorieformularUpdate.xhtml?faces-redirect=true";
    }
    
    public String updateSelectedPlaceOfAction() {
        placeOfActionUpdateView.setSelectedPlaceOfAction(placeOfActionList.get(0));
        return "/PlaceOfActionUpdate.xhtml?faces-redirect=true";
    }
    
    /**
     * Abbrechen Funktion für die Eingabeformulare:
     * Redirect auf die Startseite, damit alle Werte verworfen werden.
     * @return 
     */
    public String abbrechen() {
        return "/Orga/OrganisationsVerwaltung.xhtml?faces-redirect=true";        
    }    
    
    
    public void newKategorie() {        
        sessionData.setSelectedCharity(selectedCharity); 
    }
    
    public void newPlaceOfAction() {
        sessionData.setSelectedCharity(selectedCharity);        
    }
    
    
    public String loeschenKategorie() {
        for(int i = 0; i < kategorieList.size(); i++) {
            charityController.elementeLoeschenKategorie(kategorieList.get(i));
            kategorienliste.remove(kategorieList.get(i));
        }
        kategorieList.clear();
        return "/Orga/OrgaKategorien.xhtml?faces-redirect=true";    
    }
    
    public String loeschenPlaceOfAction() {
        for(int i = 0; i < placeOfActionList.size(); i++) {
            charityController.elementeLoeschenPlaceOfAction(placeOfActionList.get(i));
            placeofactionliste.remove(placeOfActionList.get(i));
        }
        placeOfActionList.clear();
        return "/Orga/OrgaArbeitsbereiche.xhtml?faces-redirect=true";    
    }
    
    /**
     * 
     * Beginn Getter und Setter:
     *  
     */
    
    public Charity getSelectedCharity() {
        return selectedCharity;
    }

    public void setSelectedCharity(Charity selectedCharity) {
        this.selectedCharity = selectedCharity;
    }    

    public CharityController getCharityController() {
        return charityController;
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

    public int getOrgajahr() {
        return orgajahr;
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

    public void setCharityController(CharityController charityController) {
        this.charityController = charityController;
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

    public void setOrgajahr(int orgajahr) {
        this.orgajahr = orgajahr;
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

    public Kategorie getKategorie() {
        return kategorie;
    }

    public void setKategorie(Kategorie kategorie) {
        this.kategorie = kategorie;
    }

    public List<Kategorie> getKategorieList() {
        return kategorieList;
    }

    public void setKategorieList(List<Kategorie> kategorieList) {
        this.kategorieList = kategorieList;
    }

    public Kategorie getSelectedkategorien() {
        return selectedkategorien;
    }

    public void setSelectedkategorien(Kategorie selectedkategorien) {
        this.selectedkategorien = selectedkategorien;
    }

    public long getIdKategorie() {
        return idKategorie;
    }

    public void setIdKategorie(long idKategorie) {
        this.idKategorie = idKategorie;
    }

    public String getNameKategorie() {
        return nameKategorie;
    }

    public void setNameKategorie(String nameKategorie) {
        this.nameKategorie = nameKategorie;
    }

    public PlaceOfAction getPlaceofaction() {
        return placeofaction;
    }

    public void setPlaceofaction(PlaceOfAction placeofaction) {
        this.placeofaction = placeofaction;
    }

    public List<PlaceOfAction> getPlaceOfActionList() {
        return placeOfActionList;
    }

    public void setPlaceOfActionList(List<PlaceOfAction> placeOfActionList) {
        this.placeOfActionList = placeOfActionList;
    }

    public PlaceOfAction getSelectedplaceofaction() {
        return selectedplaceofaction;
    }

    public void setSelectedplaceofaction(PlaceOfAction selectedplaceofaction) {
        this.selectedplaceofaction = selectedplaceofaction;
    }

    public Long getIdPlaceOfAction() {
        return idPlaceOfAction;
    }

    public void setIdPlaceOfAction(Long idPlaceOfAction) {
        this.idPlaceOfAction = idPlaceOfAction;
    }

    public String getLandPlaceOfAction() {
        return landPlaceOfAction;
    }

    public void setLandPlaceOfAction(String landPlaceOfAction) {
        this.landPlaceOfAction = landPlaceOfAction;
    }

    public List<Kategorie> getKategorienliste() {
        return kategorienliste;
    }

    public void setKategorienliste(List<Kategorie> kategorienliste) {
        this.kategorienliste = kategorienliste;
    }

    public List<PlaceOfAction> getPlaceofactionliste() {
        return placeofactionliste;
    }

    public void setPlaceofactionliste(List<PlaceOfAction> placeofactionliste) {
        this.placeofactionliste = placeofactionliste;
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
    
     
}