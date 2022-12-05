
package spendensammler.jpa.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.validation.ConstraintViolationException;
import java.util.List;
import spendensammler.jpa.entities.Charity;
import spendensammler.jpa.entities.Charity_Organisation;
import spendensammler.jpa.entities.Benutzer;
import spendensammler.jpa.entities.Charitydao;
import spendensammler.jpa.entities.Charitydao_locationdao;
import spendensammler.jpa.entities.Charityorganizationdao;
import spendensammler.jpa.entities.Locationdao;



/**
 * Datenzugriffsobjekt zur Implementierung der JPA-spezifischen Logik.
 * 
 * Die Entitaetsklassen sollen im package edu.whs.idb.praktikum.entities
 * definiert werden.
 * 
 */
public class Datenzugriffsobjekt {
   
    /**
     * Hier koennen Sie den Entity-Manager hinterlegen, den das
     * Datenzugriffsobjekt verwalten soll.
     */
    private static EntityManagerFactory EMFACTORY;
    private EntityManager em;
    EntityTransaction et = null;

    /**
     * Starten der uebrgebenen Persistence-Unit
     *
     * @param persistenceUnit Die Persistence-Unit, die gestartet werden soll
     */
    public Datenzugriffsobjekt(String persistenceUnit) {
        this.EMFACTORY = Persistence.createEntityManagerFactory(persistenceUnit);
        this.em = EMFACTORY.createEntityManager();
       // this.em.getTransaction().begin();
    }
    
    /**
     * Diese Funktion fügt der Datenbank ein Charity Objekt und eine dazu gehörige
     * Charity_Organisation hinzu.
     * @param charity Organisation die der datenbank hinzugefügt werden soll
     */
    public void createNewCharity(Charity charity) {
        try {    
            this.em.getTransaction().begin();            
            em.persist(charity);
            em.getTransaction().commit();
            em.close();
        } catch(ConstraintViolationException cve) {
            System.out.println("Fehler Datenzugriff Charity");
        }
    }
    
    /**
     * Diese Funktion fügt der Datenbank ein Charity Objekt und eine dazu gehörige
     * Charity_Organisation hinzu.
     * @param charity Organisation die der datenbank hinzugefügt werden soll
     */
    public void createNewUser(Benutzer user) {
        try {    
            this.em.getTransaction().begin();            
            em.persist(user);
            em.getTransaction().commit();
            em.close();
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
            //Charity charity = em.find(Charity.class, 1L);
            this.em.getTransaction().begin();
            if (!em.contains(charity)) {
                charity = em.merge(charity);
            }
            em.remove(charity);
            em.getTransaction().commit();
            em.close();
        } catch(Exception e) {
            System.out.println(e);
        }        
    }
    
    public void updateCharity(Long id, String name, String bild, int yearg, String website, String worldview, String fieldsOfAction, 
            String bericht, Long member, Long employee, Long volunteers, String management, int orgayear) {
        try { 
            Charity charity = em.find(Charity.class, id);
            this.em.getTransaction().begin(); 
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
            
            em.getTransaction().commit();
            em.close();
        } catch(ConstraintViolationException cve) {
            System.out.println("Fehler Datenzugriff Charity");
        }
    }
    public void updateCharity(Long id, String pid) {
        try { 
            Charity charity = em.find(Charity.class, id);
            this.em.getTransaction().begin(); 
            // Charity:
           charity.setPaypalid(pid);
            
            em.getTransaction().commit();
            em.close();
        } catch(ConstraintViolationException cve) {
            System.out.println("Fehler Datenzugriff Charity");
        }
    }
    
    public List<Charity> findeCharity(String name){
            Query query = em.createQuery("SELECT c FROM Charity c WHERE c.name LIKE '%" + name + "%'");
            List<Charity> charitys = query.getResultList();
            return charitys;
    }      
    
     public List<Charity> findAll(){
            Query query = em.createQuery("SELECT a FROM Charity AS a");
            List<Charity> charitys = query.getResultList();
            return charitys;
    }
     
      public List<Charity> findeCharityInformationenName(String name) {
        Query query = em.createQuery("SELECT a FROM Charity AS a WHERE a.name =:name").setParameter("name", name);
        List<Charity> charitys = query.getResultList();

        return charitys;
    }
    /*
    * gibt eine Liste aller Charitys mit angegebene ID zurück
    */
    public List<Charity> findeCharityInformationenId(Long id) {
        Query query = em.createQuery("SELECT a FROM Charity AS a WHERE a.idCharity =:id").setParameter("idCharity", id);
        List<Charity> charitys = query.getResultList();

        return charitys;
    }
     
