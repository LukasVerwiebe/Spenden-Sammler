
package com.mycompany.services;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.validation.ConstraintViolationException;
import java.sql.Date;
import java.util.List;
import spendensammler.jpa.entities.Charity;
import spendensammler.jpa.entities.Einkommen;
import spendensammler.jpa.entities.Kategorie;
import spendensammler.jpa.entities.PlaceOfAction;
import spendensammler.jpa.entities.Quittung;

// Die Instanz dieser CDI-Bean existiert, solange die gesamte Web-Applikation
// deployt ist.
@ApplicationScoped
// Postgres Datenbank:
@DataSourceDefinition(name = "java:app/jdbc/idb_ds", 
        minPoolSize = 0,
        initialPoolSize = 0,
        className = "org.postgresql.Driver.ClientDataSource",
        transactional = false,
        portNumber = 1527,
        serverName = "172.16.178.31",
        user = "spenden",
        password = "admin",
        databaseName = "Spendensammler"
)

public class CharityService {
    
    private EntityManagerFactory emf;
    private EntityManager em;
    private String persistenceUnit = "Spendensammler-JPA-PhasePU";
    
    /**
     * Creates a new instance of ShopDAOBean
     */
    public CharityService() {
        initBean();
    }   
    
        /**
     * Heutiges Datum Berechnen:
     * @return 
     */
    public Date heutigesDatum() {
//        SimpleDateFormat dtf = new SimpleDateFormat("dd.MM.yyyy");
//        Calendar calendar = Calendar.getInstance();
//        Date dateObj = calendar.getTime();
//        String formattedDate = dtf.format(dateObj);
        
        long miliseconds = System.currentTimeMillis();
        Date date = new Date(miliseconds);
        // Datum Format: YYYY-MM-DD
        return date;
    }
    
    /**
     * Diese Funktion fügt der Datenbank ein Charity Objekt und eine dazu gehörige
     * Charity_Organisation hinzu.
     * @param charity Organisation die der datenbank hinzugefügt werden soll
     */
    public void createNewCharity(Charity charity) {
        try {    
            charity.setErstelldatum(heutigesDatum());
            em.getTransaction().begin();            
            em.persist(charity);
            em.getTransaction().commit();
        } catch(ConstraintViolationException cve) {
            System.out.println("Fehler Datenzugriff Charity");
        }
    }
    
    /**
     * Diese Funktion entfernt ein charity Objekt, dabei wird die cascade 
     * Anweisung beachtet und die zugehörige Charity_Organisation mit entfernt.
     * @param charity Organisation die entfernt werden soll
     */
    public void removeCharity(Charity charity) {
        try {
            em.getTransaction().begin();
            if (!em.contains(charity)) {
                charity = em.merge(charity);
            }
            em.remove(charity);
            em.getTransaction().commit();
        } catch(Exception e) {
            System.out.println(e);
        }        
    }
    
    public void removeKategorie(Kategorie kategorie) {
        try {
            em.getTransaction().begin();
            if (!em.contains(kategorie)) {
                kategorie = em.merge(kategorie);
            }
            em.remove(kategorie);
            em.getTransaction().commit();
        } catch(Exception e) {
            System.out.println(e);
        }        
    }
    
