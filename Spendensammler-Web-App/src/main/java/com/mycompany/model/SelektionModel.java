
package com.mycompany.model;

import com.mycompany.controller.CharityController;
import com.mycompany.model.SessionData;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ManagedProperty;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.primefaces.PrimeFaces;
import org.primefaces.event.ColumnResizeEvent;
import spendensammler.jpa.entities.Charity;

@Named(value="selektionModel")
@ViewScoped
public class SelektionModel implements Serializable{
    
    @Inject
    private CharityController charityController;
    
    @Inject
    private EingabeUpdateModel eingabeUpdateView;
    
    @Inject
    private SessionData sessionData;
    
    private List<Charity> charityList;
    private Charity selectedCharity;
    private List<Charity> selectedCharitys;
    private boolean globalFilterOnly;
    
    @PostConstruct
    public void init() {
        charityList = sessionData.getSelectedCharitys();
    }
    
    /**
     * Funktion zum Löschen von Ausgewählten Objekten:
     * Es wird eine Map mit allen Charity Objekten gefüllt, in dieser wird 
     * fetgehalten, ob die Checkbox zum Lsöchen angehakt war oder nicht.
     * 
     * Wenn nicht, werden die Objekte im nächsten Schritt wieder entfernt aus
     * der Liste, sodass nur die benötigten Obejkte übrig bleiben.
     * Diese werden dann anhand der ID identifiziert und aus der Datenbank
     * enfernt.
     * @return 
     */
    public String markierteElementeLoeschen() {
        for(int i = 0; i < selectedCharitys.size(); i++) {
            charityController.elementeLoeschen(selectedCharitys.get(i));
            charityList.remove(i);
        }
        sessionData.setSelectedCharitys(charityList); 
        selectedCharitys.clear();
        return "/Selektion.xhtml?faces-redirect=true";    
    }
    
    public String aendern() {
        if(selectedCharitys.size() > 1) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", " Sie haben zu viele Objekte für diese Aktion ausgewählt!");
            PrimeFaces.current().dialog().showMessageDynamic(message);
            return null;
        } else {
            eingabeUpdateView.setSelectedCharity(selectedCharitys.get(0));
            return "/EingabeUpdate.xhtml?faces-redirect=true";
        }
    }
    
    public String selektionAuswahl() {
        if(selectedCharitys.size() >= 1) {
            sessionData.setSelectedCharitys(selectedCharitys);
            sessionData.setSelektionCheck(true);
            return "/Selektion.xhtml?faces-redirect=true?";
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Sie haben keine Elemente Ausgewählt!");
            PrimeFaces.current().dialog().showMessageDynamic(message);
            return null;
        }
    }
    
    public String selektionVerlassen() {
        sessionData.setSelektionCheck(false);
        List<Charity> newList = new ArrayList<Charity>();
        sessionData.setSelectedCharitys(newList);
        return "/Organisationen.xhtml?faces-redirect=true";
    }
    
    public String orgaOeffnen() {
        sessionData.setSelectedCharity(getSelectedCharity());
        return "/presentationOrga.xhtml?faces-redirect=true";
        
    }
    
    @PreDestroy
    private void end() {
        
    }
    
    /**
     * 
     * Beginn Getter und Setter:
     *  
     */
    
    public List<Charity> getCharitys() {
        charityList = charityController.findAll();        
        return charityList;
    }   

    public CharityController getCharityController() {
        return charityController;
    }

    public List<Charity> getCharityList() {
        return charityList;
    }

    public List<Charity> getSelectedCharitys() {
        return selectedCharitys;
    }

    public Charity getSelectedCharity() {        
        return selectedCharity;
    }

    public void setCharityController(CharityController charityController) {
        this.charityController = charityController;
    }

    public void setCharityList(List<Charity> charityList) {
        this.charityList = charityList;
    }

    public void setSelectedCharity(Charity selectedCharity) {
        this.selectedCharity = selectedCharity;
    }

    public void setSelectedCharitys(List<Charity> selectedCharitys) {
        this.selectedCharitys = selectedCharitys;
    }

    public boolean isGlobalFilterOnly() {
        return globalFilterOnly;
    }

    public void setGlobalFilterOnly(boolean globalFilterOnly) {
        this.globalFilterOnly = globalFilterOnly;
    }
    
}