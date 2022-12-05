
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

/**
 *
 * @author lu80238
 */
@Named(value="kategorieformularModel")
@RequestScoped
public class KategorieformularModel implements Serializable{
    
    @Inject
    private CharityController charityController;
    
    @Inject
    private IndexModel indexView;
    
    @Inject
    private SessionData sessionData;
    
    private Long idKategorie;
    private String nameKategorie;
    private Kategorie selectedkategorien;
    
    
    @PostConstruct
    public void intit() {
        
    }
    
    
    public String newKategorie() {        
        if(this.nameKategorie.isEmpty()){
            FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Aktionbereich ist leer",
							""));
            return null;
            
        }else {
        charityController.newKategorie(this.nameKategorie, sessionData.getBenutzer().getCharity());
        sessionData.setSelectedCharity(null);
        return "/Orga/OrgaKategorien.xhtml?faces-redirect=true"; 
        }
          
    }
    
    /**
     * Abbrechen Funktion für die Eingabeformulare:
     * Redirect auf die Startseite, damit alle Werte verworfen werden.
     * @return 
     */
    public String abbrechen() {
        return "/Orga/OrgaKategorien.xhtml?faces-redirect=true";        
    }
    
    
    // Getter und Setter:

    public Long getIdKategorie() {
        return idKategorie;
    }

    public void setIdKategorie(Long idKategorie) {
        this.idKategorie = idKategorie;
    }  

    public String getNameKategorie() {
        return nameKategorie;
    }

    public void setNameKategorie(String nameKategorie) {
        this.nameKategorie = nameKategorie;
    }

    public Kategorie getSelectedkategorien() {
        return selectedkategorien;
    }

    public void setSelectedkategorien(Kategorie selectedkategorien) {
        this.selectedkategorien = selectedkategorien;
    }
    
    
    
    
}