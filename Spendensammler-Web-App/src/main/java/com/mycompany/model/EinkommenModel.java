
package com.mycompany.model;

import com.mycompany.controller.CharityController;
import com.mycompany.controller.UserController;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.List;
import spendensammler.jpa.entities.Einkommen;
import spendensammler.jpa.entities.Quittung;

/**
 *
 * @author lu80238
 */
@Named(value="einkommenModel")
@RequestScoped
public class EinkommenModel {
    
    @Inject
    private CharityController charityController;
    
    @Inject
    private UserController userController;
    
    @Inject
    private AdminQuittungUpdateModel quittungUpdateModel;
    
    @Inject
    private SessionData sessiondata;
    
    // Variablen:
    private Long idEinkommen;    
    private int jahrEinkommen;
    private double geldEinkommen;
    
    //Listen:
    private List<Einkommen> einkommenListe;
    private List<Einkommen> selectedEinkommenListe;
    
    
    @PostConstruct
    public void init() {
        if(sessiondata.getBenutzer().getRolleBenutzer().equals("Admin")) {
            einkommenListe = userController.findAllEinkommen();
        }
    }
    
    public void listeNeu() {
        if(sessiondata.getBenutzer().getRolleBenutzer().equals("Admin")) {
            einkommenListe = userController.findAllEinkommen();
        }
    }
    
    // Getter und Setter:
    public Long getIdEinkommen() {
        return idEinkommen;
    }

    public void setIdEinkommen(Long idEinkommen) {
        this.idEinkommen = idEinkommen;
    }

    public int getJahrEinkommen() {
        return jahrEinkommen;
    }

    public void setJahrEinkommen(int jahrEinkommen) {
        this.jahrEinkommen = jahrEinkommen;
    }

    public double getGeldEinkommen() {
        return geldEinkommen;
    }

    public void setGeldEinkommen(double geldEinkommen) {
        this.geldEinkommen = geldEinkommen;
    }
    
    public List<Einkommen> getSelectedEinkommenListe() {
        return selectedEinkommenListe;
    }

    public void setSelectedEinkommenListe(List<Einkommen> selectedEinkommenListe) {
        this.selectedEinkommenListe = selectedEinkommenListe;
    }

    public List<Einkommen> getEinkommenListe() {
        return einkommenListe;
    }

    public void setEinkommenListe(List<Einkommen> einkommenListe) {
        this.einkommenListe = einkommenListe;
    }
    
}