    /*
    * gibt eine Liste aller Charitys mit angegebenem Gründungsjahr zurück
    */
    public List<Charity> findeCharityInformationenGrüdungsjahr(int Grüdungsjahr) {
        Query query = em.createQuery("SELECT a FROM Charity AS a WHERE a.foundingYear =:Grüdungsjahr").setParameter("Grüdungsjahr", Grüdungsjahr);
        List<Charity> charitys = query.getResultList();
        return charitys;
    }
    /*
    * gibt eine Liste aller Charitys mit angegebener ID und angegebenem Namen zurück
    */
    public List<Charity> findeCharityInformationenIDAndName(Long id, String name) {
        Query query = em.createQuery("SELECT a FROM Charity AS a WHERE a.idCharity =:id AND a.name =:name").setParameter("idCharity", id).setParameter("name", name);
        List<Charity> charitys = query.getResultList();

        return charitys;
    }
    /*
    * gibt eine Liste aller Charitys angegebenem Namen und angegebenem Gründungsjahr zurück
    */
    public List<Charity> findeCharityInformationenNameAndGrüdungsjahr(String name, int jahr) {
        Query query = em.createQuery("SELECT a FROM Charity AS a WHERE a.name =:name AND a.foundingYear=:jahr").setParameter("name", name).setParameter("jahr", jahr);
        List<Charity> charitys = query.getResultList();

        return charitys;
    }
    /*
    * gibt eine Liste aller Charitys mit angegebener ID und angegebenem Gründungsjahr zurück
    */
    public List<Charity> findeCharityInformationenIDAndGründungsjahr(Long id, int jahr) {
        Query query = em.createQuery("SELECT a FROM Charity AS a WHERE a.idCharity =:id AND a.foundingYear=:jahr").setParameter("idCharity", id).setParameter("jahr", jahr);
        List<Charity> charitys = query.getResultList();

        return charitys;
    }
    /*
    * gibt eine Liste aller Charitys mit angegebener ID und angegebenem Namen und 
    * angegebenem Gründungsjahr zurück
    */
    public List<Charity> findeCharityInformationenIDAndNameAndGründungsjahr(long id, String name, int jahr) {
        Query query = em.createQuery("SELECT a FROM Charity AS a WHERE a.idCharity =:id AND a.name=:name AND a.foundingYear=:jahr").setParameter("idCharity", id).setParameter("name", name).setParameter("jahr", jahr);
        List<Charity> charitys = query.getResultList();

        return charitys;
    }
    public List<Charitydao> findeCharityInformationenIddao(Long id) {
        Query query = em.createQuery("SELECT a FROM Charitydao AS a WHERE a.id =:id").setParameter("id", id);
        List<Charitydao> charitys = query.getResultList();

        return charitys;
    }

    public List<Charitydao> findeCharityInformationenAlldao() {
        Query query = em.createQuery("SELECT a FROM Charitydao AS a");
        List<Charitydao> charitys = query.getResultList();

        return charitys;
    }

    public Charityorganizationdao findeOrgaInformationeniddao(Long id) {

        Query query = em.createQuery("SELECT a FROM Charityorganizationdao AS a WHERE a.id =:id").setParameter("id", id);

        Charityorganizationdao orga = (Charityorganizationdao) query.getResultList().get(0);

        return orga;
    }
public Locationdao findeLocationInformationeniddao(Long id) {

        Query query = em.createQuery("SELECT a FROM Locationdao AS a WHERE a.id =:id").setParameter("id", id);

        Locationdao loca = (Locationdao) query.getResultList().get(0);

        return loca;
    }
public List<Charitydao_locationdao> findeLocationInformationencharity_location() {

        Query query = em.createQuery("SELECT a FROM Charitydao_locationdao AS a");

        List<Charitydao_locationdao> connection = query.getResultList();
        return connection;
    }
 public void removeCharityWithId(Long id) {
        try {
            Charity charity = em.find(Charity.class, id);
            this.em.getTransaction().begin();

            if (!em.contains(charity)) {
                charity = em.merge(charity);
            }

            em.remove(charity);
            em.getTransaction().commit();
            //em.close();
        } catch(Exception e) {
            System.out.println(e);
        }        
    }

    /**
     * Entity-Manager schließen.
     */
    public void close() {
        // ToDo: Schließen des Entity-Managers
        EMFACTORY.close();
    }
}