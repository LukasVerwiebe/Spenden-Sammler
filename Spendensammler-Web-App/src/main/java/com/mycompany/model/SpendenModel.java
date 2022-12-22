
package com.mycompany.model;

import com.mycompany.controller.CharityController;
import com.mycompany.controller.UserController;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.primefaces.PrimeFaces;
import spendensammler.jpa.entities.Benutzer;
import spendensammler.jpa.entities.Charity;
import spendensammler.jpa.entities.Einkommen;

/**
 *
 * @author Lukas
 */
@Named(value="spendenModel") // Nur für den Fall das eine weitere Bean mit selben Namen verwendet werden muss
@RequestScoped
public class SpendenModel implements Serializable {
    
    @Inject
    private SessionData sessiondata;
    
    @Inject
    private SessionData sessionData;
    
    @Inject
    private UserController userController;
    
    @Inject
    private CharityController charityController;
    
    @Inject
    private EinkommenModel einkommenModel;
    
    @Inject
    private OrgaEinkommenModel orgaEinkommenModel;
    
    private String spendenaktion;
    private String organisation;
    private double betrag;
    private String bezahlt;
    private String paypal;
    
    private Benutzer benutzer;
    private Charity charity;
    
    
    
    @PostConstruct
    public void init() {
        benutzer = sessionData.getBenutzer();
        charity = sessionData.getSpendenCharity();
    }
    
//    public String gibaktivesKonto(Charity charity) {
//        
//        paypal = charity.getBankkonto().get(0).getPaypalid();
//        
//        String paypal = null;
//        for(int i = 0; i <= 1; i++) {
//            if(charity.getBankkonto().get(0).isAktivKonto() == true) {
//                paypal = charity.getBankkonto().get(0).getPaypalid();
//            }            
//        }
//        return paypal;
//    }
    
    public String spenden(Charity charity) {
        // Prüfung ob Benutzer angemeldet ist
        if(sessiondata.getBenutzer() != null) {
            sessionData.setSpendenCharity(charity);
            return "/spenden.xhtml?faces-redirect=true";
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Sie sind momentan nicht angemeldet!");
            PrimeFaces.current().dialog().showMessageDynamic(message);
            return null;
        }
    }
    
    public String spendenpräsentation() {
        // Prüfung ob Benutzer angemeldet ist
        if(sessiondata.getBenutzer() != null) {
            Charity charity = sessiondata.getSelectedCharity();
            sessionData.setSpendenCharity(charity);
                    
            return "/spenden.xhtml?faces-redirect=true";
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Sie sind momentan nicht angemeldet!");
            PrimeFaces.current().dialog().showMessageDynamic(message);
            return null;
        }
    }
//    
    public String spendenAktion() {
        // Prüfung ob Benutzer angemeldet ist
        if(sessiondata.getBenutzer() != null) {
            Einkommen einkommen = jahreseinkommen();
            charityController.updateEinkommen(einkommen.getIdEinkommen(), this.betrag);
            userController.newQuittung(this.betrag, spendenaktion, this.charity, sessiondata.getBenutzer());
            einkommenModel.listeNeu();
            orgaEinkommenModel.einkommenliste();
            return "/User/SpendenDanke.xhtml?faces-redirect=true";
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Sie sind momentan nicht angemeldet!");
            PrimeFaces.current().dialog().showMessageDynamic(message);
            return null;
        }
    }
    public String spendestripe(int betrag) {
        // Prüfung ob Benutzer angemeldet ist
        if(sessiondata.getBenutzer() != null) {
            Einkommen einkommen = jahreseinkommen();
            charityController.updateEinkommen(einkommen.getIdEinkommen(),betrag);
            System.out.println("here is" + this.charity.getName());
            System.out.println("here is" + sessiondata.getBenutzer().getNachname());
            userController.newQuittung(betrag, "", this.charity, sessiondata.getBenutzer());
            einkommenModel.listeNeu();
            orgaEinkommenModel.einkommenliste();
            return "/User/SpendenDanke.xhtml?faces-redirect=true";
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Sie sind momentan nicht angemeldet!");
            PrimeFaces.current().dialog().showMessageDynamic(message);
            return null;
        }
    }
    
    public Einkommen jahreseinkommen() {
        // Aktuelles Jahr ermitteln:
        Date dt = new Date();
        int jahr = dt.getYear();
        int aktuellesJahr = jahr + 1900; 
        // Ermitteln des Einkommens für dieses Jahr:
        List<Einkommen> einkommenList = charityController.jahrEinkommen(aktuellesJahr, this.charity.getIdCharity());
        // Prüfen ob das Einkommmen schon vorhanden ist:
        if(einkommenList.size() == 1) {
           return einkommenList.get(0); 
        } else {
            // Neues Einkommen erstellen:
            einkommenList.clear();
            charityController.createEinkommen(aktuellesJahr, 0.0, this.charity);
            // Neu erstelltes Einkommen suchen und ausgeben:
            einkommenList = charityController.jahrEinkommen(aktuellesJahr, getCharity().getIdCharity());
            return einkommenList.get(0);
        }
    }
    
    // Getter und Setter:

    public String getSpendenaktion() {
        return spendenaktion;
    }

    public void setSpendenaktion(String spendenaktion) {
        this.spendenaktion = spendenaktion;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public double getBetrag() {
        return betrag;
    }

    public void setBetrag(double betrag) {
        this.betrag = betrag;
    }

    public Benutzer getBenutzer() {
        return benutzer;
    }

    public void setBenutzer(Benutzer benutzer) {
        this.benutzer = benutzer;
    }

    public Charity getCharity() {
        return charity;
    }

    public void setCharity(Charity charity) {
        this.charity = charity;
    }

    public String getBezahlt() {
        return bezahlt;
    }

    public void setBezahlt(String bezahlt) {
        this.bezahlt = bezahlt;
    }

    public List<String> getKategorien() {
        return charityController.findCategories(charity.getIdCharity());
    }

    public String getPaypal() {
        return paypal;
    }

    public void setPaypal(String paypal) {
        this.paypal = paypal;
    }
    
}
