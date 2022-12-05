
package com.mycompany.model;

import com.mycompany.controller.CharityController;
import com.mycompany.model.SessionData;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ManagedProperty;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import spendensammler.jpa.entities.Charity;
import spendensammler.jpa.entities.Kategorie;
import spendensammler.jpa.entities.PlaceOfAction;

@Named(value="placeOfActionModel")
@RequestScoped
public class PlaceOfActionModel implements Serializable{
    
    @Inject
    private CharityController charityController;
    
    @Inject
    private IndexModel indexView;
    
    @Inject
    private KategorieformularModel kategorieFormularView;
    
    @Inject
    private SessionData sessionData;
    
    private Long idPlaceOfAction;  
    private String landPlaceOfAction;
    private String isocode;
    private PlaceOfAction selectedPlaceOfAction;
    
    
    @PostConstruct
    public void intit() {
    }
    
    
    public String newPlaceOfAction() {
        if(this.landPlaceOfAction.isEmpty()){
            FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Land ist leer",
							""));
            return null;
            
        }else {
        charityController.newPlaceOfAction(this.landPlaceOfAction, sessionData.getBenutzer().getCharity());
        sessionData.setSelectedCharity(null);
        return "/Orga/OrgaArbeitsbereiche.xhtml?faces-redirect=true";  
        }
    }
       
    
    /**
     * Abbrechen Funktion für die Eingabeformulare:
     * Redirect auf die Startseite, damit alle Werte verworfen werden.
     * @return 
     */
    public String abbrechen() {
        return "/Orga/OrgaArbeitsbereiche.xhtml?faces-redirect=true";        
    }    
    
    /**
     * 
     * Beginn Getter und Setter:
     *  
     */
    public Long getIdPlaceOfAction() {
        return idPlaceOfAction;
    }

    public void setIdPlaceOfAction(Long idPlaceOfAction) {
        this.idPlaceOfAction = idPlaceOfAction;
    }

    public String getLandPlaceOfAction() {
        return landPlaceOfAction;
    }

    public void setLandPlaceOfAction(String landPlaceOfAction) {
        this.landPlaceOfAction = landPlaceOfAction;
    }

    public PlaceOfAction getSelectedPlaceOfAction() {
        return selectedPlaceOfAction;
    }

    public void setSelectedPlaceOfAction(PlaceOfAction selectedPlaceOfAction) {
        this.selectedPlaceOfAction = selectedPlaceOfAction;
    }

    public String getIsocode() {
        return isocode;
    }

    public void setIsocode(String isocode) {
        this.isocode = isocode;
    }
     
}