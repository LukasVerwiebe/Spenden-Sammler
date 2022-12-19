
package com.mycompany.controller;

import com.mycompany.model.SessionData;
import com.mycompany.services.CharityService;
import com.mycompany.services.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.sql.Date;
import java.util.List;
import spendensammler.jpa.entities.Bankkonto;
import spendensammler.jpa.entities.Benutzer;
import spendensammler.jpa.entities.Charity;
import spendensammler.jpa.entities.Einkommen;
import spendensammler.jpa.entities.Quittung;

/**
 *
 * @author lu80238
 */
@Named(value="userController")
@RequestScoped
public class UserController {
    
    @Inject
    private UserService userService;
    
    @Inject
    private SessionData sessionData;
    
    private List<Benutzer> benutzerList;
    
    @PostConstruct
    public void charityController() {
        benutzerList = userService.findAll();
    }
    
    public void newUser(String vorname, String nachname, String email, String passwort, String stadt, String strasse, String rolle) {
        try {
            // Erst Rolle und Status setzten:
            //String rolle = "Benutzer";
            boolean status = true;
            
            // User anlegen:
            Benutzer user = new Benutzer(vorname, nachname, email, passwort, rolle, status);
            
            userService.createNewUser(user, stadt, strasse);
            // User in der Session Speichern:
            //sessionData.setBenutzer(user);  
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public void newQuittung(double summe, String text, Charity charity, Benutzer benutzer) {
        try { 
            Quittung quittung = new Quittung(summe, text);
            // Verbindungen:
            quittung.setCharity(charity);
            quittung.setBenutzer(benutzer);
            userService.createNewQuittung(quittung);            
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public void newQuittungDate(double summe, String text, Charity charity, Benutzer benutzer, Date heutigesDatum) {
        try { 
            Quittung quittung = new Quittung(summe, text);
            // Verbindungen:
            quittung.setCharity(charity);
            quittung.setBenutzer(benutzer);
            userService.createNewQuittungDate(quittung, heutigesDatum);            
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public void newQuittungAdmin(double summe, String text) {
        try {
            userService.createNewQuittungAdmin(summe, text);            
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public void newBAnkkonto(String ibanBankkonto, String inhaberBankkonto, String bicBankkonto, boolean deletedBankkonto, boolean aktivKonto) {
        try {            
            userService.createNewnewBAnkkonto(ibanBankkonto, inhaberBankkonto, bicBankkonto, deletedBankkonto, aktivKonto);  
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public void newBAnkkontoOrga(String ibanBankkonto, String inhaberBankkonto, String bicBankkonto, boolean deletedBankkonto, boolean aktivKonto, Charity charity, String paypalid) {
        try {            
            userService.createNewnewBAnkkontoOrga(ibanBankkonto, inhaberBankkonto, bicBankkonto, deletedBankkonto, aktivKonto, charity, paypalid);  
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public List<Benutzer> findeBenutzer(String email) {
        List<Benutzer> benutzer = userService.findeBenutzer(email);
        return benutzer;
    }
    
    
    public void updateUser(Long id, String vorname, String nachname, String email, String passwort, String rolle, boolean status, String stadt, String strasse, int postleitzahl) {
        try {
            userService.updateUser(id, vorname, nachname, email, passwort, rolle, status, stadt, strasse, postleitzahl);
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public void updateQuittung(long id, double summe, String text) {
        try {
            userService.updateQuittung(id, summe, text);
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public void updateBankkonto(long id, String iban, String inhaber, String bic, boolean deleted, boolean status, String paypalid) {
        try {
            userService.updateBankkonto(id, iban, inhaber, bic, deleted, status, paypalid);
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
    public Benutzer loginUser(String emailBenutzer) {        
        List<Benutzer> benutzerList = userService.findeBenutzer(emailBenutzer);       
        return benutzerList.get(0);
    }
    
    /**
     * Auflistung aller Benutzer Objekte in der Datenbank.
     * @return 
     */
    public List<Benutzer> findAll() {
        return benutzerList;
    }
    
    public List<Benutzer> findAllUser() {
        List<Benutzer> benutzerListUser = userService.findAllUser();
        return benutzerListUser;
    }
    
    public List<Benutzer> findAllUserOrga() {
        List<Benutzer> benutzerListOrga = userService.findAllUserOrga();
        return benutzerListOrga;
    }
    
    public List<Quittung> findAllQuittungen() {
        List<Quittung> quittungen = userService.findAllQuittungen();
        return quittungen;
    }
    
    public List<Bankkonto> findAllBankkonten() {
        List<Bankkonto> bankkonto = userService.findAllBankkonten();
        return bankkonto;
    }
    
    public List<Bankkonto> findAllBankkontenOrga(Long id) {
        List<Bankkonto> bankkonto = userService.findAllBankkontenOrga(id);
        return bankkonto;
    }
    
    public List<Einkommen> findAllEinkommen() {
        List<Einkommen> einkommen = userService.findAllEinkommen();
        return einkommen;
    }
    
    public List<Quittung> findAllQuittungenBenutzer(Long id) {
        List<Quittung> quittungen = userService.findAllQuittungenBenutzer(id);
        return quittungen;
    }
    
    public List<Quittung> findAllQuittungenOrga(Long id) {
        List<Quittung> quittungen = userService.findAllQuittungenOrga(id);
        return quittungen;
    }
    
    public List<Einkommen> findAllEinkommenOrga(Long id) {
        List<Einkommen> einkommen = userService.findAllEinkommenOrga(id);
        return einkommen;
    }
    
    public List<Quittung> orgaDiagrammQuittungen(int jahr, Long id) {
        List<Quittung> quittung = userService.orgaDiagrammQuittungen(jahr, id);
        return quittung;
    }
    
    public List<Quittung> getUserQuittung(int jahr, Long id) {
        List<Quittung> quittungList = userService.getUserQuittung(jahr, id);
        return quittungList;
    }
    
    public List<Quittung> getUQuittung(Long id) {
        List<Quittung> quittungList = userService.getUQuittung(id);
        return quittungList;
    }
    
    public List<Quittung> diagrammQuittungen(int jahr) {
        List<Quittung> quittung = userService.diagrammQuittungen(jahr);
        return quittung;
    }
    
    public void elementeLoeschenBenutzer(Benutzer benutzer) {
        userService.removeUser(benutzer);
    }
    
    public void elementeLoeschenQuittung(Quittung quittung) {
        userService.removeQuittung(quittung);
    }
    
    public void elementeLoeschenBankkonto(Bankkonto bankkonto) {
        userService.removeBankkonto(bankkonto);
    }
    
    public void benutzerStatusSetzten(Benutzer benutzer) {
        userService.benutzerStatusSetzten(benutzer);
    }
    
    public void benutzerBankkontoSetzten(Bankkonto bankkonto, Charity charity, List<Bankkonto> bankkonten) {
        userService.benutzerBankkontoSetzten(bankkonto, charity, bankkonten);
    }
}