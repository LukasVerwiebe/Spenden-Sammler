
package com.mycompany.controller;

import com.mycompany.model.SessionData;
import com.mycompany.services.CharityService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import spendensammler.jpa.entities.Benutzer;
import spendensammler.jpa.entities.Charity;
import spendensammler.jpa.entities.Charity_Organisation;
import spendensammler.jpa.entities.Einkommen;
import spendensammler.jpa.entities.Kategorie;
import spendensammler.jpa.entities.PlaceOfAction;
import spendensammler.jpa.entities.Quittung;

/**
 * Java Bean Klasse: Zur Steurung der Geschäftlogik
 * Über diese Klasse wird auf das Datenzugriffsobjekt zugegriffen.
 * @author lu80238
 */
@Named(value="charityController")
@RequestScoped
public class CharityController implements Serializable{
    
    /**
     * Erstellung eines neuen Datenzugriffsobjekts
     * Ist noch notwendig, da diese Klasse noch keine Java Bean ist
     */
    //private Datenzugriffsobjekt dao = new Datenzugriffsobjekt("Spendensammler-JPA-PhasePU");
    private List<Charity> charityList;
    private List<String> placeList;
    private List<String> categoryList;
    private final static String[] worldviews;
    
    @Inject
    private CharityService charityService;
    
    @Inject
    private SessionData sessionData;
        
    @PostConstruct
    public void charityController() {
        charityList = charityService.findAll();
    }
    
    static {
        worldviews = new String[] {"evangelisch", "katholisch", "christlich", "unabhängig" };
    }
    
