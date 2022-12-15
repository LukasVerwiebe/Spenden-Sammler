
package com.mycompany.services;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.validation.ConstraintViolationException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.List;
import org.primefaces.PrimeFaces;
import spendensammler.jpa.entities.Bankkonto;
import spendensammler.jpa.entities.Benutzer;
import spendensammler.jpa.entities.Charity;
import spendensammler.jpa.entities.Einkommen;
import spendensammler.jpa.entities.Kategorie;
import spendensammler.jpa.entities.Quittung;

/**
 *
 * @author lu80238
 */
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

public class UserService {
    
    private EntityManagerFactory emf;
    private EntityManager em;
    private String persistenceUnit = "Spendensammler-JPA-PhasePU";
    
     /**
     * Creates a new instance of ShopDAOBean
     */
    public UserService() {
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
     * Diese Funktion fügt der Datenbank ein neues Benutzer Objekt hinzu.
     * @param user
     */
    public void createNewUser(Benutzer user, String stadt, String strasse) {
        try {    
            em.getTransaction().begin();
            // Setzten von nicht Pflicht Werten:
            user.setStadt(stadt);
            user.setStrasse(strasse);
            user.setErstelldatum(heutigesDatum());
            
            em.persist(user);
            em.getTransaction().commit();
        } catch(ConstraintViolationException cve) {
            System.out.println("Fehler Datenzugriff User");
        }
    }
    
    public void createNewQuittung(Quittung quittung) {
        try {
            em.getTransaction().begin();
            quittung.setErstelldatum(heutigesDatum());   
            em.persist(quittung);
            em.getTransaction().commit();
        } catch(ConstraintViolationException cve) {
            System.out.println("Fehler Datenzugriff Quittung");
        }
    }
    
    public void createNewQuittungAdmin(double summe, String text) {
        try {
            Quittung quittung = new Quittung(summe, text);
            em.getTransaction().begin();
            quittung.setErstelldatum(heutigesDatum());                        
            em.persist(quittung);
            em.getTransaction().commit();
        } catch(ConstraintViolationException cve) {
            System.out.println("Fehler Datenzugriff Quittung");
        }
    }
    
    public void createNewnewBAnkkonto(String ibanBankkonto, String inhaberBankkonto, String bicBankkonto, boolean deletedBankkonto, boolean aktivKonto) {
        try { 
            Bankkonto bankkonto = new Bankkonto(ibanBankkonto, inhaberBankkonto, bicBankkonto, deletedBankkonto);
            bankkonto.setAktivKonto(aktivKonto);
            
            em.getTransaction().begin();            
            em.persist(bankkonto);
            em.getTransaction().commit();
        } catch(ConstraintViolationException cve) {
            System.out.println("Fehler Datenzugriff Bankkonto");
        }
    }
    
    public void createNewnewBAnkkontoOrga(String ibanBankkonto, String inhaberBankkonto, String bicBankkonto, boolean deletedBankkonto, boolean aktivKonto, Charity charity, String paypalid) {
        try { 
            Bankkonto bankkonto = new Bankkonto(ibanBankkonto, inhaberBankkonto, bicBankkonto, deletedBankkonto);
            bankkonto.setAktivKonto(aktivKonto);
            bankkonto.setPaypalid(paypalid);
            
            // Verbindungen:
            bankkonto.setCharity(charity);
            charity.getBankkonto().add(bankkonto);
            
            em.getTransaction().begin();            
            em.persist(bankkonto);
            em.getTransaction().commit();
        } catch(ConstraintViolationException cve) {
            System.out.println("Fehler Datenzugriff Bankkonto");
        }
    }
    
    /**
     * Diese Funktion ist für die Bearbeitung eines Benutzers gedacht.
     * @param user
     */
    public void updateUser(Long id, String vorname, String nachname, String email, String passwort, String rolle, boolean status, String stadt, String strasse, int postleitzahl) {
        try { 
            Benutzer benutzer = em.find(Benutzer.class, id);
            em.getTransaction().begin(); 
            // Benutzer:
            benutzer.setVorname(vorname);
            benutzer.setNachname(nachname);
            benutzer.setEmailBenutzer(email);
            benutzer.setPasswort(passwort);
            benutzer.setStadt(stadt);
            benutzer.setStrasse(strasse);
            benutzer.setStatusBenutzer(status);
            benutzer.setPostleitzahl(postleitzahl);
            
            em.getTransaction().commit();            
        } catch(ConstraintViolationException cve) {
            System.out.println("Fehler Datenzugriff Benutzer");
        }
    }
    
    public void updateQuittung(long id, double summe, String text) {
        try { 
            Quittung quittung = em.find(Quittung.class, id);
            em.getTransaction().begin(); 
            // Benutzer:
            quittung.setTextQuittung(text);
            quittung.setSummeQuittung(summe);
            
            em.getTransaction().commit();            
        } catch(ConstraintViolationException cve) {
            System.out.println("Fehler Datenzugriff Quittung");
        }
    }
    
    public void updateBankkonto(long id, String iban, String inhaber, String bic, boolean deleted, boolean status, String paypalid) {
        try { 
            Bankkonto bankkonto = em.find(Bankkonto.class, id);
            em.getTransaction().begin(); 
            // Benutzer:
            bankkonto.setIbanBankkonto(iban);
            bankkonto.setInhaberBankkonto(inhaber);
            bankkonto.setBicBankkonto(bic);
            bankkonto.setDeletedBankkonto(deleted);
            bankkonto.setAktivKonto(status);
            bankkonto.setPaypalid(paypalid);
            
            em.getTransaction().commit();            
        } catch(ConstraintViolationException cve) {
            System.out.println("Fehler Datenzugriff Bankkonto");
        }
    }
    
    public List<Benutzer> findeBenutzer(String emailBenutzer){
            Query query = em.createQuery("SELECT b FROM Benutzer b WHERE b.emailBenutzer LIKE " + "'" + emailBenutzer + "'");
            List<Benutzer> benutzer = query.getResultList();
            return benutzer;
    }
    
    public List<Benutzer> findAll(){
            Query query = em.createQuery("SELECT b FROM Benutzer AS b");
            List<Benutzer> benutzer = query.getResultList();
            return benutzer;
    }
    
    public List<Benutzer> findAllUser(){
            Query query = em.createQuery("SELECT b FROM Benutzer AS b WHERE b.rolleBenutzer LIKE 'Benutzer'");
            List<Benutzer> benutzer = query.getResultList();
            return benutzer;
    }
     
    public List<Benutzer> findAllUserOrga(){
            Query query = em.createQuery("SELECT b FROM Benutzer AS b WHERE b.rolleBenutzer LIKE 'Organisation'");
            List<Benutzer> benutzer = query.getResultList();
            return benutzer;
    }
    
    public List<Quittung> findAllQuittungen(){
            Query query = em.createQuery("SELECT q FROM Quittung AS q");
            List<Quittung> quittungen = query.getResultList();
            return quittungen;
    }
    
    public List<Bankkonto> findAllBankkonten(){
            Query query = em.createQuery("SELECT b FROM Bankkonto AS b");
            List<Bankkonto> bankkonto = query.getResultList();
            return bankkonto;
    }
    
    public List<Bankkonto> findAllBankkontenOrga(Long id){
            Query query = em.createQuery("SELECT b FROM Bankkonto AS b WHERE b.charity.idCharity = " + id + "");
            List<Bankkonto> bankkonto = query.getResultList();
            return bankkonto;
    }
    
    public List<Einkommen> findAllEinkommen(){
            Query query = em.createQuery("SELECT e FROM Einkommen AS e");
            List<Einkommen> einkommen = query.getResultList();
            return einkommen;
    }
    
    public List<Einkommen> findAllEinkommenOrga(Long id){
            Query query = em.createQuery("SELECT e FROM Einkommen AS e WHERE e.charity.idCharity = " + id + "");
            List<Einkommen> einkommen = query.getResultList();
            return einkommen;
    }
    
    public List<Quittung> findAllQuittungenBenutzer(Long id){
            Query query = em.createQuery("SELECT q FROM Quittung AS q WHERE q.benutzer.idBenutzer = " + id + "");
            List<Quittung> quittungen = query.getResultList();
            return quittungen;
    }
    
    public List<Quittung> findAllQuittungenOrga(Long id){
            Query query = em.createQuery("SELECT q FROM Quittung AS q WHERE q.charity.idCharity = " + id + "");
            List<Quittung> quittungen = query.getResultList();
            return quittungen;
    }    
         
    public void removeUser(Benutzer benutzer) {
        try {
            em.getTransaction().begin();
            if (!em.contains(benutzer)) {
                benutzer = em.merge(benutzer);
            }
            em.remove(benutzer);
            em.getTransaction().commit();
        } catch(Exception e) {
            System.out.println(e);
        }        
    }
     
    public void removeQuittung(Quittung quittung) {
        try {
            em.getTransaction().begin();
            if (!em.contains(quittung)) {
                quittung = em.merge(quittung);
            }
            em.remove(quittung);
            em.getTransaction().commit();
        } catch(Exception e) {
            System.out.println(e);
        }        
    }
    
    public void removeBankkonto(Bankkonto bankkonto) {
        try {
            em.getTransaction().begin();
            if (!em.contains(bankkonto)) {
                bankkonto = em.merge(bankkonto);
            }
            em.remove(bankkonto);
            em.getTransaction().commit();
        } catch(Exception e) {
            System.out.println(e);
        }        
    }
    
    public void benutzerStatusSetzten(Benutzer benutzer) {
        try {
            em.getTransaction().begin();
            
            if(benutzer.getStatusBenutzer()) {                
                benutzer.setStatusBenutzer(false);
            } else {
                benutzer.setStatusBenutzer(true);
            }
            
            em.getTransaction().commit();
        } catch(Exception e) {
            System.out.println(e);
        }        
    }   
    
    public void benutzerBankkontoSetzten(Bankkonto bankkonto, Charity charity, List<Bankkonto> bankkonten) {
        try {
            em.getTransaction().begin();  
            
            for(int i = 0; i < bankkonten.size(); i++) {
                if(bankkonten.get(i).isAktivKonto()) {
                    bankkonten.get(i).setAktivKonto(false);
                    charity.setPaypalid(null);
                }
            }
            
            bankkonto.setAktivKonto(true);
            charity.setPaypalid(bankkonto.getPaypalid());
            
            em.getTransaction().commit();
        } catch(Exception e) {
            System.out.println(e);
        }        
    } 
    
    public List<Quittung> orgaDiagrammQuittungen(int jahr, Long id) {
        Query query = em.createQuery("SELECT q FROM Quittung AS q WHERE q.charity.idCharity = " + id  + " AND YEAR(q.erstelldatum) = " + jahr);
        List<Quittung> quittungen = query.getResultList();
        return quittungen;
    }
    
    public List<Quittung> diagrammQuittungen(int jahr) {
        Query query = em.createQuery("SELECT q FROM Quittung AS q WHERE YEAR(q.erstelldatum) = " + jahr);
        List<Quittung> quittungen = query.getResultList();
        return quittungen;
    }
    
    public List<Quittung> getUserQuittung(int jahr, Long id) {
        Query query = em.createQuery("SELECT q FROM Quittung AS q WHERE q.benutzer.idBenutzer = " + id  + " AND YEAR(q.erstelldatum) = " + jahr);        
        List<Quittung> quittung = query.getResultList();
        return quittung;
    }
    
    public List<Quittung> getUQuittung(Long id) {
        Query query = em.createQuery("SELECT q FROM Quittung AS q WHERE q.benutzer.idBenutzer = " + id);        
        List<Quittung> quittung = query.getResultList();
        return quittung;
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