    public void removePlaceOfaction(PlaceOfAction placeofaction) {
        try {
            em.getTransaction().begin();
            if (!em.contains(placeofaction)) {
                placeofaction = em.merge(placeofaction);
            }
            em.remove(placeofaction);
            em.getTransaction().commit();
        } catch(Exception e) {
            System.out.println(e);
        }  
    }
    
    
    public void updateCharity(Long id, String name, String bild, int yearg, String website, String worldview, String fieldsOfAction, 
            String bericht, Long member, Long employee, Long volunteers, String management, int orgayear, String strasse, String stadt,
            String postleitzahl, String telefon, String telefax, String email) {
        try { 
            Charity charity = em.find(Charity.class, id);
            em.getTransaction().begin(); 
            // Charity:
            charity.setName(name);
            charity.setLogoPath(bild);
            charity.setFoundingYear(yearg);
            charity.setHomepage(website);
            charity.setWorldview(worldview);
            charity.setFieldsOfAction(fieldsOfAction);
            charity.setAdvertingFinancialInspektion(bericht);            
            // Charity Organisation:
            charity.getCh_orga().setMembercount(member);
            charity.getCh_orga().setEmployeecount(employee);
            charity.getCh_orga().setVolounteersCount(volunteers);
            charity.getCh_orga().setManagmentbody(management);
            charity.getCh_orga().setOrgaYear(orgayear);
            // Kontakte und Standort:
            charity.setStrasse(strasse);
            charity.setStadt(stadt);
            charity.setPostleitzahl(postleitzahl);
            charity.setTelefon(telefon);
            charity.setTelefax(telefax);
            charity.setEmail(email);
            
            em.getTransaction().commit();            
        } catch(ConstraintViolationException cve) {
            System.out.println("Fehler Datenzugriff Charity");
        }
    }
    
    public void updateKategorie(Long idKategorie, String nameKategorie) {
        try { 
            Kategorie kategorie = em.find(Kategorie.class, idKategorie);
            em.getTransaction().begin(); 
            // Kategorie:
            kategorie.setNameKategorie(nameKategorie);
            
            em.getTransaction().commit();            
        } catch(ConstraintViolationException cve) {
            System.out.println("Fehler Datenzugriff Kategorie");
        }
    }
    
    public void updatePlaceOfAction(Long idPlace, String namePlace) {
        try { 
            PlaceOfAction placeofaction = em.find(PlaceOfAction.class, idPlace);
            em.getTransaction().begin(); 
            // Place of Action:
            placeofaction.setLandPlaceOfAction(namePlace);
            
            em.getTransaction().commit();            
        } catch(ConstraintViolationException cve) {
            System.out.println("Fehler Datenzugriff PlaceOfAction");
        }
    }
    
    public void updateEinkommen(Long idEinkommen, double betrag) {
        try { 
            Einkommen einkommen = em.find(Einkommen.class, idEinkommen);
            em.getTransaction().begin(); 
            // Betag erhöhen:
            double einkommensWert = einkommen.getGeldEinkommen();
            double summe = einkommensWert + betrag;
            einkommen.setGeldEinkommen(summe);
            
            em.getTransaction().commit();            
        } catch(ConstraintViolationException cve) {
            System.out.println("Fehler Datenzugriff updateEinkommen");
        }
    }
    
    public List<Charity> findeCharity(String name){
        Query query = em.createQuery("SELECT c FROM Charity c WHERE c.name LIKE '%" + name + "%'");
        List<Charity> charitys = query.getResultList();
        return charitys;
    }  

    public List<Charity> gefilterteCharities(String worldview, String land, String category) {
        List<Charity> charitys;
        
        if(worldview == null){
            worldview = "";
        }
        if(land == null){
            land = "";
        }
        if(category == null){
            category = "";
        }
        Query queryNurWV = em.createQuery("SELECT c FROM Charity c WHERE c.worldview LIKE '%" + worldview + "%'");
        Query queryAll = em.createQuery("SELECT c FROM Charity c JOIN Kategorie k ON (c.idCharity = k.charity) JOIN PlaceOfAction p ON (c.idCharity = p.charity) WHERE c.worldview LIKE '%" + worldview + "%'" + " AND UPPER(k.nameKategorie) LIKE UPPER('%" + category + "%')" + " AND UPPER(p.landPlaceOfAction) LIKE UPPER('%" + land + "%') GROUP BY c.idCharity");
        Query queryWvC = em.createQuery("SELECT c FROM Charity c JOIN Kategorie k ON (c.idCharity = k.charity) WHERE c.worldview LIKE '%" + worldview + "%'" + " AND UPPER(k.nameKategorie) LIKE UPPER('%" + category + "%')" + " GROUP BY c.idCharity");
        Query queryWvL = em.createQuery("SELECT c FROM Charity c JOIN PlaceOfAction p ON (c.idCharity = p.charity) WHERE c.worldview LIKE '%" + worldview + "%'" + " AND UPPER(p.landPlaceOfAction) LIKE UPPER('%" + land + "%') GROUP BY c.idCharity");
        
        
        if(land.isEmpty() && category.isEmpty()){
            charitys = queryNurWV.getResultList();
            System.out.println("queryNurMV");
        }
        else if(land.isEmpty()){
            charitys = queryWvC.getResultList();
        }
        else if(category.isEmpty()){
            charitys = queryWvL.getResultList();
        }
        else{
            charitys = queryAll.getResultList();
            System.out.println("queryAll");
        }
            System.out.println("Weltansicht: " + worldview + "Kategorie: " + category + "Land: " + land);
        return charitys;
    }    
    