    /**
     * Erstellung eines neuen Charity Objekts:
     * Zuerst wird das Charity Objekt angelegt, dann das Charity_Organisation Objekt,
     * da diese beide festmiteinander verbunden sein sollen wird auch ein leeres
     * Charity_Organisation Objekt angelegt wenn nötig.
     * 
     * Nach dem Anlegen werden die beiden Objekte miteinader verknüpft und dem
     * Datenzugriffsobjekt übergeben.
     * 
     * @param name
     * @param bild
     * @param year
     * @param website
     * @param worldview
     * @param fieldsOfAction
     * @param bericht
     * @param member
     * @param employee
     * @param volunteers
     * @param management
     * @param orgayear 
     */
    public void newCharity(String name, String bild, int year, String website, String worldview, String fieldsOfAction, String bericht,
            Long member, Long employee, Long volunteers, String management, int orgayear, String strasse, String stadt, String postleitzahl, 
            String telefon, String telefax, String email) {
        try { 
            // Erst Charity anlegen:
            Charity charity = new Charity(name, bild, year, website, worldview, fieldsOfAction, bericht);            
            // Dann Charity_Organisation anlegen:
            Charity_Organisation chOrg = new Charity_Organisation(member, employee, volunteers, management, orgayear, charity);
            
            // Setzen der Verknüpfung zwischen Charity und Charity_Organisation:
            charity.setCh_orga(chOrg);
            chOrg.setCharity(charity);
            
            // Setzen Location und Contact:
            charity.setStrasse(strasse);
            charity.setStadt(stadt);
            charity.setPostleitzahl(postleitzahl);
            charity.setTelefon(telefon);
            charity.setTelefax(telefax);
            charity.setEmail(email);
            
            if(sessionData.getSelektionCheck()) {
                sessionData.getSelectedCharitys().add(charity);
            }           
            
            charityService.createNewCharity(charity);
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public void newCharityOrga(String name, String bild, int year, String website, String worldview, String fieldsOfAction, String bericht,
            Long member, Long employee, Long volunteers, String management, int orgayear, String strasse, String stadt, String postleitzahl, 
            String telefon, String telefax, String email) {
        try { 
            // Erst Charity anlegen:
            Charity charity = new Charity(name, bild, year, website, worldview, fieldsOfAction, bericht);            
            // Dann Charity_Organisation anlegen:
            Charity_Organisation chOrg = new Charity_Organisation(member, employee, volunteers, management, orgayear, charity);
                        
            // Setzen der Verknüpfung zwischen Charity und Charity_Organisation:
            charity.setCh_orga(chOrg);
            chOrg.setCharity(charity);
            charity.setBenutzer(sessionData.getBenutzerOrga());
            sessionData.getBenutzerOrga().setCharity(charity);
            
            // Setzen Location und Contact:
            charity.setStrasse(strasse);
            charity.setStadt(stadt);
            charity.setPostleitzahl(postleitzahl);
            charity.setTelefon(telefon);
            charity.setTelefax(telefax);
            charity.setEmail(email);
            
            if(sessionData.getSelektionCheck()) {
                sessionData.getSelectedCharitys().add(charity);
            }           
            
            charityService.createNewCharity(charity);
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    /**
     * Update eines bereits vorhandenen Objekts:
     * Die neu eingegebenen Daten Werden dem Datenzuriffsobjekt übergeben, dort
     * werden die Daten über die Klassen Setter neu gesetzt und das Objekt wird 
     * in der Datenbank auf den neuen Stand gebracht.
     * 
     * @param id
     * @param name
     * @param bild
     * @param yearg
     * @param website
     * @param worldview
     * @param fieldsOfAction
     * @param bericht
     * @param member
     * @param employee
     * @param volunteers
     * @param management
     * @param orgayear 
     */
    public void updateCharity(Long id, String name, String bild, int yearg, String website, String worldview, String fieldsOfAction, String bericht,
            Long member, Long employee, Long volunteers, String management, int orgayear, String strasse, String stadt, String postleitzahl, String telefon, 
            String telefax, String email) {
        try {
            charityService.updateCharity(id, name, bild, yearg, website, worldview, fieldsOfAction, bericht,
                    member, employee, volunteers, management, orgayear, strasse, stadt, postleitzahl, telefon, telefax, email);            
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    /**
     * Auflistung aller Charity Objekte in der Datenbank.
     * @return 
     */
    public List<Charity> findAll() {
        return charityList;
    }
    
    public List<String> findAllPlaces() {
        placeList = charityService.findAllPlaces();
        return placeList;
    }
    
    public List<String> findAllCategories() {
        categoryList = charityService.findAllCategories();
        return categoryList;
    }
    
    public List<String> findCategories(Long charityID) {
        categoryList = charityService.findCategories(charityID);
        return categoryList;
    }
    
    public List<String> findPlaces(Long charityID) {
        placeList = charityService.findPlaces(charityID);
        return placeList;
    }
    
    /**
     * Neue Kategorie
     * @return 
     */
    public void newKategorie(String nameKategorie, Charity charity) {
        try {            
            //Kategorie anlegen:
            Kategorie kategorie = new Kategorie(nameKategorie); 
            
            //Setzen der Verknüpfung zwischen Kategorie und Charity:
            kategorie.setCharity(charity);
            charity.getKategorie().add(kategorie);
            
//            if(sessionData.getSelektionCheck()) {
//                sessionData.getSelectedCharitys().add(charity);
//            }           
            
            charityService.createNewKategorie(kategorie);
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    /**
     * Neuer PlaceOfAction
     * @return 
     */
    public void newPlaceOfAction(String landPlaceOfAction, Charity charity) {
        try {            
            //PlaceOfaction anlegen:
            PlaceOfAction placeofAction = new PlaceOfAction(landPlaceOfAction, ""); 
            
            //Setzen der Verknüpfung zwischen Kategorie und Charity:
            placeofAction.setCharity(charity);
            charity.getPlaceofaction().add(placeofAction);
            
//            if(sessionData.getSelektionCheck()) {
//                sessionData.getSelectedCharitys().add(charity);
//            }           
            
            charityService.createNewPlaceOfAction(placeofAction);
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public void createEinkommen(int jahr, double betrag, Charity charity) {
        try {            
            //PlaceOfaction anlegen:
            Einkommen einkommen = new Einkommen(jahr, betrag); 
            
            //Setzen der Verknüpfung zwischen Kategorie und Charity:
            einkommen.setCharity(charity);
            charity.getEinkommen().add(einkommen);       
            
            charityService.createEinkommen(einkommen);
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public void upadateKategorie(Long idKategorie, String nameKategorie) {
        try {         
            charityService.updateKategorie(idKategorie, nameKategorie);
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public void upadatePlaceOfAction(Long idPlace, String namePlace) {
        try {         
            charityService.updatePlaceOfAction(idPlace, namePlace);
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public void updateEinkommen(Long idEinkommen, double betrag) {
        try {         
            charityService.updateEinkommen(idEinkommen, betrag);
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    
    /**
     * Auflistung aller Charity Objekte in der Datenbank bei denen der 'Name'
     * das eingegebene Such Wort enthält (LIKE %_%)
     * @param name
     * @return 
     */
    public List<Charity> findCharity(String name) {        
        List<Charity> charityList = charityService.findeCharity(name);        
        return charityList;
    }
    
    public List<Charity> gefilterteCharities(String worldview, String land, String category) {
        List<Charity> charityList = charityService.gefilterteCharities(worldview, land, category);
        return charityList;
    }  
    
    /**
     * Funtkion zum Löschen von Objekten:
     * Ein ausgewähltes Charity Objekt wird dem Datenzugriffsobjekt übergeben
     * dabei wird über eine remove cascade ebenfalls das verknüpfte
     * charity_Organisation Obejekt mit entfernt.
     * @param charity 
     */
    public void elementeLoeschen(Charity charity) {
        charityService.removeCharity(charity);
    }   
    
    public void elementeLoeschenKategorie(Kategorie kategorie) {
        charityService.removeKategorie(kategorie);
    }
    
    public void elementeLoeschenPlaceOfAction(PlaceOfAction placeofaction) {
        charityService.removePlaceOfaction(placeofaction);
    }
    
    public List<Einkommen> jahrEinkommen(int jahr, Long id) {
        List<Einkommen> einkommenList = charityService.jahrEinkommen(jahr, id);
        return einkommenList;
    }
    
    public List<Einkommen> orgaEinkommen(Long id) {
        List<Einkommen> einkommenList = charityService.orgaEinkommen(id);
        return einkommenList;
    }
    
    public List<String> getWorldviews() {
        return Arrays.asList(worldviews);
    }
    
    public List<Quittung> getOrgaQuittung(Long id) {
        List<Quittung> quittungList = charityService.getOrgaQuittung(id);
        return quittungList;
    }
    
    public List<Quittung> getQuittungen() {
        List<Quittung> quittungList = charityService.getQuittung();
        return quittungList;
    }
    
    
}