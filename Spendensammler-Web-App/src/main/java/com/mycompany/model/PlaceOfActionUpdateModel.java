
package com.mycompany.model;

import com.mycompany.controller.CharityController;
import com.mycompany.model.SessionData;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
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

@Named(value="placeOfActionUpdateModel")
@SessionScoped
public class PlaceOfActionUpdateModel implements Serializable{
    
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
    
    
    private PlaceOfAction selectedPlaceOfAction;

    @PostConstruct
    public void intit() {
    }

    public String updatePlaceOfAction() {
        charityController.upadatePlaceOfAction(selectedPlaceOfAction.getIdPlaceOfAction(), selectedPlaceOfAction.getLandPlaceOfAction());
        //charityController.upadateKategorie(selectedKategorie.getIdKategorie(), selectedKategorie.getNameKategorie());
        return "/Orga/OrgaArbeitsbereiche.xhtml?faces-redirect=true";
    }
    
    public String updateSelectedPlaceofAction(PlaceOfAction placeofaction) {
        setSelectedPlaceOfAction(placeofaction);
        return "/PlaceOfActionUpdate.xhtml?faces-redirect=true";
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
}