    public void createNewKategorie(Kategorie kategorie) {
        try {    
            em.getTransaction().begin();            
            em.persist(kategorie);
            em.getTransaction().commit();
        } catch(ConstraintViolationException cve) {
            System.out.println("Fehler Datenzugriff Kategorie");
        }
    }
    
    public void createNewPlaceOfAction(PlaceOfAction placeofAction) {
        try {    
            em.getTransaction().begin();            
            em.persist(placeofAction);
            em.getTransaction().commit();
        } catch(ConstraintViolationException cve) {
            System.out.println("Fehler Datenzugriff PlaceOfAction");
        }
    }
    
    public List<Charity> findAll(){
            Query query = em.createQuery("SELECT a FROM Charity AS a");
            List<Charity> charitys = query.getResultList();
            return charitys;
    } 

    public List<String> findAllPlaces(){
            Query query = em.createQuery("SELECT p.landPlaceOfAction FROM PlaceOfAction AS p Group BY p.landPlaceOfAction");
            List<String> places = query.getResultList();
            return places;
    }
    
    public List<String> findAllCategories(){
            Query query = em.createQuery("SELECT k.nameKategorie FROM Kategorie AS k GROUP BY k.nameKategorie");
            List<String> categories = query.getResultList();
            return categories;
    }    
    
    public List<Einkommen> jahrEinkommen(int jahr, Long id) {
        Query query = em.createQuery("SELECT e FROM Einkommen AS e WHERE e.charity.idCharity = " + id  + " AND e.jahrEinkommen = " + jahr);
        List<Einkommen> einkommen = query.getResultList();
        return einkommen;
    }
    
    public List<Einkommen> orgaEinkommen(Long id) {
        Query query = em.createQuery("SELECT e FROM Einkommen AS e WHERE e.charity.idCharity = " + id);
        List<Einkommen> einkommen = query.getResultList();
        return einkommen;
    }
    
    public List<Quittung> getOrgaQuittung(Long id) {
        Query query = em.createQuery("SELECT q FROM Quittung AS q WHERE q.charity.idCharity = " + id);
        List<Quittung> quittung = query.getResultList();
        return quittung;
    }
    
    public List<Quittung> getQuittung() {
        Query query = em.createQuery("SELECT q FROM Quittung AS q");
        List<Quittung> quittung = query.getResultList();
        return quittung;
    }
    
    public List<String> findCategories(Long charityID){
            Query query = em.createQuery("SELECT k.nameKategorie FROM Kategorie AS k WHERE k.charity = " + charityID);
            List<String> categories = query.getResultList();
            return categories;
    }
    
    public List<String> findPlaces(Long charityID){
            Query query = em.createQuery("SELECT p.landPlaceOfAction FROM PlaceOfAction AS p WHERE p.charity = " + charityID);
            List<String> categories = query.getResultList();
            return categories;
    }    
    
    public void createEinkommen(Einkommen einkommen) {
        try {    
            em.getTransaction().begin();            
            em.persist(einkommen);
            em.getTransaction().commit();
        } catch(ConstraintViolationException cve) {
            System.out.println("Fehler Datenzugriff Einkommen");
        }
    }
    
    @PostConstruct
    private void initBean() {
        this.emf = Persistence.createEntityManagerFactory(persistenceUnit);
        this.em = emf.createEntityManager();        
    }
    
    @PreDestroy
    private void tearDownBean() {
        emf.close();
        em.close();
    }
    
}
