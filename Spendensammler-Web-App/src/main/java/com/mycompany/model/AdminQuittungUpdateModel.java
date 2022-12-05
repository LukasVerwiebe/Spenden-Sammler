
package com.mycompany.model;

import com.mycompany.controller.CharityController;
import com.mycompany.controller.UserController;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import org.primefaces.PrimeFaces;
import spendensammler.jpa.entities.Quittung;

/**
 *
 * @author Lukas
 */
@Named(value="adminQuittungUpdateModel")
@SessionScoped
public class AdminQuittungUpdateModel implements Serializable{
    
    @Inject
    private CharityController charityController;
    
    @Inject
    private UserController userController;
    
    @Inject
    private BenutzerBearbeitenAdminModel benutzerBearbeitenAdminModel;
    
    @Inject
    private SessionData sessiondata;
    
    // Variablen:
    private Long idQuittung;
    private double summeQuittung;
    private String textQuittung;
    private Quittung quittung;
    
    //Listen:
    private List<Quittung> quittungenListe;
    private List<Quittung> selectedQuittungenListe;
    private Quittung selectedQuittung;
    
    @PostConstruct
    public void init() {
    }
    
    public String updateQuittung() {
        userController.updateQuittung(selectedQuittung.getIdQuittung(), selectedQuittung.getSummeQuittung(), selectedQuittung.getTextQuittung());        
        return "/Admin/AdminQuittungen.xhtml?faces-redirect=true";        
    }    
    
    public String abbrechen() {
        return "/Admin/AdminQuittungen.xhtml?faces-redirect=true";                 
    }
    
    
    // Getter und Setter:

    public Long getIdQuittung() {
        return idQuittung;
    }

    public void setIdQuittung(Long idQuittung) {
        this.idQuittung = idQuittung;
    }

    public double getSummeQuittung() {
        return summeQuittung;
    }

    public void setSummeQuittung(double summeQuittung) {
        this.summeQuittung = summeQuittung;
    }

    public String getTextQuittung() {
        return textQuittung;
    }

    public void setTextQuittung(String textQuittung) {
        this.textQuittung = textQuittung;
    }

    public Quittung getQuittung() {
        return quittung;
    }

    public void setQuittung(Quittung quittung) {
        this.quittung = quittung;
    }

    public List<Quittung> getQuittungenListe() {
        return quittungenListe;
    }

    public void setQuittungenListe(List<Quittung> quittungenListe) {
        this.quittungenListe = quittungenListe;
    }

    public List<Quittung> getSelectedQuittungenListe() {
        return selectedQuittungenListe;
    }

    public void setSelectedQuittungenListe(List<Quittung> selectedQuittungenListe) {
        this.selectedQuittungenListe = selectedQuittungenListe;
    }

    public Quittung getSelectedQuittung() {
        return selectedQuittung;
    }

    public void setSelectedQuittung(Quittung selectedQuittung) {
        this.selectedQuittung = selectedQuittung;
    }
    
}