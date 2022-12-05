
package com.mycompany.model;

import com.mycompany.controller.CharityController;
import com.mycompany.controller.UserController;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
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
@Named(value="adminQuittungModel")
@ViewScoped
public class AdminQuittungModel implements Serializable{
    
    @Inject
    private CharityController charityController;
    
    @Inject
    private UserController userController;
    
    @Inject
    private AdminQuittungUpdateModel quittungUpdateModel;
    
    @Inject
    private SessionData sessiondata;
    
    // Variablen:
    private Long idQuittung;
    private double summeQuittung;
    private String textQuittung;
    private Quittung quittung;
    private Quittung selectedQuittung;
    
    //Listen:
    private List<Quittung> quittungenListe;
    private List<Quittung> selectedQuittungenListe;
    
    @PostConstruct
    public void init() {
        if(sessiondata.getBenutzer().getRolleBenutzer().equals("Admin")) {
            quittungenListe = userController.findAllQuittungen();
        } else if(sessiondata.getBenutzer().getRolleBenutzer().equals("Organisation")) {
            quittungenListe = userController.findAllQuittungenOrga(sessiondata.getBenutzer().getCharity().getIdCharity());
        }
    }
    
    public String markierteElementeLoeschenQui() {
        for(int i = 0; i < selectedQuittungenListe.size(); i++) {
            userController.elementeLoeschenQuittung(selectedQuittungenListe.get(i));
            quittungenListe.remove(selectedQuittungenListe.get(i));
        }
        selectedQuittungenListe.clear();  
        return "/Admin/AdminQuittungen.xhtml?faces-redirect=true";    
    }
    
    public String aendern() {
        if(selectedQuittungenListe.size() > 1) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", " Sie haben zu viele Objekte für diese Aktion ausgewählt!");
            PrimeFaces.current().dialog().showMessageDynamic(message);
            return null;
        } else {
            quittungUpdateModel.setSelectedQuittung(selectedQuittungenListe.get(0));
            return "/Admin/QuittungenBearbeiten.xhtml?faces-redirect=true";
        }
    }
    
    public String newQuittung() {
        userController.newQuittungAdmin(this.summeQuittung, this.textQuittung);
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